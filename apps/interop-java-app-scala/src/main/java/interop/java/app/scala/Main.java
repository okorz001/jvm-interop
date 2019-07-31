package interop.java.app.scala;

import java.util.Arrays;
import java.util.Collections;

import interop.scala.lib.ScalaBaseClass;
import interop.scala.lib.ScalaLibrary;
import interop.scala.lib.ScalaTraitWithProperty;
import interop.scala.lib.package$;
import interop.scala.lib.ScalaClass;
import interop.scala.lib.ScalaObject;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

public class Main {
    private static final String LANG = "Java";

    private static <T> Seq<T> toSeq(Iterable<T> it) {
        // CollectionConverters provides adapters for Java collections (but not arrays).
        return CollectionConverters.asScala(it).toSeq();
    }

    public static void main(String[] args) {
        // The package methods are available as static methods on the interop.scala.lib.package class.
        // However, "package" is a reserved word in Java, so we cannot import (or even reference) that class.
        // As a workaround, we can access the package object instance in interop.scala.lib.package$.MODULES$
        package$.MODULE$.packageProperty_$eq(LANG);
        System.out.printf("Using Scala package property in %s\n", package$.MODULE$.packageProperty());
        package$.MODULE$.packageMethod(LANG);

        // The ScalaClass companion object instance is available in interop.scala.lib.ScalaClass$.MODULES$
        ScalaClass.companionProperty_$eq(LANG);
        System.out.printf("Using Scala companion property in %s\n", ScalaClass.companionProperty());
        ScalaClass.companionMethod(LANG);

        ScalaClass instance = new ScalaClass();
        instance.instanceProperty_$eq(LANG);
        System.out.printf("Using Scala instance property in %s\n", instance.instanceProperty());
        instance.instanceMethod(LANG);

        // The ScalaObject instance is available in interop.scala.lib.ScalaObject$.MODULES$
        ScalaObject.objectProperty_$eq(LANG);
        System.out.printf("Using Scala object property in %s\n", ScalaObject.objectProperty());
        ScalaObject.objectMethod(LANG);

        // Parameter lists are flattened together.
        ScalaLibrary.multipleParameterLists(LANG, 1);

        // Scala does not generate overloads for default arguments.
        // Java callers must know the default values. Adding new default arguments is a breaking change for Java.
        ScalaLibrary.defaultArguments(LANG, 1, 2);

        // Scala varargs are passed as a Seq.
        ScalaLibrary.varargs(LANG, toSeq(Collections.emptyList()));
        ScalaLibrary.varargs(LANG, toSeq(Collections.singleton(1)));
        ScalaLibrary.varargs(LANG, toSeq(Arrays.asList(1, 2)));
        // @varargs changes it from Seq to array which is much easier for Java callers.
        ScalaLibrary.varargsWithAnnotation(LANG);
        ScalaLibrary.varargsWithAnnotation(LANG, 1);
        ScalaLibrary.varargsWithAnnotation(LANG, 1, 2);
        // @varargs can also be called with an array
        ScalaLibrary.varargsWithAnnotation(LANG, new Object[]{1, 2});

        ScalaLibrary.implementedFunction(() -> LANG);
        ScalaLibrary.implementedCurriedFunction(() -> () -> LANG);
        ScalaLibrary.implementedTrait(() -> LANG);
        ScalaLibrary.implementedTraitWithProperty(LANG, new ScalaTraitWithProperty() {
            private String language;

            @Override
            public String language() {
                return language;
            }

            @Override
            public void language_$eq(String language) {
                this.language = language;
            }
        });
        ScalaLibrary.extendedBaseClass(new ScalaBaseClass() {
            @Override
            public String language() {
                return LANG;
            }
        });
    }
}
