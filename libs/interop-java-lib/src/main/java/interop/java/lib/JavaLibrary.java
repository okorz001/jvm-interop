package interop.java.lib;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class JavaLibrary {
    public static void varargs(String from, Object... args) {
        System.out.printf("Called Java method with varargs from %s with %s\n", from, Arrays.asList(args));
    }

    public static void implementedInterface(JavaInterface it) {
        System.out.println("Implemented Java interface in " + it.getLanguage());
    }

    public static void implementedInterfaceWithMethod(JavaInterfaceWithMethod it) {
        it.interfaceMethod();
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
