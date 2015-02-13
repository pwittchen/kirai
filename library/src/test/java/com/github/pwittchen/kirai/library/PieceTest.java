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

import junit.framework.TestCase;

/**
 * This test can be executed with an Android device or emulator.
 */
public class PieceTest extends TestCase {

    private String testKey;
    private String testValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testKey = "testKey";
        testValue = "testValue";
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        /**
         * Test cleaning operations can be executed here.
         * Right now, we don't have to do anything like that, but we keep this method as a template.
         */
    }

    public void testPut() throws Exception {
        // given
        // data in setUp() method

        // when
        Piece piece = Piece.put(testKey, testValue);

        // then
        assertEquals(piece.getKey(), testKey);
        assertEquals(piece.getValue(), testValue);
    }

    public void testPutShouldThrowExceptionWhenKeyAndValueAreNull() throws Exception {
        // given
        String nullTestKey = null;
        String nullTestValue = null;
        String expectedExceptionMessage = "Key and value cannot be null or empty";

        try {
            // when
            Piece.put(nullTestKey, nullTestValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyAndValueAreEmpty() throws Exception {
        // given
        String emptyTestKey = "";
        String emptyTestValue = "";
        String expectedExceptionMessage = "Key and value cannot be null or empty";

        try {
            // when
            Piece.put(emptyTestKey, emptyTestValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testBold() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.STRONG_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).bold();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testItalic() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.ITALIC_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).italic();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testUnderline() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.UNDERLINE_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).underline();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testBig() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.BIG_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).big();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testSmall() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SMALL_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).small();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testSub() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SUB_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).sub();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testSup() throws Exception {
        // given
        String expectedFormattedValue = String.format(Piece.SUP_FORMAT, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).sup();

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testColor() throws Exception {
        // given
        String testColorHexValue = "#FF0000";
        String expectedFormattedValue = String.format(Piece.COLOR_FORMAT, testColorHexValue, testValue);

        // when
        Piece piece = Piece.put(testKey, testValue).color(testColorHexValue);

        // then
        assertEquals(piece.getValue(), expectedFormattedValue);
    }

    public void testColorShouldThrowExceptionWhenHexIsNull() throws Exception {
        // given
        String nullTestColorHexValue = null;
        String expectedExceptionMessage = "Hex value of the color cannot be null or empty";

        // when
        try {
            Piece.put(testKey, testValue).color(nullTestColorHexValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testColorShouldThrowExceptionWhenHexIsEmpty() throws Exception {
        // given
        String emptyTestColorHexValue = "";
        String expectedExceptionMessage = "Hex value of the color cannot be null or empty";

        // when
        try {
            Piece.put(testKey, testValue).color(emptyTestColorHexValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testColorHexShouldContainSevenCharacters() throws Exception {
        // given
        String correctTestColorHexValue = "#FFFFFF";

        // when
        Piece.put(testKey, testValue).color(correctTestColorHexValue);

        // then
        // no exception occurs
    }

    public void testColorShouldThrowExceptionWhenHexDoesNotContainSevenCharacters() throws Exception {
        // given
        String incorrectTestColorHexValue = "#FFFFFFF";
        String expectedExceptionMessage = "Hex value have to contain 7 characters including hash sign (#)";

        try {
            // when
            Piece.put(testKey, testValue).color(incorrectTestColorHexValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testColorShouldThrowExceptionWhenHexDoesNotStartFromHash() throws Exception {
        // given
        String incorrectTestColorHexValue = "F#FFFFF";
        String expectedExceptionMessage = "Hex value have to start from hash sign (#)";

        try {
            // when
            Piece.put(testKey, testValue).color(incorrectTestColorHexValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

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
        assertEquals(piece.getValue(), expectedFormattedValue);
    }
}