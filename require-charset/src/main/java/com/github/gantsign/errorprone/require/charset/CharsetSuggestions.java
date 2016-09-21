/*
 * Copyright 2016 GantSign Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.gantsign.errorprone.require.charset;

import com.google.common.collect.ImmutableList;

/**
 * Suggestions of {@link java.nio.charset.Charset} values.
 */
final class CharsetSuggestions {

  private CharsetSuggestions() {
  }

  static final ImmutableList<String> CHARSET_SUGGESTIONS = ImmutableList.of(
      "java.nio.charset.StandardCharsets.UTF_8",
      "java.nio.charset.StandardCharsets.ISO_8859_1",
      "java.nio.charset.Charset.forName(\"windows-1252\")",
      "java.nio.charset.Charset.defaultCharset()");
}
