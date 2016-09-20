package com.github.gantsign.errorprone.require.charset.testdata;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NewStringWithoutCharsetNegativeCases {

  private static final byte[] TEST_BYTES = "test".getBytes(UTF_8);

  public void newString() {
    new String(TEST_BYTES, UTF_8);
  }
}
