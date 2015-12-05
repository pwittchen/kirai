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

public enum TerminalColor {
  DEFAULT(39), WHITE(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), MAGENTA(35), CYAN(36),
  LIGHT_GRAY(37), DARK_GRAY(90), LIGHT_RED(91), LIGHT_GREEN(92), LIGHT_YELLOW(93), LIGHT_BLUE(94),
  LIGHT_MAGENTA(95), LIGHT_CYAN(96), BLACK(97);

  private final int code;

  TerminalColor(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
