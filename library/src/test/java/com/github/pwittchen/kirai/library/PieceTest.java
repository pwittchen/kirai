/*
 * Copyright (C) 2015 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.kirai.library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class PieceTest {

  private String testKey;
  private String testValue;
  private Syntax htmlSyntax;

  @Before
  public void setUp() {
    testKey = "testKey";
    testValue = "testValue";
    htmlSyntax = new HtmlSyntax();
  }

  @Test
  public void testPut() throws Exception {
    // given
    // data in setUp() method

    // when
    Piece piece = Piece.put(testKey, testValue);

    // then
    assertThat(piece.getKey()).isEqualTo(testKey);
    assertThat(piece.getValue()).isEqualTo(testValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowExceptionWhenKeyAndValueAreNull() throws Exception {
    // given
    String nullTestKey = null;
    String nullTestValue = null;

    // when
    Piece.put(nullTestKey, nullTestValue);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowExceptionWhenKeyAndValueAreEmpty() throws Exception {
    // given
    String emptyTestKey = "";
    String emptyTestValue = "";

    // when
    Piece.put(emptyTestKey, emptyTestValue);

    // then
    // throw an exception
  }

  @Test
  public void testBoldHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getBoldFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).bold();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testItalicHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getItalicFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).italic();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testUnderlineHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getUnderlineFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).underline();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testBigHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getBigFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).big();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testSmallHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getSmallFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).small();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testSubHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getSubFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).sub();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testSupHtml() throws Exception {
    // given
    String expectedFormattedValue = String.format(htmlSyntax.getSupFormat(), testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).sup();

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test
  public void testColorHtml() throws Exception {
    // given
    String testColorHexValue = "#FF0000";
    String expectedFormattedValue = String.format(htmlSyntax.getColorFormat(), testColorHexValue, testValue);

    // when
    Piece piece = Piece.put(testKey, testValue).color(testColorHexValue);

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorShouldThrowExceptionWhenHexIsNullForHtml() throws Exception {
    // given
    String nullTestColorHexValue = null;

    // when
    Piece.put(testKey, testValue).color(nullTestColorHexValue);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorShouldThrowExceptionWhenHexIsEmptyForHtml() throws Exception {
    // given
    String emptyTestColorHexValue = "";

    // when
    Piece.put(testKey, testValue).color(emptyTestColorHexValue);

    // then
    // throw an exception
  }

  @Test
  public void testColorHexShouldContainSevenCharactersForHtml() throws Exception {
    // given
    String correctTestColorHexValue = "#FFFFFF";

    // when
    Piece piece = Piece.put(testKey, testValue).color(correctTestColorHexValue);

    // then
    // no exception occurs and object is created
    assertThat(piece).isNotNull();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorShouldThrowExceptionWhenHexDoesNotContainSevenCharactersForHtml() throws Exception {
    // given
    String incorrectTestColorHexValue = "#FFFFFFF";

    // when
    Piece.put(testKey, testValue).color(incorrectTestColorHexValue);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorShouldThrowExceptionWhenHexDoesNotStartFromHashForHtml() throws Exception {
    // given
    String incorrectTestColorHexValue = "F#FFFFF";

    // when
    Piece.put(testKey, testValue).color(incorrectTestColorHexValue);

    // then
    // throw an exception
  }

  @Test
  public void testAllFormatsHtml() {
    /**
     * I realize that setting big and small at the same time doesn't make sense
     * as well as setting sub and sup at the same time.
     * This is done just for testing purposes in order to check
     * correctness of the formatting with all possible methods.
     */

    // given
    String expectedFormattedValue;
    String testColorHexValue = "#FF0000";
    expectedFormattedValue = String.format(htmlSyntax.getBoldFormat(), testValue);
    expectedFormattedValue = String.format(htmlSyntax.getItalicFormat(), expectedFormattedValue);
    expectedFormattedValue = String.format(htmlSyntax.getUnderlineFormat(), expectedFormattedValue);
    expectedFormattedValue = String.format(htmlSyntax.getBigFormat(), expectedFormattedValue);
    expectedFormattedValue = String.format(htmlSyntax.getSmallFormat(), expectedFormattedValue);
    expectedFormattedValue = String.format(htmlSyntax.getSubFormat(), expectedFormattedValue);
    expectedFormattedValue = String.format(htmlSyntax.getSupFormat(), expectedFormattedValue);
    expectedFormattedValue =
        String.format(htmlSyntax.getColorFormat(), testColorHexValue, expectedFormattedValue);

    // when
    Piece piece = Piece.put(testKey, testValue)
        .bold()
        .italic()
        .underline()
        .big()
        .small()
        .sub()
        .sup()
        .color(testColorHexValue);

    // then
    assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
  }
}