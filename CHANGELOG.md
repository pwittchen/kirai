CHANGELOG
=========

v. 1.4.0
--------
*22 Nov 2015*

- added support for formatting text in Unix terminal (bold, underline, color and background color)
- created separate packages for classes responsible for HTML formatting and terminal formatting
- added `TerminalColor` and `TerminalBgColor` enums with predefined terminal color codes
- updated `README.md` and prepared more code samples
- added test coverage with codecov.io

v. 1.3.1
--------
*21 Nov 2015*

Changed `syntax`, `key` and `value` fields from `private` to `protected` in `Piece` class.

v. 1.3.0
--------
*18 Nov 2015*

- `Piece` is now an abstract class
- created `HtmlPiece` extending Piece `class`, which uses `HtmlSyntax` class
- updated documentation and tests

v. 1.2.0
--------
*15 Nov 2015*

- changed library type from `aar` (Android packaging) to `jar` (pure Java packaging)

v. 1.1.0
--------
*06 Nov 2015*

- removed `formatter(...)` method from `Kirai` class
- added `format(...)` method accepting implementation of `Formatter` interface to `Kirai` class
- added `Syntax` interface and `HtmlSyntax` class implementing this interface
- added `put(String key, Object value, Syntax syntax)` method to `Piece` class
- set `HtmlSyntax` as default `Syntax` implementation in `Piece` class
- removed dependencies to Android SDK
- updated project dependencies
- applied `Square` code style
- updated tests, sample app and code snippets in `README.md`
- added gh-pages with JavaDoc

v. 1.0.1
--------
*03 Apr 2015*

- updated existing unit tests to JUnit4
- added Google Truth library for test assertions
- added more unit tests
- added `Kirai formatter(Formatter formatter)` method in `Kirai` class for setting custom `Formatter` implementation.

v. 1.0.0
--------
*12 Jan 2015*

First version of the library released to Maven Central Repository.
