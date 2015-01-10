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
package pwittchen.com.kirai.library;

import android.text.Html;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kirai ("phrase" in Swahili language) - flavored Android string formatting library
 * A fluent API for formatting Strings. Canonical usage:
 * Basic:
 * <pre>
 *   CharSequence formatted = Kirai.from("Hi {first_name}, you are {age} years old.")
 *       .put("first_name", firstName)
 *       .put("age", age)
 *       .format();
 * </pre>
 * Flavored:
 * <pre>
 *   CharSequence formatted = Kirai.from("Hi {first_name}, you are {age} years old.")
 *       .put(Piece.put("first_name", firstName).bold().italic().big())
 *       .put(Piece.put("age", age).underline().color("#FF0000"))
 *       .format();
 * </pre>
 * <ul>
 * <li>Surround keys with curly braces</li>
 * <li>Keys start with lowercase letters followed by lowercase letters and underscores.</li>
 * <li>Can be formatted for TextView (bold, italic, underline, big, small, sub, sup).</li>
 * <li>Can be colored for TextView with HEX value followed by hash sign.</li>
 * <li>Fails on any mismatched keys.</li>
 * <li>Fails on not balanced braces in input.</li>
 * <li>Fails on incorrect, empty or null input data.</li>
 * </ul>
 */
public final class Kirai {
    private final static char BRACE_START = '{';
    private final static char BRACE_END = '}';
    private final static String REGEX_VALID_TAG = "[a-z]{1}[a-z0-9_]*";
    private final static Pattern TAG_REGEX = Pattern.compile("\\" + BRACE_START + "(.+?)\\" + BRACE_END);
    private static String input;
    private List<Piece> pieces = new ArrayList<>();
    private List<String> tags;

    private Kirai(String string) {
        input = string;

        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        if (!isInputBalanced()) {
            throw new IllegalArgumentException("Braces in provided string are not balanced");
        }

        if (!areTagsValid()) {
            throw new IllegalArgumentException("Tags have to start from lower case letter and can contain only lower case letters [a-z] numbers [0-9] and underscore [_]");
        }
    }

    public static Kirai from(String string) {
        return new Kirai(string);
    }

    public Kirai put(String key, Object value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(String.valueOf(value))) {
            throw new IllegalArgumentException("Key and value cannot be null or empty");
        }

        if (!tags.contains(key)) {
            throw new IllegalArgumentException("Tag {" + key + "} was not defined in input string");
        }

        input = input.replace(BRACE_START + key + BRACE_END, String.valueOf(value));
        return this;
    }

    public Kirai put(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece object cannot be null");
        }

        if (!tags.contains(piece.getKey())) {
            throw new IllegalArgumentException("Tag {" + piece.getKey() + "} was not defined in input string");
        }

        pieces.add(piece);
        return this;
    }

    public CharSequence format() {
        if (pieces.isEmpty()) {
            return input;
        }

        for (Piece piece : pieces) {
            input = input.replace(BRACE_START + piece.getKey() + BRACE_END, String.valueOf(piece.getValue()));
        }

        return Html.fromHtml(input);
    }

    /**
     * Checks whether input string is balanced
     * Analyzes only curly braces like { and }
     * and ignores other characters
     *
     * @return true if string is balanced
     */
    private boolean isInputBalanced() {
        Stack<Character> stack = new Stack<>();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char currentChar = input.charAt(i);
            if (currentChar == BRACE_START) {
                stack.push(currentChar);
            } else if (currentChar == BRACE_END) {
                if (stack.empty()) return false;
                if (stack.pop() != BRACE_START) return false;
            }
        }
        return stack.empty();
    }

    private boolean areTagsValid() {
        tags = getTags(input);
        for (String tag : tags) {
            if (tag.matches(REGEX_VALID_TAG)) continue;
            return false;
        }
        return true;
    }

    private static List<String> getTags(final String string) {
        final List<String> tags = new ArrayList<>();
        final Matcher matcher = TAG_REGEX.matcher(string);
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }
        return tags;
    }
}
