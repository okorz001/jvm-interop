package interop.java.app.java;

import interop.java.lib.JavaBaseClass;
import interop.java.lib.JavaCheckedException;
import interop.java.lib.JavaClass;
import interop.java.lib.JavaConstructorAnnotation;
import interop.java.lib.JavaFieldAnnotation;
import interop.java.lib.JavaLibrary;
import interop.java.lib.JavaMethodAnnotation;
import interop.java.lib.JavaParameterAnnotation;
import interop.java.lib.JavaTypeAnnotation;
import interop.java.lib.JavaUncheckedException;

public class Main {
    private static final String LANG = "Java";

    @JavaTypeAnnotation(LANG)
    public static class UseJavaAnnotations {
        @JavaFieldAnnotation(LANG)
        public int field;

        @JavaConstructorAnnotation(LANG)
        public UseJavaAnnotations() {}

        @JavaMethodAnnotation(LANG)
        public void method() {}

        public void method(@JavaParameterAnnotation(LANG) int param) {}
    }

    public static void main(String[] args) {
        JavaClass.staticField = LANG;
        System.out.printf("Used Java static field in %s\n", JavaClass.staticField);
        JavaClass.staticMethod(LANG);

        JavaClass instance = new JavaClass();
        instance.instanceField = LANG;
        System.out.printf("Used Java instance field in %s\n", instance.instanceField);
        instance.instanceMethod(LANG);

        JavaLibrary.varargs(LANG);
        JavaLibrary.varargs(LANG, 1);
        JavaLibrary.varargs(LANG, 1, 2);
        // Varargs can also be called with an array
        JavaLibrary.varargs(LANG, new Object[]{1, 2});

        try {
            JavaLibrary.throwsUnchecked();
        }
        catch (JavaUncheckedException e) {
            System.out.println("Caught Java unchecked exception in Java");
        }

        try {
            JavaLibrary.throwsChecked();
        }
        catch (JavaCheckedException e) {
            System.out.println("Caught Java checked exception in Java");
        }

        JavaLibrary.implementedInterface(() -> LANG);
        JavaLibrary.implementedInterfaceWithMethod(() -> LANG);
        JavaLibrary.extendedBaseClass(new JavaBaseClass() {
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
        JavaLibrary.usedTypeAnnotation(new UseJavaAnnotations());
        JavaLibrary.usedConstructorAnnotation(new UseJavaAnnotations());
        JavaLibrary.usedMethodAnnotation(new UseJavaAnnotations());
        JavaLibrary.usedFieldAnnotation(new UseJavaAnnotations());
        JavaLibrary.usedParameterAnnotation(new UseJavaAnnotations());
    }
}
