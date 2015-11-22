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
package com.github.pwittchen.kirai.library.terminal;

import com.github.pwittchen.kirai.library.Piece;

public class TerminalPiece extends Piece {
  private TerminalPiece(String key, Object value) {
    super(key, value, new TerminalSyntax());
  }

  public static TerminalPiece put(String key, Object value) {
    return new TerminalPiece(key, value);
  }

  public TerminalPiece color(TerminalColor color) {
    value = String.format(syntax.getColorFormat(), color.getCode(), value);
    return this;
  }

  public TerminalPiece background(TerminalBgColor color) {
    value = String.format(syntax.getColorFormat(), color.getCode(), value);
    return this;
  }
}
