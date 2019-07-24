package interop.java.app.groovy;

import interop.groovy.lib.GroovyBaseClass;
import interop.groovy.lib.GroovyClass;
import interop.groovy.lib.GroovyConstructorAnnotation;
import interop.groovy.lib.GroovyExtensionMethods;
import interop.groovy.lib.GroovyFieldAnnotation;
import interop.groovy.lib.GroovyMethodAnnotation;
import interop.groovy.lib.GroovyParameterAnnotation;
import interop.groovy.lib.GroovyStaticExtensionMethods;
import interop.groovy.lib.GroovyTypeAnnotation;

public class Main {
    private static final String LANG = "Java";

    @GroovyTypeAnnotation(LANG)
    public static class UseGroovyAnnotations {
        @GroovyFieldAnnotation(LANG)
        public int field;

        @GroovyConstructorAnnotation(LANG)
        public UseGroovyAnnotations() {}

        @GroovyMethodAnnotation(LANG)
        public void method() {}

        public void method(@GroovyParameterAnnotation(LANG) int param) {}
    }

    public static void main(String[] args) {
        GroovyClass.setStaticProperty(LANG);
        System.out.printf("Using Groovy static property in %s\n", GroovyClass.getStaticProperty());
        GroovyClass.staticField = LANG;
        System.out.printf("Using Groovy static field in %s\n", GroovyClass.staticField);
        GroovyClass.staticMethod(LANG);
        // If there are multiple static extensions with the same name, then you must cast null to pick one.
        GroovyStaticExtensionMethods.staticExtensionMethod(null, LANG);

        GroovyClass instance = new GroovyClass();
        instance.setInstanceProperty(LANG);
        System.out.printf("Using Groovy instance property in %s\n", instance.getInstanceProperty());
        instance.instanceField = LANG;
        System.out.printf("Using Groovy instance field in %s\n", instance.instanceField);
        instance.instanceMethod(LANG);
        GroovyExtensionMethods.instanceExtensionMethod(instance, LANG);

        GroovyClass.implementedInterface(() -> LANG);
        GroovyClass.implementedTrait(() -> LANG);
        GroovyClass.extendedBaseClass(new GroovyBaseClass() {
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
        GroovyClass.usedTypeAnnotation(new UseGroovyAnnotations());
        GroovyClass.usedConstructorAnnotation(new UseGroovyAnnotations());
        GroovyClass.usedMethodAnnotation(new UseGroovyAnnotations());
        GroovyClass.usedFieldAnnotation(new UseGroovyAnnotations());
        GroovyClass.usedParameterAnnotation(new UseGroovyAnnotations());
    }
}
