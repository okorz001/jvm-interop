package interop.java.app.groovy;

import interop.groovy.lib.GroovyBaseClass;
import interop.groovy.lib.GroovyClass;
import interop.groovy.lib.GroovyExtensionMethods;
import interop.groovy.lib.GroovyStaticExtensionMethods;

public class Main {
    private static final String LANG = "Java";

    public static void main(String[] args) {
        GroovyClass.setStaticProperty(LANG);
        System.out.printf("Using Groovy static property in %s\n", GroovyClass.getStaticProperty());
        GroovyClass.staticField = LANG;
        System.out.printf("Using Groovy static field in %s\n", GroovyClass.staticField);
        GroovyClass.staticMethod(LANG);
        // If there are multiple static extensions with the same name, then you must cast null to pick one.
        GroovyStaticExtensionMethods.extensionStaticMethod(null, LANG);

        GroovyClass instance = new GroovyClass();
        instance.setInstanceProperty(LANG);
        System.out.printf("Using Groovy instance property in %s\n", instance.getInstanceProperty());
        instance.instanceField = LANG;
        System.out.printf("Using Groovy instance field in %s\n", instance.instanceField);
        instance.instanceMethod(LANG);
        GroovyExtensionMethods.extensionInstanceMethod(instance, LANG);

        GroovyClass.implementedInterface(() -> LANG);
        GroovyClass.implementedTrait(() -> LANG);
        GroovyClass.extendedBaseClass(new GroovyBaseClass() {
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
    }
}
