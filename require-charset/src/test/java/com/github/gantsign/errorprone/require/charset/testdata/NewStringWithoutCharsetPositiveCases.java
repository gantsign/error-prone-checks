package com.github.gantsign.errorprone.require.charset.testdata;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NewStringWithoutCharsetPositiveCases {

  private static final byte[] TEST_BYTES = "test".getBytes(UTF_8);

  public void newString() {
    // BUG: Diagnostic contains: java.nio.charset.Charset must be specified when constructing a new java.lang.String from a byte array.
    new String(TEST_BYTES);
  }

}
