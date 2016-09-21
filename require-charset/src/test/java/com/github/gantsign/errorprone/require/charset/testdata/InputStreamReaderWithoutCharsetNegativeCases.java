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

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("unused")
public class InputStreamReaderWithoutCharsetNegativeCases {

  private static final byte[] BYTES = new byte[0];

  public void newInputStreamReaderWithCharset() {
    new InputStreamReader(new ByteArrayInputStream(BYTES), UTF_8);
  }

  public void newInputStreamReaderWithCharsetEncoder() {
    new InputStreamReader(new ByteArrayInputStream(BYTES), UTF_8.newDecoder());
  }

  public void newInputStreamReaderWithString()
      throws UnsupportedEncodingException {

    new InputStreamReader(new ByteArrayInputStream(BYTES), "UTF-8");
  }
}
