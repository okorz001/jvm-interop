package interop.java.lib;

import java.lang.reflect.Executable;
import java.util.Objects;
import java.util.stream.Stream;

public class JavaClass {
    public static String staticField = "";
    // Java language has no concept of a property, it's just a programmer convention.
    private static String staticProperty = "";

    public static String getStaticProperty() {
        return staticProperty;
    }

    public static void setStaticProperty(String value) {
        staticProperty = value;
    }

    public static void staticMethod(String from) {
        System.out.println("Calling Java static method from " + from);
    }

    public String instanceField = "";
    private String instanceProperty = "";

    public String getInstanceProperty() {
        return instanceProperty;
    }

    public void setInstanceProperty(String value) {
        instanceProperty = value;
    }

    public void instanceMethod(String from) {
        System.out.println("Calling Java instance method from " + from);
    }

    public static void implementedInterface(JavaInterface it) {
        System.out.println("Implemented Java interface in " + it.getLanguage());
    }

    public static void extendedBaseClass(JavaBaseClass it) {
        System.out.println("Extended Java class in " + it.getLanguage());
    }

    public static void usedTypeAnnotation(Object it) {
        String from = it.getClass().getAnnotation(JavaTypeAnnotation.class).value();
        System.out.println("Used Java type annotation in " + from);
    }

    public static void usedConstructorAnnotation(Object it) {
        String from = Stream.of(it.getClass().getConstructors())
            .map(c -> c.getAnnotation(JavaConstructorAnnotation.class))
            .filter(Objects::nonNull)
            .findFirst()
            .get()
            .value();
        System.out.println("Used Java constructor annotation in " + from);
    }

    public static void usedMethodAnnotation(Object it) {
        String from = Stream.of(it.getClass().getMethods())
            .map(c -> c.getAnnotation(JavaMethodAnnotation.class))
            .filter(Objects::nonNull)
            .findFirst()
            .get()
            .value();
        System.out.println("Used Java method annotation in " + from);
    }

    public static void usedFieldAnnotation(Object it) {
        String from = Stream.of(it.getClass().getFields())
            .map(c -> c.getAnnotation(JavaFieldAnnotation.class))
            .filter(Objects::nonNull)
            .findFirst()
            .get()
            .value();
        System.out.println("Used Java field annotation in " + from);
    }

    public static void usedParameterAnnotation(Object it) {
        String from = Stream.concat(Stream.of(it.getClass().getConstructors()),
                                    Stream.of(it.getClass().getMethods()))
            .map(Executable::getParameters)
            .flatMap(Stream::of)
            .map(p -> p.getAnnotation(JavaParameterAnnotation.class))
            .filter(Objects::nonNull)
            .findFirst()
            .get()
            .value();
        System.out.println("Used Java parameter annotation in " + from);
    }
}
