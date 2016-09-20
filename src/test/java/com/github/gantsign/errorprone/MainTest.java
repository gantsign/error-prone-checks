package com.github.gantsign.errorprone;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class MainTest {

  @Test
  public void main()
    throws Exception {

    PrintStream out = System.out;
    try {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      System.setOut(new PrintStream(buffer, true, "UTF-8"));

      Main.main(new String[0]);

      String actual = new String(buffer.toByteArray(), UTF_8).trim();
      assertThat(actual, endsWith(".Main - Hello, World!"));
    } finally {
      System.setOut(out);
    }
  }

  @Test
  public void instantiate() {
    new Main();
  }
}
