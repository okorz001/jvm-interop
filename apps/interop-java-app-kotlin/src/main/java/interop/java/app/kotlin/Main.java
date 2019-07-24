package interop.java.app.kotlin;

import interop.kotlin.lib.KotlinBaseClass;
import interop.kotlin.lib.KotlinClass;
import interop.kotlin.lib.KotlinLibraryKt;
import interop.kotlin.lib.KotlinObject;

import org.jetbrains.annotations.NotNull;

public class Main {
    private static final String LANG = "Java";

    public static void main(String[] args) {
        KotlinLibraryKt.setPackageProperty(LANG);
        System.out.printf("Using Kotlin package property in %s\n", KotlinLibraryKt.getPackageProperty());
        KotlinLibraryKt.packageFunction(LANG);

        KotlinClass.Companion.setCompanionProperty(LANG);
        System.out.printf("Using Kotlin companion property in %s\n", KotlinClass.Companion.getCompanionProperty());
        KotlinClass.setStaticProperty(LANG);
        System.out.printf("Using Kotlin @JvmStatic companion property in %s\n", KotlinClass.getStaticProperty());
        KotlinClass.staticField = LANG;
        System.out.printf("Using Kotlin companion field in %s\n", KotlinClass.staticField);
        KotlinClass.Companion.companionMethod(LANG);
        KotlinClass.staticMethod(LANG);

        KotlinClass instance = new KotlinClass();
        instance.setInstanceProperty(LANG);
        System.out.printf("Using Kotlin instance property in %s\n", instance.getInstanceProperty());
        instance.instanceField = LANG;
        System.out.printf("Using Kotlin instance field in %s\n", instance.instanceField);
        instance.instanceMethod(LANG);
        KotlinLibraryKt.extensionMethod(instance, LANG);

        KotlinObject.INSTANCE.setObjectProperty(LANG);
        System.out.printf("Using Kotlin object property in %s\n", KotlinObject.INSTANCE.getObjectProperty());
        KotlinObject.setStaticProperty(LANG);
        System.out.printf("Using Kotlin @JvmStatic object property in %s\n", KotlinObject.getStaticProperty());
        KotlinObject.staticField = LANG;
        System.out.printf("Using Kotlin object field in %s\n", KotlinObject.staticField);
        KotlinObject.INSTANCE.objectMethod(LANG);
        KotlinObject.staticMethod(LANG);

        KotlinLibraryKt.implementedFunction(() -> LANG);
        KotlinLibraryKt.implementedFunctionWithReceiver(it -> LANG);
        KotlinLibraryKt.implementedInterface(() -> LANG);
        KotlinLibraryKt.extendedBaseClass(new KotlinBaseClass() {
            @NotNull
            @Override
            public String getLanguage() {
                return LANG;
            }
        });
    }
}
