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

package com.github.gantsign.errorprone.require.charset.testdata;

import java.nio.charset.Charset;

@SuppressWarnings("unused")
public interface CharsetForStandardCharsetPositiveCases {

  // BUG: Diagnostic contains: Did you mean 'Charset US_ASCII = java.nio.charset.StandardCharsets.US_ASCII
  Charset US_ASCII = Charset.forName("US-ASCII");

  // BUG: Diagnostic contains: Did you mean 'Charset ISO_8859_1 = java.nio.charset.StandardCharsets.ISO_8859_1
  Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

  // BUG: Diagnostic contains: Did you mean 'Charset UTF_8 = java.nio.charset.StandardCharsets.UTF_8
  Charset UTF_8 = Charset.forName("UTF-8");

  // BUG: Diagnostic contains: Did you mean 'Charset UTF_16BE = java.nio.charset.StandardCharsets.UTF_16BE
  Charset UTF_16BE = Charset.forName("UTF-16BE");

  // BUG: Diagnostic contains: Did you mean 'Charset UTF_16LE = java.nio.charset.StandardCharsets.UTF_16LE
  Charset UTF_16LE = Charset.forName("UTF-16LE");

  // BUG: Diagnostic contains: Did you mean 'Charset UTF_16 = java.nio.charset.StandardCharsets.UTF_16
  Charset UTF_16 = Charset.forName("UTF-16");

  // BUG: Diagnostic contains: Did you mean 'Charset LATIN1 = java.nio.charset.StandardCharsets.ISO_8859_1
  Charset LATIN1 = Charset.forName("latin1");

  // BUG: Diagnostic contains: Did you mean 'Charset UTF8 = java.nio.charset.StandardCharsets.UTF_8
  Charset UTF8 = Charset.forName("utf8");
}
