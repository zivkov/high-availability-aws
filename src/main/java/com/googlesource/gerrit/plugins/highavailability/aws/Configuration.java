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

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.google.gerrit.server.config.ConfigUtil;
import com.google.inject.Singleton;
import java.time.Duration;
import org.eclipse.jgit.lib.Config;
import java.nio.file.Path;

@Singleton
public record Configuration(Identity identity, Topics topics, Limits limits, Retry retry) {

  public record Identity(
      String region, Path accessKeyIdLocation, Path secretAccessKeyLocation) {}

  public record Topics(String main, String streamEvents) {}

  public record Limits(
      Duration visibilityTimeout,
      Duration waitTime,
      int maxNumberOfMessages,
      int messageProcessingThreads) {}

  public record Retry(int maxReceiveCount) {}

  private static final String AWS = "aws";
  private static final String TOPICS = "topics";
  private static final String LIMITS = "limits";
  private static final String RETRY = "retry";

  public Configuration(Config cfg) {
    this(
        new Identity(
            cfg.getString(AWS, null, "region"),
            Path.of(cfg.getString(AWS, null, "accessKeyIdLocation")),
            Path.of(cfg.getString(AWS, null, "secretAccessKeyLocation"))),
        new Topics(
            getString(cfg, AWS, TOPICS, "main", "default"),
            getString(cfg, AWS, TOPICS, "streamEvents", "stream-events")),
        new Limits(
            duration(cfg, AWS, LIMITS, "visibilityTimeout", Duration.ofSeconds(2)),
            duration(cfg, AWS, LIMITS, "waitTime", Duration.ofSeconds(20)),
            cfg.getInt(AWS, LIMITS, "maxNumberOfMessages", 10),
            cfg.getInt(AWS, LIMITS, "messageProcessingThreadPoolSize", 4)),
        new Retry(cfg.getInt(AWS, RETRY, "maxReceiveCount", 5)));
  }

  private static Duration duration(
      Config c, String section, String subsection, String key, Duration defaultValue) {
    return Duration.ofMillis(
        ConfigUtil.getTimeUnit(c, section, subsection, key, defaultValue.toMillis(), MILLISECONDS));
  }

  private static String getString(
      Config cfg, String section, String subsection, String field, String def) {
    String value = cfg.getString(section, subsection, field);
    return value == null ? def : value;
  }
}
