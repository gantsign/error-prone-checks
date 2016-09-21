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
public class NewStringWithoutCharsetTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper =
        CompilationTestHelper.newInstance(NewStringWithoutCharset.class, getClass());
  }

  @Test
  public void newStringPositiveCases() {
    compilationHelper.addSourceFile("NewStringWithoutCharsetPositiveCases.java").doTest();
  }

  @Test
  public void newStringNegativeCases() {
    compilationHelper.addSourceFile("NewStringWithoutCharsetNegativeCases.java").doTest();
  }
}
