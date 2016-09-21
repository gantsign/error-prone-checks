package com.github.gantsign.errorprone.require.charset.testdata;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

@SuppressWarnings("unused")
public class OutputStreamWriterWithoutCharsetPositiveCases {

  public void newOutputStreamWriter() {
    // BUG: Diagnostic contains: Charset must be specified when constructing a new java.io.OutputStreamWriter.
    new OutputStreamWriter(new ByteArrayOutputStream());
  }

}
