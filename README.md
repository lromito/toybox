# Toybox

## Description
This project is actually two things in one:
1. A repository of useful code that I don't want to rewrite every time I need it.
2. A playground for trying new tools and languages and experimenting with new stuff.

## Subproject/Modules
(Meaning: currently there are separate Gradle subprojects, but I hope to migrate them soon to actual Java 9+ modules)

* **Utilities**: Basic utilities functions, things that are IMHO missing from the Java standard libraries.
* **Functional**: Some experiments with Java 8 lambdas and adding some missing features like throwing functions and Scala-like `Try` and `Either` classes.
* **Streams**: Like `Functional`, some experiments with `Stream`s and some missing features like `take-`/`dropWhile`.
* **Testing**: Has the `FunctionalAssert` class, which enables to use lambda functions in JUnit 4 tests.

## Further goals:
1. Add _meaningful_ Javadoc documentation. Not documenting for documenting's sake.
2. Add Spock tests to each class.
3. Add some form of continuous integration. Travis CI maybe?
4. Migrate to Java 11, including modularization and `var`s.
