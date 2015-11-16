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

public abstract class Piece {
  private final Syntax syntax;
  private final String key;
  private Object value;

  public Piece(String key, Object value, Syntax syntax) {
    validatePair(key, value);
    Utils.checkNotNull(syntax, "syntax == null");
    this.key = key;
    this.value = value;
    this.syntax = syntax;
  }

  private static void validatePair(String key, Object value) {
    if (Utils.isEmpty(key) || Utils.isEmpty(String.valueOf(value))) {
      throw new IllegalArgumentException("Key and value cannot be null or empty");
    }
  }

  public Piece bold() {
    value = String.format(syntax.getBoldFormat(), value);
    return this;
  }

  public Piece italic() {
    value = String.format(syntax.getItalicFormat(), value);
    return this;
  }

  public Piece underline() {
    value = String.format(syntax.getUnderlineFormat(), value);
    return this;
  }

  public Piece big() {
    value = String.format(syntax.getBigFormat(), value);
    return this;
  }

  public Piece small() {
    value = String.format(syntax.getSmallFormat(), value);
    return this;
  }

  public Piece sub() {
    value = String.format(syntax.getSubFormat(), value);
    return this;
  }

  public Piece sup() {
    value = String.format(syntax.getSupFormat(), value);
    return this;
  }

  public Piece color(String code) {
    syntax.validateColorCode(code);
    value = String.format(syntax.getColorFormat(), code, value);
    return this;
  }

  public String getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }
}
