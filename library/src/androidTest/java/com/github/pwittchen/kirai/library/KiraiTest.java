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

import android.test.AndroidTestCase;

/**
 * This test can be executed with an Android device or emulator.
 */
public class KiraiTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        /**
         * Test cleaning operations can be executed here.
         * Right now, we don't have to do anything like that, but we keep this method as a template.
         */
    }

    public void testStringShouldBeBalanced() {
        // given
        String testTagOne = "tag_one";
        String testTagTwo = "tag_two";
        String testBalancedString = "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";

        // when
        Kirai.from(testBalancedString);

        // then
        // no exception should occur
    }

    public void testShouldThrowExceptionWhenStringIsNotBeBalanced() {
        // given
        String testTagOne = "tag_one";
        String testTagTwo = "tag_two";
        String testNotBalancedString = "Sample text } {{ {" + testTagOne + "} and {" + testTagTwo + "} { rest of the sentence.";
        String expectedExceptionMessage = "Braces in provided string are not balanced";

        try {
            // when
            Kirai.from(testNotBalancedString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenInputStringIsNull() {
        // given
        String testInputString = null;
        String expectedExceptionMessage = "Input string cannot be null or empty";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenInputStringIsEmpty() {
        // given
        String testInputString = "";
        String expectedExceptionMessage = "Input string cannot be null or empty";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenKeyAndValueAreNull() {
        // given
        String testTagOne = "tag_one";
        String testTagTwo = "tag_two";
        String testBalancedString = "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";
        String nullTestKey = null;
        String nullTestValue = null;
        String expectedExceptionMessage = "Key and value cannot be null or empty";

        try {
            // when
            Kirai.from(testBalancedString).put(nullTestKey, nullTestValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenKeyAndValueAreEmpty() {
        // given
        String testTagOne = "tag_one";
        String testTagTwo = "tag_two";
        String testBalancedString = "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";
        String emptyTestKey = "";
        String emptyTestValue = "";
        String expectedExceptionMessage = "Key and value cannot be null or empty";

        try {
            // when
            Kirai.from(testBalancedString).put(emptyTestKey, emptyTestValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenPieceIsNull() {
        // given
        Piece nullPiece = null;
        String testTagOne = "tag_one";
        String testInputString = "Sample text {" + testTagOne + "} rest of the sentence.";
        String expectedExceptionMessage = "Piece object cannot be null";

        try {
            // when
            Kirai.from(testInputString).put(nullPiece);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testShouldThrowExceptionWhenPieceHasKeyNotDefinedInInputString() {
        // given
        String testValue = "test value";
        String notPresentTestTag = "test_tag_NOT_present";
        Piece testPiece = Piece.put(notPresentTestTag, testValue);
        String testTagOne = "tag_one";
        String testInputString = "Sample text {" + testTagOne + "} rest of the sentence.";
        String expectedExceptionMessage = "Tag {" + notPresentTestTag + "} was not defined in input string";

        try {
            // when
            Kirai.from(testInputString).put(testPiece);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyIsNotDefinedInInputString() {
        // given
        String presentTestTag = "test_tag_present";
        String notPresentTestTag = "test_tag_NOT_present";
        String testValue = "test value";
        String testInputString = "Sample text {" + presentTestTag + "} rest of the sentence.";
        String expectedExceptionMessage = "Tag {" + notPresentTestTag + "} was not defined in input string";

        try {
            // when
            Kirai.from(testInputString).put(notPresentTestTag, testValue);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyStartsFromNumber() {
        // given
        String incorrectTestTag = "1_incorrect_tag";
        String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";
        String expectedExceptionMessage = "Tags have to start from lower case letter and can contain only lower case letters [a-z] numbers [0-9] and underscore [_]";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyStartsFromUnderscore() {
        // given
        String incorrectTestTag = "_incorrect_tag";
        String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";
        String expectedExceptionMessage = "Tags have to start from lower case letter and can contain only lower case letters [a-z] numbers [0-9] and underscore [_]";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyStartsFromSpecialCharacter() {
        // given
        String incorrectTestTag = "!_incorrect_tag";
        String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";
        String expectedExceptionMessage = "Tags have to start from lower case letter and can contain only lower case letters [a-z] numbers [0-9] and underscore [_]";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }

    public void testPutShouldThrowExceptionWhenKeyContainsSpecialCharacters() {
        // given
        String incorrectTestTag = "#!@ tag &^*_";
        String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";
        String expectedExceptionMessage = "Tags have to start from lower case letter and can contain only lower case letters [a-z] numbers [0-9] and underscore [_]";

        try {
            // when
            Kirai.from(testInputString);
            // then
            fail();
        } catch (IllegalArgumentException e) {
            // and then
            assertEquals(e.getMessage(), expectedExceptionMessage);
        }
    }
}