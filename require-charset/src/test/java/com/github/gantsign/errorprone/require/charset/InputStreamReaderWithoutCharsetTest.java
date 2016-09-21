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

package com.github.gantsign.errorprone.require.charset;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link InputStreamReaderWithoutCharset}.
 */
@RunWith(JUnit4.class)
public class InputStreamReaderWithoutCharsetTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper =
        CompilationTestHelper.newInstance(InputStreamReaderWithoutCharset.class, getClass());
  }

  @Test
  public void inputStreamReaderWithoutCharsetPositiveCases() {
    compilationHelper.addSourceFile("InputStreamReaderWithoutCharsetPositiveCases.java").doTest();
  }

  @Test
  public void inputStreamReaderWithoutCharsetNegativeCases() {
    compilationHelper.addSourceFile("InputStreamReaderWithoutCharsetNegativeCases.java").doTest();
  }
}
