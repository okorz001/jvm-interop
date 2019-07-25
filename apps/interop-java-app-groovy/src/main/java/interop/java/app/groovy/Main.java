package interop.java.app.groovy;

import groovy.lang.Closure;
import interop.groovy.lib.GroovyBaseClass;
import interop.groovy.lib.GroovyClass;
import interop.groovy.lib.GroovyConstructorAnnotation;
import interop.groovy.lib.GroovyExtensionMethods;
import interop.groovy.lib.GroovyFieldAnnotation;
import interop.groovy.lib.GroovyLibrary;
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

        // Use null for owner since we have no instance in a static method.
        GroovyLibrary.implementedClosure(new Closure<String>(null){
            @SuppressWarnings("unused") // called by reflection
            public String doCall() {
                return LANG;
            }
        });
        GroovyLibrary.implementedClosureWithDelegate(new Closure<Void>(null){
            @SuppressWarnings("unused") // called by reflection
            public void doCall() {
                Closure message = (Closure) getProperty("message");
                message.call(LANG);
            }
        });
        GroovyLibrary.implementedInterface(() -> LANG);
        GroovyLibrary.implementedTrait(() -> LANG);
        GroovyLibrary.extendedBaseClass(new GroovyBaseClass() {
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
        GroovyLibrary.usedTypeAnnotation(new UseGroovyAnnotations());
        GroovyLibrary.usedConstructorAnnotation(new UseGroovyAnnotations());
        GroovyLibrary.usedMethodAnnotation(new UseGroovyAnnotations());
        GroovyLibrary.usedFieldAnnotation(new UseGroovyAnnotations());
        GroovyLibrary.usedParameterAnnotation(new UseGroovyAnnotations());
    }
}
