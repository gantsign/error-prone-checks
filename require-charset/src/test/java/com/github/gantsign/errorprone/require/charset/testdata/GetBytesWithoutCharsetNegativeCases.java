package com.github.gantsign.errorprone.require.charset.testdata;

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("unused")
public class GetBytesWithoutCharsetNegativeCases {

  public byte[] getBytes() {
    return "test".getBytes(UTF_8);
  }

}
