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

import com.google.common.base.Strings;
import java.net.URI;

public class RealAwsPubSubTestSystem extends AwsPubSubTestSystem {

  private final String region;
  private final String accessKeyLocation;
  private final String secretKeyLocation;
  private final String topic;
  private final String streamEventsTopic;

  public RealAwsPubSubTestSystem() {
    region = getEnv("AWS_REGION");
    accessKeyLocation = getEnv("AWS_ACCESS_KEY_FILE");
    secretKeyLocation = getEnv("AWS_SECRET_KEY_FILE");
    topic = getEnvOrDefault("PUBSUB_TOPIC", "default");
    streamEventsTopic = getEnvOrDefault("PUBSUB_STREAM_EVENTS_TOPIC", "stream-events");
  }

  private static String getEnv(String name) {
    String value = System.getenv(name);
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalStateException("Environment variable " + name + " is not set");
    }
    return value;
  }

  private static String getEnvOrDefault(String name, String defaultValue) {
    String value = System.getenv(name);
    if (Strings.isNullOrEmpty(value)) {
      return defaultValue;
    }
    return value;
  }

  @Override
  public void start() {}

  @Override
  public void stop() {}

  @Override
  public URI getEndpoint() {
    return null;
  }

  @Override
  public String getRegion() {
    return region;
  }

  @Override
  public String getAccessKeyLocation() {
    return accessKeyLocation;
  }

  @Override
  public String getSecretKeyLocation() {
    return secretKeyLocation;
  }

  @Override
  public String getDefaultTopicArn() {
    return null;
  }

  @Override
  public String getDefaultTopicName() {
    return topic;
  }

  @Override
  public String getStreamEventsTopicName() {
    return streamEventsTopic;
  }
}
