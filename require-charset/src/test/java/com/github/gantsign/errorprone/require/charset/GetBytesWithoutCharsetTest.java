package com.github.gantsign.errorprone.require.charset;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link GetBytesWithoutCharset}.
 */
@RunWith(JUnit4.class)
public class GetBytesWithoutCharsetTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper = CompilationTestHelper.newInstance(GetBytesWithoutCharset.class, getClass());
  }

  @Test
  public void getBytesPositiveCases() {
    compilationHelper.addSourceFile("GetBytesWithoutCharsetPositiveCases.java").doTest();
  }

  @Test
  public void getBytesNegativeCases() {
    compilationHelper.addSourceFile("GetBytesWithoutCharsetNegativeCases.java").doTest();
  }
}
