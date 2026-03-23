// Copyright (C) 2025 The Android Open Source Project
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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;

@Singleton
public class TopicNames {
  private final Configuration.Topics topics;

  @Inject
  public TopicNames(Configuration config) {
    this.topics = config.topics();
  }

  public String defaultTopic() {
    return topics.main();
  }

  public String streamEventsTopic() {
    return topics.streamEvents();
  }

  public List<String> all() {
    return List.of(topics.main(), topics.streamEvents());
  }
}
