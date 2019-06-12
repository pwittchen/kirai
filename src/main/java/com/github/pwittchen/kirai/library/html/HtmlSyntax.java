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
package com.github.pwittchen.kirai.library.html;

import com.github.pwittchen.kirai.library.Syntax;
import com.github.pwittchen.kirai.library.Preconditions;

public final class HtmlSyntax implements Syntax {
  private static final int COLOR_CODE_LENGTH = 7;
  private static final char COLOR_PREFIX = '#';

  private final static String STRONG_FORMAT = "<strong>%s</strong>";
  private final static String ITALIC_FORMAT = "<em>%s</em>";
  private final static String UNDERLINE_FORMAT = "<u>%s</u>";
  private final static String COLOR_FORMAT = "<font color='%s'>%s</font>";
  private final static String BIG_FORMAT = "<big>%s</big>";
  private final static String SMALL_FORMAT = "<small>%s</small>";
  private final static String SUB_FORMAT = "<sub>%s</sub>";
  private final static String SUP_FORMAT = "<sup>%s</sup>";

  @Override
  public String getBoldFormat() {
    return STRONG_FORMAT;
  }

  @Override
  public String getItalicFormat() {
    return ITALIC_FORMAT;
  }

  @Override
  public String getUnderlineFormat() {
    return UNDERLINE_FORMAT;
  }

  @Override
  public String getColorFormat() {
    return COLOR_FORMAT;
  }

  @Override
  public void validateColorCode(String code) {
    Preconditions.checkNotEmpty(code, "color code is empty");

    if (code.length() != COLOR_CODE_LENGTH) {
      throw new IllegalArgumentException(
          "Hex value have to contain "
              + COLOR_CODE_LENGTH
              + " characters including hash sign ("
              + COLOR_PREFIX
              + ")");
    }

    if (code.charAt(0) != COLOR_PREFIX) {
      throw new IllegalArgumentException(
          "Hex value have to start from hash sign (" + COLOR_PREFIX + ")");
    }
  }

  @Override
  public String getBigFormat() {
    return BIG_FORMAT;
  }

  @Override
  public String getSmallFormat() {
    return SMALL_FORMAT;
  }

  @Override
  public String getSubFormat() {
    return SUB_FORMAT;
  }

  @Override
  public String getSupFormat() {
    return SUP_FORMAT;
  }
}
