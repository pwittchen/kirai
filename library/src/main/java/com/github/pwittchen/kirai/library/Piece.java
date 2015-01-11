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

import android.text.TextUtils;

public class Piece {
    public final static String STRONG_FORMAT = "<strong>%s</strong>";
    public final static String ITALIC_FORMAT = "<em>%s</em>";
    public final static String UNDERLINE_FORMAT = "<u>%s</u>";
    public final static String COLOR_FORMAT = "<font color='%s'>%s</font>";
    public final static String BIG_FORMAT = "<big>%s</big>";
    public final static String SMALL_FORMAT = "<small>%s</small>";
    public final static String SUB_FORMAT = "<sub>%s</sub>";
    public final static String SUP_FORMAT = "<sup>%s</sup>";
    private String key;
    private Object value;

    private Piece(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static Piece put(String key, Object value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(String.valueOf(value))) {
            throw new IllegalArgumentException("Key and value cannot be null or empty");
        }
        return new Piece(key, value);
    }

    public Piece bold() {
        value = String.format(STRONG_FORMAT, value);
        return this;
    }

    public Piece italic() {
        value = String.format(ITALIC_FORMAT, value);
        return this;
    }

    public Piece underline() {
        value = String.format(UNDERLINE_FORMAT, value);
        return this;
    }

    public Piece big() {
        value = String.format(BIG_FORMAT, value);
        return this;
    }

    public Piece small() {
        value = String.format(SMALL_FORMAT, value);
        return this;
    }

    public Piece sub() {
        value = String.format(SUB_FORMAT, value);
        return this;
    }

    public Piece sup() {
        value = String.format(SUP_FORMAT, value);
        return this;
    }

    public Piece color(String hex) {
        if (TextUtils.isEmpty(hex)) {
            throw new IllegalArgumentException("Hex value of the color cannot be null or empty");
        }

        if (hex.length() != 7) {
            throw new IllegalArgumentException("Hex value have to contain 7 characters including hash sign (#)");
        }

        if (hex.charAt(0) != '#') {
            throw new IllegalArgumentException("Hex value have to start from hash sign (#)");
        }

        value = String.format(COLOR_FORMAT, hex, value);
        return this;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
