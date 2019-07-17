package interop.java.lib;

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
}
