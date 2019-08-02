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
        System.out.printf("Used Scala package property in %s\n", package$.MODULE$.packageProperty());
        // @BeanProperty generates conventional getter/setter.
        package$.MODULE$.setPackageBeanProperty(LANG);
        System.out.printf("Used Scala @BeanProperty package property in %s\n",
                          package$.MODULE$.getPackageBeanProperty());
        // Regular Scala getter/setter are still available
        package$.MODULE$.packageBeanProperty_$eq(LANG);
        System.out.printf("Used Scala @BeanProperty package property in %s\n",
                          package$.MODULE$.packageBeanProperty());
        // @BooleanBeanProperty generates conventional getter/setter for booleans.
        package$.MODULE$.setPackageBooleanBeanProperty(true);
        System.out.printf("Used Scala @BooleanBeanProperty package property in Java: %b\n",
                          package$.MODULE$.isPackageBooleanBeanProperty());
        // Regular Scala getter/setter are still available
        package$.MODULE$.packageBooleanBeanProperty_$eq(true);
        System.out.printf("Used Scala @BooleanBeanProperty package property in Java: %b\n",
                          package$.MODULE$.packageBooleanBeanProperty());
        package$.MODULE$.packageMethod(LANG);

        // The ScalaClass companion object instance is available in interop.scala.lib.ScalaClass$.MODULES$
        ScalaClass.companionProperty_$eq(LANG);
        System.out.printf("Used Scala companion property in %s\n", ScalaClass.companionProperty());
        // @BeanProperty generates conventional getter/setter.
        ScalaClass.setCompanionBeanProperty(LANG);
        System.out.printf("Used Scala @BeanProperty companion property in %s\n",
                          ScalaClass.getCompanionBeanProperty());
        // Regular Scala getter/setter are still available
        ScalaClass.companionBeanProperty_$eq(LANG);
        System.out.printf("Used Scala @BeanProperty companion property in %s\n",
                          ScalaClass.companionBeanProperty());
        // @BooleanBeanProperty generates conventional getter/setter for booleans.
        ScalaClass.setCompanionBooleanBeanProperty(true);
        System.out.printf("Used Scala @BooleanBeanProperty companion property in Java: %b\n",
                          ScalaClass.isCompanionBooleanBeanProperty());
        // Regular Scala getter/setter are still available
        ScalaClass.companionBooleanBeanProperty_$eq(true);
        System.out.printf("Used Scala @BooleanBeanProperty companion property in Java: %b\n",
                          ScalaClass.companionBooleanBeanProperty());
        ScalaClass.companionMethod(LANG);

        ScalaClass instance = new ScalaClass();
        instance.instanceProperty_$eq(LANG);
        System.out.printf("Used Scala instance property in %s\n", instance.instanceProperty());
        // @BeanProperty generates conventional getter/setter.
        instance.setInstanceBeanProperty(LANG);
        System.out.printf("Used Scala @BeanProperty instance property in %s\n",
                          instance.getInstanceBeanProperty());
        // Regular Scala getter/setter are still available
        instance.instanceProperty_$eq(LANG);
        System.out.printf("Used Scala @BeanProperty instance property in %s\n",
                          instance.instanceProperty());
        // @BooleanBeanProperty generates conventional getter/setter for booleans.
        instance.setInstanceBooleanBeanProperty(true);
        System.out.printf("Used Scala @BooleanBeanProperty instance property in Java: %b\n",
                          instance.isInstanceBooleanBeanProperty());
        // Regular Scala getter/setter are still available
        instance.instanceBooleanBeanProperty_$eq(true);
        System.out.printf("Used Scala @BooleanBeanProperty instance property in Java: %b\n",
                          instance.instanceBooleanBeanProperty());
        instance.instanceMethod(LANG);

        // The ScalaObject instance is available in interop.scala.lib.ScalaObject$.MODULES$
        ScalaObject.objectProperty_$eq(LANG);
        System.out.printf("Used Scala object property in %s\n", ScalaObject.objectProperty());
        // @BeanProperty generates conventional getter/setter.
        ScalaObject.setObjectBeanProperty(LANG);
        System.out.printf("Used Scala @BeanProperty object property in %s\n",
                          ScalaObject.getObjectBeanProperty());
        // Regular Scala getter/setter are still available
        ScalaObject.objectBeanProperty_$eq(LANG);
        System.out.printf("Used Scala @BeanProperty object property in %s\n",
                          ScalaObject.objectBeanProperty());
        // @BooleanBeanProperty generates conventional getter/setter for booleans.
        ScalaObject.setObjectBooleanBeanProperty(true);
        System.out.printf("Used Scala @BooleanBeanProperty object property in Java: %b\n",
                          ScalaObject.isObjectBooleanBeanProperty());
        // Regular Scala getter/setter are still available
        ScalaObject.objectBooleanBeanProperty_$eq(true);
        System.out.printf("Used Scala @BooleanBeanProperty object property in Java: %b\n",
                          ScalaObject.objectBooleanBeanProperty());
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
