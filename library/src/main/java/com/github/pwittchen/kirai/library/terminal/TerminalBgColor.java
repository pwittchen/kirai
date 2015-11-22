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

public enum TerminalBgColor {
  DEFAULT(49), WHITE(40), RED(41), GREEN(42), YELLOW(43), BLUE(44), MAGENTA(45), CYAN(46),
  LIGHT_GRAY(47), DARK_GRAY(100), LIGHT_RED(101), LIGHT_GREEN(102), LIGHT_YELLOW(103),
  LIGHT_BLUE(104), LIGHT_MAGENTA(105), LIGHT_CYAN(106), BLACK(107);

  private final int code;

  TerminalBgColor(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
