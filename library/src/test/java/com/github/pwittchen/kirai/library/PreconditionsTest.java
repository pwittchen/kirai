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

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PreconditionsTest {

  @Test
  public void testShouldInitializePreconditions() {
    Preconditions preconditions = new Preconditions();
    assertThat(preconditions).isNotNull();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringShouldBeEmpty() {
    // given
    String emptyString = "";

    // when
    Preconditions.checkNotEmpty(emptyString, "string is empty");

    // then
    // an exception is thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullStringShouldBeEmpty() {
    // given
    String emptyString = null;

    // when
    Preconditions.checkNotEmpty(emptyString, "string is empty");

    // then
    // an exception is thrown
  }

  @Test
  public void testStringShouldNotBeEmpty() {
    // given
    String notEmptyString = "not empty string";

    // when
    Preconditions.checkNotEmpty(notEmptyString, "string is empty");

    // then
    // no exception is thrown
  }

  @Test
  public void testShouldNotThrowAnyExceptionWhenObjectIsNotNull() {
    // given
    Object object = new Object();

    // when
    Preconditions.checkNotNull(object, "object == null");

    // then
    assertThat(object).isNotNull(); // and no exception is thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowAnExceptionWhenObjectIsNull() {
    // given
    Object nullObject = null;

    // when
    Preconditions.checkNotNull(nullObject, "nullObject == null");

    // then throw an exception
  }
}
