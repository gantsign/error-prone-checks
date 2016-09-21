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
