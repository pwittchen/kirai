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

import com.github.pwittchen.kirai.library.Preconditions;
import com.github.pwittchen.kirai.library.Syntax;

public class TerminalSyntax implements Syntax {
  private final static String ERROR_MSG_FORMAT = "%s format is currently not supported in Terminal";
  private final static String STRONG_FORMAT = "\033[1m%s\033[21m";
  private final static String UNDERLINE_FORMAT = "\033[39;4m%s\033[0m";
  private final static String COLOR_FORMAT = "\033[%sm%s\033[0m";

  @Override
  public String getBoldFormat() {
    return STRONG_FORMAT;
  }

  @Override
  public String getItalicFormat() {
    throw new UnsupportedOperationException(String.format(ERROR_MSG_FORMAT, "italic"));
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
    throw new UnsupportedOperationException(String.format(ERROR_MSG_FORMAT, "big"));
  }

  @Override
  public String getSmallFormat() {
    throw new UnsupportedOperationException(String.format(ERROR_MSG_FORMAT, "small"));
  }

  @Override
  public String getSubFormat() {
    throw new UnsupportedOperationException(String.format(ERROR_MSG_FORMAT, "sub"));
  }

  @Override
  public String getSupFormat() {
    throw new UnsupportedOperationException(String.format(ERROR_MSG_FORMAT, "sup"));
  }
}
