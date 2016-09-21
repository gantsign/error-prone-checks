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
