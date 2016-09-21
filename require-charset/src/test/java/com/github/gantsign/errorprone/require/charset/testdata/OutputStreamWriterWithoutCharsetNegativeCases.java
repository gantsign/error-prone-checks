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

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("unused")
public class OutputStreamWriterWithoutCharsetNegativeCases {

  public void newOutputStreamWriterWithCharset() {
    new OutputStreamWriter(new ByteArrayOutputStream(), UTF_8);
  }

  public void newOutputStreamWriterWithCharsetEncoder() {
    new OutputStreamWriter(new ByteArrayOutputStream(), UTF_8.newEncoder());
  }

  public void newOutputStreamWriterWithString()
      throws UnsupportedEncodingException {

    new OutputStreamWriter(new ByteArrayOutputStream(), "UTF-8");
  }
}
