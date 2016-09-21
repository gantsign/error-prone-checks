package com.github.gantsign.errorprone.require.charset;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link OutputStreamWriterWithoutCharset}.
 */
@RunWith(JUnit4.class)
public class OutputStreamWriterWithoutCharsetTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper =
        CompilationTestHelper.newInstance(OutputStreamWriterWithoutCharset.class, getClass());
  }

  @Test
  public void newStringPositiveCases() {
    compilationHelper.addSourceFile("OutputStreamWriterWithoutCharsetPositiveCases.java").doTest();
  }

  @Test
  public void newStringNegativeCases() {
    compilationHelper.addSourceFile("OutputStreamWriterWithoutCharsetNegativeCases.java").doTest();
  }
}
