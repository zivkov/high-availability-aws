// Copyright (C) 2026 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.highavailability.aws;

import static org.mockito.Mockito.mock;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.ericsson.gerrit.plugins.highavailability.forwarder.commands.CommandProcessor;
import com.ericsson.gerrit.plugins.highavailability.forwarder.commands.CommandsGson;
import com.ericsson.gerrit.plugins.highavailability.forwarder.commands.ForwarderCommandsModule;
import com.google.gerrit.server.config.GerritInstanceId;
import com.google.gerrit.server.events.EventGson;
import com.google.gerrit.server.events.EventGsonProvider;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.util.Modules;
import com.googlesource.gerrit.plugins.highavailability.aws.AwsPubSubForwarderModule.LocalStackModule;
import com.googlesource.gerrit.plugins.highavailability.aws.Configuration.Identity;
import com.googlesource.gerrit.plugins.highavailability.aws.Configuration.Limits;
import com.googlesource.gerrit.plugins.highavailability.aws.Configuration.Retry;
import com.googlesource.gerrit.plugins.highavailability.aws.Configuration.Topics;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.crt.AwsCrtHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

class TestApp {
  private static final int MAX_RECEIVE_COUNT = 3;

  final String instanceId;
  final AwsPubSubTestSystem testSystem;
  CommandProcessor cmdProcessor = mock(CommandProcessor.class);

  @Inject private SnsAsyncClient snsClient;
  @Inject private SqsAsyncClient sqsClient;
  @Inject private SqsClient sqsClientSync;
  @Inject AwsPubSubForwarder forwarder;
  @Inject private SqsMessagePollerManager pollerManager;
  @Inject SqsMessagePoller.Factory pollerFactory;
  @Inject @DefaultTopic SqsQueueInfo defaultQueueInfo;
  @Inject @CommandsGson Gson gson;

  @Inject
  @Named("defaultDlq")
  SqsQueueInfo dlqInfo;

  TestApp(String instanceId, AwsPubSubTestSystem testSystem) {
    this.instanceId = instanceId;
    this.testSystem = testSystem;
  }

  void start() {
    Configuration cfg = new Configuration(
        new Identity(testSystem.getRegion(), Path.of(testSystem.getAccessKeyLocation()), Path.of(testSystem.getSecretKeyLocation())),
        new Topics(testSystem.getDefaultTopicName(), testSystem.getStreamEventsTopicName()),
        new Limits(Duration.ofSeconds(2), Duration.ofSeconds(1), 10, 2),
        new Retry(MAX_RECEIVE_COUNT));

    Module testSupportModule =
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(Configuration.class).toInstance(cfg);
            install(new ForwarderCommandsModule());
            bind(Gson.class)
                .annotatedWith(EventGson.class)
                .toInstance(new EventGsonProvider().get());
            bind(String.class).annotatedWith(GerritInstanceId.class).toInstance(instanceId);
            bind(CommandProcessor.class).toInstance(cmdProcessor);
          }

          @Provides
          @Singleton
          @Named("defaultDlq")
          SqsQueueInfo getDefaultDlqSqsQueue(
              SqsQueueCreator queueCreator, @DefaultTopic SqsQueueInfo defaultQueue)
              throws InterruptedException, ExecutionException {
            return queueCreator.createDlq(defaultQueue);
          }

          @Provides
          @Singleton
          SdkHttpClient getSdkHttpClient() {
            return AwsCrtHttpClient.builder()
                .maxConcurrency(50)
                .connectionTimeout(Duration.ofSeconds(3))
                .build();
          }

          @Provides
          @Singleton
          SqsClient getSqsClient(
              SdkHttpClient httpClient, AwsCredentialsProvider credentials, Region region) {
            return SqsClient.builder()
                .region(region)
                .endpointOverride(testSystem.getEndpoint())
                .credentialsProvider(credentials)
                .httpClient(httpClient)
                .build();
          }
        };

    AwsPubSubForwarderModule awsModule = new AwsPubSubForwarderModule();

    Module testModule;
    if (testSystem instanceof EmulatedAwsPubSubTestSystem) {
      String localStackEndpoint = testSystem.getEndpoint().toString();
      LocalStackModule localStackModule = new LocalStackModule(localStackEndpoint);
      testModule =
          Modules.combine(testSupportModule, Modules.override(awsModule).with(localStackModule));
    } else {
      testModule = Modules.combine(testSupportModule, awsModule);
    }

    Injector injector = Guice.createInjector(testModule);
    injector.injectMembers(this);
    pollerManager.start();
  }

  List<Message> receiveMessagesFromDlq() {
    return sqsClientSync
        .receiveMessage(
            req -> req.queueUrl(dlqInfo.url()).maxNumberOfMessages(10).waitTimeSeconds(10))
        .messages();
  }

  void stop() {
    pollerManager.stop();

    if (snsClient != null) {
      snsClient.close();
    }
    if (sqsClient != null) {
      sqsClient.close();
    }
  }
}
