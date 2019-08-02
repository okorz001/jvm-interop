package interop.java.app.groovy;

import java.util.Collections;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import interop.groovy.lib.GroovyBaseClass;
import interop.groovy.lib.GroovyCategory;
import interop.groovy.lib.GroovyClass;
import interop.groovy.lib.GroovyConstructorAnnotation;
import interop.groovy.lib.GroovyExtensionMethods;
import interop.groovy.lib.GroovyFieldAnnotation;
import interop.groovy.lib.GroovyLibrary;
import interop.groovy.lib.GroovyMethodAnnotation;
import interop.groovy.lib.GroovyParameterAnnotation;
import interop.groovy.lib.GroovyStaticExtensionMethods;
import interop.groovy.lib.GroovyTraitWithMethod;
// IntelliJ highlights this as missing, but still compiles and executes without error.
import interop.groovy.lib.GroovyTraitWithMethod$Trait$Helper;
import interop.groovy.lib.GroovyTraitWithProperty;
import interop.groovy.lib.GroovyTypeAnnotation;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

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

    // dumb workaround for GroovyObject methods being synthetic in 2.5.x
    // https://issues.apache.org/jira/browse/GROOVY-8497
    private static GroovyObject asPOGO(GroovyObject it) {
        return it;
    }

    public static void main(String[] args) {
        GroovyClass.setStaticProperty(LANG);
        System.out.printf("Used Groovy static property in %s\n", GroovyClass.getStaticProperty());
        GroovyClass.staticField = LANG;
        System.out.printf("Used Groovy static field in %s\n", GroovyClass.staticField);
        // Dynamic properties are accessed through the MetaClass.
        // Since Class is a Java object, it does not have getMetaClass.
        DefaultGroovyMethods.getMetaClass(GroovyClass.class)
            .setProperty(GroovyClass.class, "staticDynamicProperty", LANG);
        System.out.printf("Used Groovy static dynamic property in %s\n",
                          DefaultGroovyMethods.getMetaClass(GroovyClass.class)
                              .getProperty(GroovyClass.class, "staticDynamicProperty"));
        GroovyClass.staticMethod(LANG);
        // If there are multiple static extensions with the same name, then you must cast null to pick one.
        GroovyStaticExtensionMethods.staticExtensionMethod(null, LANG);
        // Dynamic methods are accessed through the MetaClass.
        // Since Class is a Java object, it does not have getMetaClass.
        DefaultGroovyMethods.getMetaClass(GroovyClass.class)
            .invokeStaticMethod(GroovyClass.class, "staticDynamicMethod", new Object[]{LANG});

        GroovyClass instance = new GroovyClass();
        instance.setInstanceProperty(LANG);
        System.out.printf("Used Groovy instance property in %s\n", instance.getInstanceProperty());
        instance.instanceField = LANG;
        System.out.printf("Used Groovy instance field in %s\n", instance.instanceField);
        // Dynamic properties are accessed through the MetaClass.
        // GroovyObject's getProperty and setProperty handles this for us.
        asPOGO(instance).setProperty("instanceDynamicProperty", LANG);
        System.out.printf("Used Groovy instance dynamic property in %s\n",
                          asPOGO(instance).getProperty("instanceDynamicProperty"));
        instance.instanceMethod(LANG);
        GroovyExtensionMethods.instanceExtensionMethod(instance, LANG);
        // Dynamic methods are accessed through the MetaClass.
        // GroovyObject's invokeMethod handles this for us.
        asPOGO(instance).invokeMethod("staticDynamicMethod", new Object[]{LANG});
        GroovyCategory.categoryMethod(instance, LANG);

        // Default arguments are implemented as overloads.
        GroovyLibrary.defaultArguments(LANG, 1, 2);
        GroovyLibrary.defaultArguments(LANG, 1);
        GroovyLibrary.defaultArguments(LANG);

        // Named parameters are passed as a map in the first position (just like in the method declaration).
        // [a: 1, b: 2] is an exercise for the reader (too verbose in Java!)
        GroovyLibrary.namedParameters(Collections.singletonMap("a", 1), LANG);
        GroovyLibrary.namedParameters(Collections.singletonMap("b", 2), LANG);
        GroovyLibrary.namedParameters(Collections.emptyMap(), LANG);
        GroovyLibrary.namedParameters(LANG);

        GroovyLibrary.varargs(LANG);
        GroovyLibrary.varargs(LANG,1);
        GroovyLibrary.varargs(LANG, 1, 2);
        // Varargs can also be called with an array
        GroovyLibrary.varargs(LANG, new Object[]{1, 2});

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
        // Java implementation must implement the getter/setter.
        GroovyLibrary.implementedTraitWithProperty(LANG, new GroovyTraitWithProperty() {
            private String language = "";

            @Override
            public String getLanguage() {
                return language;
            }

            @Override
            public void setLanguage(final String language) {
                this.language = language;
            }
        });
        GroovyLibrary.implementedTraitWithMethod(new GroovyTraitWithMethod() {
            @Override
            public String getLanguage() {
                return LANG;
            }

            // Java classes must implement all trait methods
            @Override
            public void traitMethod() {
                // Non-abstract trait methods are in a static $Trait$Helper class.
                // The trait instance is passed as the first argument.
                GroovyTraitWithMethod$Trait$Helper.traitMethod(this);
            }
        });
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
