package com.github.pwittchen.kirai.library;

import com.github.pwittchen.kirai.library.terminal.TerminalSyntax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TerminalSyntaxTest {

  private Syntax syntax = new TerminalSyntax();

  @Test(expected = UnsupportedOperationException.class)
  public void testItalicFormatShouldNotBeSupported() {
    syntax.getItalicFormat();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testBigFormatShouldNotBeSupported() {
    syntax.getBigFormat();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSmallFormatShouldNotBeSupported() {
    syntax.getSmallFormat();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSubFormatShouldNotBeSupported() {
    syntax.getSubFormat();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSupFormatShouldNotBeSupported() {
    syntax.getSupFormat();
  }

  @Test
  public void testColorCodeShouldBeValid() {
    // given
    String validColorCode = "150"; // number greater than 0 and lower than 257

    // when
    syntax.validateColorCode(validColorCode);

    // then no exception is thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorCodeShouldBeInValidForZero() {
    // given
    String invalidColorCode = "0";

    // when
    syntax.validateColorCode(invalidColorCode);

    // then an exception is thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorCodeShouldBeInValidForNumberLowerThanZero() {
    // given
    String invalidColorCode = "-3";

    // when
    syntax.validateColorCode(invalidColorCode);

    // then an exception is thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorCodeShouldBeInValidForNumberGreaterThan256() {
    // given
    String invalidColorCode = "258";

    // when
    syntax.validateColorCode(invalidColorCode);

    // then an exception is thrown
  }
}
