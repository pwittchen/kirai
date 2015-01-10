Kirai - flavored Android string formatting
==========================================

[![Build Status](https://travis-ci.org/pwittchen/kirai.svg?branch=master)](https://travis-ci.org/pwittchen/kirai)

Kirai means *phrase* in Swahili language.

Project is inspired by [phrase](https://github.com/square/phrase), [TaggerString](https://github.com/polok/TaggerString) and [BabushkaText](https://github.com/quiqueqs/BabushkaText).
Kirai has fluent API similar to phrase with additional formatting similar to TaggerString and allows to add formatted pieces of text like BabushkaText.

Usage
-----

### Basic

```java
CharSequence formatted = Kirai
   .from("Hi {first_name}, your are {age} years old.")
   .put("first_name", firstName)
   .put("age", age)
   .format();
```

### Flavored

```java
CharSequence formatted = Kirai
   .from("Hi {first_name}, your are {age} years old.")
   .put(Piece.put("first_name", firstName).bold().italic().big())
   .put(Piece.put("age", age).underline().color("#FF0000"))
   .format();
```

### In TextView

```java
textView.setText(formatted);
```

Tests
-----

Unit Tests are available in `androidTest` directories. We need launched and attached Android device or emulator in order to run them.

License
-------

    Copyright 2015 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
