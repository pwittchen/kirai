package com.github.pwittchen.kirai.library;

import com.github.pwittchen.kirai.library.terminal.TerminalBgColor;
import com.github.pwittchen.kirai.library.terminal.TerminalColor;
import com.github.pwittchen.kirai.library.terminal.TerminalPiece;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class TerminalPieceTest {

  @Test
  public void testShouldPutTerminalPiece() {
    // given
    String key = "key";
    String value = "value";

    // when
    TerminalPiece piece = TerminalPiece.put(key, value);

    // then
    assertThat(piece).isNotNull();
  }

  @Test
  public void testShouldPutTerminalPieceWithTerminalColor() {
    // given
    String key = "key";
    String value = "value";

    // when
    TerminalPiece piece = TerminalPiece.put(key, value).color(TerminalColor.BLACK);

    // then
    assertThat(piece).isNotNull();
  }

  @Test
  public void testShouldPutTerminalPieceWithTerminalBackgroundColor() {
    // given
    String key = "key";
    String value = "value";

    // when
    TerminalPiece piece = TerminalPiece.put(key, value).background(TerminalBgColor.BLACK);

    // then
    assertThat(piece).isNotNull();
  }
}
