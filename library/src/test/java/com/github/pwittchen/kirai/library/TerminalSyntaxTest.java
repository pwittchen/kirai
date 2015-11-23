package com.github.pwittchen.kirai.library;

import com.github.pwittchen.kirai.library.terminal.TerminalSyntax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

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

  @Test
  public void testShouldHaveCorrectBoldFormat() {
    // given
    String expectedFormat = "\033[1m%s\033[21m";

    // when
    String format = syntax.getBoldFormat();

    // then
    assertThat(format).isEqualTo(expectedFormat);
  }

  @Test
  public void testShouldHaveCorrectUnderlineFormat() {
    // given
    String expectedFormat = "\033[39;4m%s\033[0m";

    // when
    String format = syntax.getUnderlineFormat();

    // then
    assertThat(format).isEqualTo(expectedFormat);
  }

  @Test
  public void testShouldHaveCorrectColorFormat() {
    // given
    String expectedFormat = "\033[%sm%s\033[0m";

    // when
    String format = syntax.getColorFormat();

    // then
    assertThat(format).isEqualTo(expectedFormat);
  }
}
