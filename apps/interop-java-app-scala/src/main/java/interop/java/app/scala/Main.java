package interop.java.app.scala;

import interop.scala.lib.package$;
import interop.scala.lib.ScalaClass;
import interop.scala.lib.ScalaObject;

public class Main {
    private static final String LANG = "Java";

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
    }
}
