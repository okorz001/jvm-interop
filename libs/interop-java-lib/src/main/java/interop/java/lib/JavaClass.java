package interop.java.lib;

import java.lang.reflect.Executable;
import java.util.Objects;
import java.util.stream.Stream;

public class JavaClass {

    public static String staticField = "";

    public static void staticMethod(String from) {
        System.out.println("Called Java static method from " + from);
    }

    public String instanceField = "";

    public void instanceMethod(String from) {
        System.out.println("Called Java instance method from " + from);
    }
}
