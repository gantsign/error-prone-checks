package com.github.gantsign.errorprone.require.charset;

import java.lang.reflect.Constructor;
import org.junit.Test;

/**
 * Unit tests for {@link CharsetSuggestions}.
 */
public class CharsetSuggestionsTest {

  @Test
  public void testNew()
      throws Exception {

    // Needed to comply with code coverage rules.
    Constructor<CharsetSuggestions> constructor = CharsetSuggestions.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    constructor.newInstance();
  }

}
