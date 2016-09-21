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
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("unused")
public class FileReaderLacksCharsetPositiveCases {

  public void newFileReaderWithFile()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileReader must be avoided as you are unable to specify the charset.
    new FileReader(new File("test"));
  }

  public void newFileReaderWithFileDescriptor()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileReader must be avoided as you are unable to specify the charset.
    new FileReader(FileDescriptor.out);
  }

  public void newFileReaderWithString()
      throws IOException {

    // BUG: Diagnostic contains: java.io.FileReader must be avoided as you are unable to specify the charset.
    new FileReader("test");
  }

}
