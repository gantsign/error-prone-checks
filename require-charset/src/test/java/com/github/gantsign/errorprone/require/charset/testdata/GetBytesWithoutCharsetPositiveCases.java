package com.github.gantsign.errorprone.require.charset.testdata;

@SuppressWarnings("unused")
public class GetBytesWithoutCharsetPositiveCases {

  public byte[] getBytes() {
    // BUG: Diagnostic contains: java.nio.charset.Charset must be specified when calling java.lang.String.getBytes().
    return "test".getBytes();
  }
}
