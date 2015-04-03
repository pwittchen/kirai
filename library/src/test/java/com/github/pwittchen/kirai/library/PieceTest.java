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

    @Before
    public void setUp() {
        testKey = "testKey";
        testValue = "testValue";
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
    public void testBold() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.STRONG_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).bold();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testItalic() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.ITALIC_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).italic();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testUnderline() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.UNDERLINE_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).underline();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testBig() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.BIG_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).big();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testSmall() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SMALL_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).small();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testSub() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SUB_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).sub();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testSup() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SUP_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).sup();

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test
    public void testColor() throws Exception {
        // given
        String testColorHexValue = "#FF0000";
        String expectedFormattedValue = String.format(Piece.COLOR_FORMAT, testColorHexValue, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).color(testColorHexValue);

        // then
        assertThat(piece.getValue()).isEqualTo(expectedFormattedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColorShouldThrowExceptionWhenHexIsNull() throws Exception {
        // given
        String nullTestColorHexValue = null;

        // when
        Piece.put(testKey, testValue).color(nullTestColorHexValue);

        // then
        // throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColorShouldThrowExceptionWhenHexIsEmpty() throws Exception {
        // given
        String emptyTestColorHexValue = "";

        // when
        Piece.put(testKey, testValue).color(emptyTestColorHexValue);

        // then
        // throw an exception
    }

    @Test
    public void testColorHexShouldContainSevenCharacters() throws Exception {
        // given
        String correctTestColorHexValue = "#FFFFFF";

        // when
        Piece.put(testKey, testValue).color(correctTestColorHexValue);

        // then
        // no exception occurs
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColorShouldThrowExceptionWhenHexDoesNotContainSevenCharacters() throws Exception {
        // given
        String incorrectTestColorHexValue = "#FFFFFFF";

        // when
        Piece.put(testKey, testValue).color(incorrectTestColorHexValue);

        // then
        // throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColorShouldThrowExceptionWhenHexDoesNotStartFromHash() throws Exception {
        // given
        String incorrectTestColorHexValue = "F#FFFFF";

        // when
        Piece.put(testKey, testValue).color(incorrectTestColorHexValue);

        // then
        // throw an exception
    }

    @Test
    public void testAllFormats() {
        /**
         * I realize that setting big and small at the same time doesn't make sense
         * as well as setting sub and sup at the same time.
         * This is done just for testing purposes in order to check
         * correctness of the formatting with all possible methods.
         */

        // given
        String expectedFormattedValue;
        String testColorHexValue = "#FF0000";
        expectedFormattedValue = String.format(Piece.STRONG_FORMAT, testValue);
        expectedFormattedValue = String.format(Piece.ITALIC_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.UNDERLINE_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.BIG_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.SMALL_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.SUB_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.SUP_FORMAT, expectedFormattedValue);
        expectedFormattedValue = String.format(Piece.COLOR_FORMAT, testColorHexValue, expectedFormattedValue);

        // when
        Piece piece = Piece
                .put(testKey, testValue)
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