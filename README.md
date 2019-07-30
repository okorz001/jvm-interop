# jvm-interop

JVM interop examples

## What is Interop?

>in·ter·op·er·a·bil·i·ty
>
>/ˌin(t)ərˌäp(ə)rəˈbilədē/
>
>noun
>
>the ability of computer systems or software to exchange and make use of
>information.

## Repository Goals

Demonstrate creation of distributable executable JARs from language X. These
JARs depend solely on a JVM (language X does not need to installed).

Demonstrate use of language Y from language X:
  1. Calling methods and using classes from language Y directly.
  2. Implementing interfaces/extending classes from language Y in language X
    (e.g. callback flows).

Generally, this just requires understanding of how
[class files](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html)
are created for a given language.

For reference, there are also examples of "interop" where X = Y.

### _NOT_ Repository Goals

Demonstrate all features of language X. Only API-visible features will be
demonstrated, since these are relevant for interop.

## Languages

This repository currently covers:
* Java
* Groovy
* Kotlin
* Scala

## Build

This repository builds with Maven. There's a wrapper so you do not have to
pre-install Maven.

The following command will build all modules. The application builds also
execute the applications, verifying the interop.

```
$ ./mvnw verify
```

### Why Maven?

This repository needs a single build tool for all languages to handle
dependencies in a sane manner, and Maven has decent support for almost
everything JVM-related.

Also Maven is enterprisey and if you need to do interop, then you're probably
enterprisey too.
