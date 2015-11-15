Kirai - flavored string formatting
===================================

[![Build Status](https://travis-ci.org/pwittchen/kirai.svg?branch=master)](https://travis-ci.org/pwittchen/kirai)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kirai-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1391) ![Maven Central](https://img.shields.io/maven-central/v/com.github.pwittchen.kirai/library.svg?style=flat)

Kirai means *phrase* in Swahili language.

Project is inspired by [phrase](https://github.com/square/phrase), [TaggerString](https://github.com/polok/TaggerString) and [BabushkaText](https://github.com/quiqueqs/BabushkaText).
Kirai has fluent API similar to phrase with additional formatting similar to TaggerString and allows to add formatted pieces of text like BabushkaText.

JavaDoc is available at: http://pwittchen.github.io/kirai/

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
  .format(new Formatter() {
    @Override public CharSequence format(String input) {
      return Html.fromHtml(input);
    }
  }
```

### In Android TextView

```java
textView.setText(formatted);
```

Download
--------

You can depend on the library through Maven:

```xml
<dependency>
    <groupId>com.github.pwittchen.kirai</groupId>
    <artifactId>library</artifactId>
    <version>1.2.0</version>
</dependency>
```

or through Gradle:

```groovy
dependencies {
  compile 'com.github.pwittchen.kirai:library:1.2.0'
}
```

Tests
-----

Unit Tests are available in `library/src/test` directory. They don't have any dependencies to Android API and can be run from Android Studio or CLI with Gradle without any emulator or attached device thanks to enabled [Unit Testing Support](http://tools.android.com/tech-docs/unit-testing-support). Tests were written according to TDD methodology. They determine library specification and check if project is fault-tolerant.

Code style
----------

Code style used in the project is called `Square` from Java Code Styles repository by Square available at: https://github.com/square/java-code-styles.

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
