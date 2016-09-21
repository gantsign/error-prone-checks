package com.github.gantsign.errorprone.require.charset;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link NewStringWithoutCharset}.
 */
@RunWith(JUnit4.class)
public class FileWriterLacksCharsetTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper = CompilationTestHelper.newInstance(FileWriterLacksCharset.class, getClass());
  }

  @Test
  public void newFileWriterPositiveCases() {
    compilationHelper.addSourceFile("FileWriterLacksCharsetPositiveCases.java").doTest();
  }

}
