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

import com.github.pwittchen.kirai.library.html.HtmlPiece;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class KiraiTest {

  @Test
  public void testKiraiShouldSetAnInputAndNotBeNull() {
    // given
    String sampleString = "sample string";

    // when
    Kirai kirai = Kirai.from(sampleString);

    // then
    // no exception is thrown and object is created
    assertThat(kirai).isNotNull();
  }

  @Test
  public void testStringShouldBeBalanced() {
    // given
    String testTagOne = "tag_one";
    String testTagTwo = "tag_two";
    String testBalancedString =
        "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";

    // when
    Kirai kirai = Kirai.from(testBalancedString);

    // then
    // no exception is thrown and object is created
    assertThat(kirai).isNotNull();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenStringIsNotBeBalanced() {
    // given
    String testTagOne = "tag_one";
    String testTagTwo = "tag_two";
    String testNotBalancedString =
        "Sample text } {{ {" + testTagOne + "} and {" + testTagTwo + "} { rest of the sentence.";

    // when
    Kirai.from(testNotBalancedString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenStringHasNoStartingBrace() {
    // given
    String testTag = "tag_one";
    String testNotBalancedString = testTag + "}";

    // when
    Kirai.from(testNotBalancedString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenStringHasNoEndingBrace() {
    // given
    String testTag = "tag_one";
    String testNotBalancedString = "{" + testTag;

    // when
    Kirai.from(testNotBalancedString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenStringHasOneAdditionalEndingBrace() {
    // given
    String testTag = "tag_one";
    String testNotBalancedString = "{" + testTag + "}}";

    // when
    Kirai.from(testNotBalancedString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenInputStringIsNull() {
    // given
    String testInputString = null;

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenInputStringIsEmpty() {
    // given
    String testInputString = "";

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenKeyAndValueAreNull() {
    // given
    String testTagOne = "tag_one";
    String testTagTwo = "tag_two";
    String testBalancedString =
        "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";
    String nullTestKey = null;
    String nullTestValue = null;

    // when
    Kirai.from(testBalancedString).put(nullTestKey, nullTestValue);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenKeyAndValueAreEmpty() {
    // given
    String testTagOne = "tag_one";
    String testTagTwo = "tag_two";
    String testBalancedString =
        "Sample text {" + testTagOne + "} and {" + testTagTwo + "} rest of the sentence.";
    String emptyTestKey = "";
    String emptyTestValue = "";

    // when
    Kirai.from(testBalancedString).put(emptyTestKey, emptyTestValue);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenPieceIsNull() {
    // given
    Piece nullPiece = null;
    String testTagOne = "tag_one";
    String testInputString = "Sample text {" + testTagOne + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(nullPiece);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowExceptionWhenPieceHasKeyNotDefinedInInputString() {
    // given
    String testValue = "test value";
    String notPresentTestTag = "test_tag_NOT_present";
    Piece testPiece = HtmlPiece.put(notPresentTestTag, testValue);
    String testTagOne = "tag_one";
    String testInputString = "Sample text {" + testTagOne + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(testPiece);

    // then
    // throw an exception
  }

  @Test
  public void testPutShouldAddKeyAndValue() {
    // given
    String testKey = "test_tag_present";
    String testValue = "test value";
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";
    String expectedOutPutString = "Sample text " + testValue + " rest of the sentence.";

    // when
    CharSequence generatedCharSequence =
        Kirai.from(testInputString).put(testKey, testValue).format();

    // then
    assertThat(generatedCharSequence).isEqualTo(expectedOutPutString);
  }

  @Test
  public void testPutShouldAddPiece() {
    // given
    String testKey = "test_tag_present";
    String testValue = "test value";
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";
    String expectedOutPutString = "Sample text " + testValue + " rest of the sentence.";

    // when
    CharSequence generatedCharSequence = Kirai.from(testInputString)
        .put(HtmlPiece.put(testKey, testValue))
        .format();

    // then
    assertThat(generatedCharSequence).isEqualTo(expectedOutPutString);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowExceptionWhenKeyIsNotDefinedInInputString() {
    // given
    String presentTestTag = "test_tag_present";
    String notPresentTestTag = "test_tag_NOT_present";
    String testValue = "test value";
    String testInputString = "Sample text {" + presentTestTag + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(notPresentTestTag, testValue);

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowAnExceptionWhenKeyIsAnEmptyString() {
    // given
    String testKey = "";
    String testValue = "test value";
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(testKey, testValue).format();

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowAnExceptionWhenKeyIsNull() {
    // given
    String testKey = null;
    String testValue = "test value";
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(testKey, testValue).format();

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowAnExceptionWhenValueIsAnEmptyString() {
    String testKey = "testKey";
    String testValue = "";
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(testKey, testValue).format();

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPutShouldThrowAnExceptionWhenValueIsNull() {
    String testKey = "testKey";
    String testValue = null;
    String testInputString = "Sample text {" + testKey + "} rest of the sentence.";

    // when
    Kirai.from(testInputString).put(testKey, testValue).format();

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromShouldThrowExceptionWhenKeyStartsFromNumber() {
    // given
    String incorrectTestTag = "1_incorrect_tag";
    String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromShouldThrowExceptionWhenKeyStartsFromUnderscore() {
    // given
    String incorrectTestTag = "_incorrect_tag";
    String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromShouldThrowExceptionWhenKeyStartsFromSpecialCharacter() {
    // given
    String incorrectTestTag = "!_incorrect_tag";
    String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromShouldThrowExceptionWhenKeyContainsSpecialCharacters() {
    // given
    String incorrectTestTag = "#!@ tag &^*_";
    String testInputString = "Sample text {" + incorrectTestTag + "} rest of the sentence.";

    // when
    Kirai.from(testInputString);

    // then
    // throw an exception
  }

  @Test
  public void testFormatterShouldFormatInput() {
    // given
    // formatter below is created for test purposes and it can do anything
    Formatter upperCaseTestFormatter = new Formatter() {
      @Override
      public CharSequence format(String input) {
        return input.toString().toUpperCase();
      }
    };

    String input = "value";
    String expectedOutput = "VALUE";

    // when
    CharSequence output = Kirai.from(input).format(upperCaseTestFormatter);

    // then
    assertThat(output).isEqualTo(expectedOutput);
  }
}