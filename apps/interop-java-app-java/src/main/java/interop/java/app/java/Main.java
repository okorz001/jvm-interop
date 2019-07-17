package interop.java.app.java;

import interop.java.lib.JavaBaseClass;
import interop.java.lib.JavaClass;

public class Main {
    private static final String LANG = "Java";

    public static void main(String[] args) {
        JavaClass.staticField = LANG;
        System.out.printf("Using Java static field in %s\n", JavaClass.staticField);
        JavaClass.setStaticProperty(LANG);
        System.out.printf("Using Java static property in %s\n", JavaClass.getStaticProperty());
        JavaClass.staticMethod(LANG);

        JavaClass instance = new JavaClass();
        instance.instanceField = LANG;
        System.out.printf("Using Java instance field in %s\n", instance.instanceField);
        instance.setInstanceProperty(LANG);
        System.out.printf("Using Java instance property in %s\n", instance.getInstanceProperty());
        instance.instanceMethod(LANG);

        JavaClass.implementedInterface(() -> LANG);
        JavaClass.extendedBaseClass(new JavaBaseClass() {
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
    }
}
