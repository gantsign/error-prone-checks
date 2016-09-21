package com.github.gantsign.errorprone.require.charset.testdata;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("unused")
public class FileWriterLacksCharsetPositiveCases {

  public void newFileWriterWithFile()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileWriter must be avoided as you are unable to specify the charset.
    new FileWriter(new File("test"));
  }

  public void newFileWriterWithFileAndBoolean()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileWriter must be avoided as you are unable to specify the charset.
    new FileWriter(new File("test"), true);
  }

  public void newFileWriterWithFileDescriptor()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileWriter must be avoided as you are unable to specify the charset.
    new FileWriter(FileDescriptor.out);
  }

  public void newFileWriterWithString()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileWriter must be avoided as you are unable to specify the charset.
    new FileWriter("test");
  }

  public void newFileWriterWithStringAndBoolean()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileWriter must be avoided as you are unable to specify the charset.
    new FileWriter("test", true);
  }
}
