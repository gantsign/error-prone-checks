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

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("unused")
public class NewStringWithoutCharsetPositiveCases {

  private static final byte[] TEST_BYTES = "test".getBytes(UTF_8);

  public void newString() {
    // BUG: Diagnostic contains: java.nio.charset.Charset must be specified when constructing a new java.lang.String from a byte array.
    new String(TEST_BYTES);
  }

  public void newPartialString() {
    // BUG: Diagnostic contains: java.nio.charset.Charset must be specified when constructing a new java.lang.String from a byte array.
    new String(TEST_BYTES, 1, 2);
  }
}
