Kirai
=====

[![Build Status](https://travis-ci.org/pwittchen/kirai.svg?branch=master)](https://travis-ci.org/pwittchen/kirai)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kirai-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1391) ![Maven Central](https://img.shields.io/maven-central/v/com.github.pwittchen.kirai/library.svg?style=flat) [![codecov.io](https://codecov.io/github/pwittchen/kirai/coverage.svg?branch=master)](https://codecov.io/github/pwittchen/kirai?branch=master)

Kirai means *phrase* in Swahili language. It's string formatting library for Java, Android, Web and Unix Terminal.

Project is inspired by [phrase](https://github.com/square/phrase), [TaggerString](https://github.com/polok/TaggerString) and [BabushkaText](https://github.com/quiqueqs/BabushkaText).
Kirai has fluent API similar to phrase with additional formatting similar to TaggerString and allows to add formatted pieces of text like BabushkaText.

JavaDoc is available at: http://pwittchen.github.io/kirai/

Library is compatible with Java 1.7 and higher. It should work with Java 1.6 and is compatible with Android applications.

Contents
--------
- [Usage](#usage)
  - [Basic](#basic)
  - [Android](#android)
  - [Web](#web)
  - [Terminal](#terminal)
- [Download](#download)
- [Building project](#building-project)
- [Tests](#tests)
- [Static Code Analysis](#static-code-analysis)
- [Code style](#code-style)
- [License](#license)

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

### Android

```java
CharSequence formatted = Kirai
  .from("Hi {first_name}, your are {age} years old.")
  .put(HtmlPiece.put("first_name", firstName).bold().italic().big())
  .put(HtmlPiece.put("age", age).underline().color("#FF0000"))
  .format(new Formatter() {
    @Override public CharSequence format(String input) {
      return Html.fromHtml(input);
    }
  });
```

Code above will generate formatted text and can be used in **Android TextView** as follows:

```java
textView.setText(formatted);
```

### Web

```java
CharSequence formatted = Kirai
  .from("Hi {first_name}, your are {age} years old.")
  .put(HtmlPiece.put("first_name", firstName).bold().italic().big())
  .put(HtmlPiece.put("age", age).underline().color("#FF0000"))
  .format();
```

Code above will generate text formatted with **HTML tags**.

### Terminal

```java
CharSequence formatted = Kirai
  .from("Hi {first_name}, your are {age} years old.")
  .put(TerminalPiece.put("first_name", firstName).background(TerminalBgColor.DARK_GRAY).bold())
  .put(TerminalPiece.put("age", age).color(TerminalColor.CYAN).underline())
  .format();
```

Code above will generate formatted text ready to display in **Unix terminal** as follows:

```java
System.out.println(formatted);
```

instead of `TerminalColor` and `TerminalBgColor` enums we can pass color code as a string to `color(string)` method and it will work as well. We can use it for setting foreground and background color. For the reference of color codes take a look at http://misc.flogisoft.com/bash/tip_colors_and_formatting website.

Download
--------

You can depend on the library through Maven:

```xml
<dependency>
    <groupId>com.github.pwittchen.kirai</groupId>
    <artifactId>library</artifactId>
    <version>1.4.1</version>
</dependency>
```

or through Gradle:

```groovy
dependencies {
  compile 'com.github.pwittchen.kirai:library:1.4.1'
}
```

Building project
----------------

To build project, run the following command:

```
./gradlew build
```

Tests
-----

Unit Tests are available in `library/src/test` directory. They can be run from IntelliJ IDEA or CLI with Gradle Wrapper. Tests were written according to TDD methodology. They determine library specification and check if project is fault-tolerant. Code Coverage is monitored by [codecov.io](https://codecov.io/github/pwittchen/kirai?branch=master) integrated with Travis CI.

To execute tests, run the following command:

```
./gradlew test
```

To generate code coverage report, run the following command:

```
./gradlew jacocoTestReport
```

All reports are generated in `build/reports/` directory.

Static Code Analysis
--------------------

Project has Static Code Analysis configured in `build.gradle` file. It consists of CheckStyle, PMD and FindBugs.

Static Code Analysis can be executed with the following command:

```
./gradlew check
```

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
