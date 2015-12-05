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

import com.github.pwittchen.kirai.library.Syntax;
import com.github.pwittchen.kirai.library.Preconditions;

public class TerminalSyntax implements Syntax {
  private final static String STRONG_FORMAT = "\033[1m%s\033[21m";
  private final static String UNDERLINE_FORMAT = "\033[39;4m%s\033[0m";
  private final static String COLOR_FORMAT = "\033[%sm%s\033[0m";

  @Override
  public String getBoldFormat() {
    return STRONG_FORMAT;
  }

  @Override
  public String getItalicFormat() {
    throw new UnsupportedOperationException("italic format is currently not supported "
        + "in TerminalSyntax");
  }

  @Override
  public String getUnderlineFormat() {
    return UNDERLINE_FORMAT;
  }

  @Override
  public String getColorFormat() {
    return COLOR_FORMAT;
  }

  /**
   * Validates terminal color code For reference check website:
   * http://misc.flogisoft.com/bash/tip_colors_and_formatting
   *
   * @param code numeric value as a String
   */
  @Override
  public void validateColorCode(String code) {
    Preconditions.checkNotEmpty(code, "color code is empty");
    Integer numericColorCode = Integer.valueOf(code);
    boolean isColorCodeValid = numericColorCode > 0 && numericColorCode < 257;
    if (!isColorCodeValid) {
      throw new IllegalArgumentException("color code should be a number between 1 and 256");
    }
  }

  @Override
  public String getBigFormat() {
    throw new UnsupportedOperationException("big format is currently not supported "
        + "in TerminalSyntax");
  }

  @Override
  public String getSmallFormat() {
    throw new UnsupportedOperationException("small format is currently not supported "
        + "in TerminalSyntax");
  }

  @Override
  public String getSubFormat() {
    throw new UnsupportedOperationException("sub format is currently not supported "
        + "in TerminalSyntax");
  }

  @Override
  public String getSupFormat() {
    throw new UnsupportedOperationException("sup format is currently not supported "
        + "in TerminalSyntax");
  }
}
