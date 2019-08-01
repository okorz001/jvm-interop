package interop.java.app.kotlin;

import interop.kotlin.lib.KotlinBaseClass;
import interop.kotlin.lib.KotlinClass;
import interop.kotlin.lib.KotlinClassAnnotation;
import interop.kotlin.lib.KotlinConstructorAnnotation;
import interop.kotlin.lib.KotlinFieldAnnotation;
import interop.kotlin.lib.KotlinFunctionAnnotation;
import interop.kotlin.lib.KotlinLibraryKt;
import interop.kotlin.lib.KotlinObject;
import interop.kotlin.lib.KotlinPropertyGetterAnnotation;
import interop.kotlin.lib.KotlinPropertySetterAnnotation;
import interop.kotlin.lib.KotlinValueParameterAnnotation;
import org.jetbrains.annotations.NotNull;

public class Main {
    private static final String LANG = "Java";

    @KotlinClassAnnotation(from = LANG)
    public static class UseKotlinAnnotations {
        @KotlinFieldAnnotation(from = LANG)
        public int field;

        @KotlinConstructorAnnotation(from = LANG)
        public UseKotlinAnnotations() {}

        @KotlinFunctionAnnotation(from = LANG)
        public void method() {}

        public void method(@KotlinValueParameterAnnotation(from = LANG) int param) {}

        @KotlinPropertyGetterAnnotation(from = LANG)
        public int getProperty() {
            return field;
        }

        @KotlinPropertySetterAnnotation(from = LANG)
        public void setProperty(int field) {
            this.field = field;
        }
    }

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
        KotlinLibraryKt.setCompanionExtensionProperty(KotlinClass.Companion, LANG);
        System.out.printf("Using Kotlin companion extension property in %s\n",
                          KotlinLibraryKt.getCompanionExtensionProperty(KotlinClass.Companion));
        KotlinClass.Companion.companionMethod(LANG);
        KotlinClass.staticMethod(LANG);
        KotlinLibraryKt.companionExtensionFunction(KotlinClass.Companion, LANG);

        KotlinClass instance = new KotlinClass();
        instance.setInstanceProperty(LANG);
        System.out.printf("Using Kotlin instance property in %s\n", instance.getInstanceProperty());
        instance.instanceField = LANG;
        System.out.printf("Using Kotlin instance field in %s\n", instance.instanceField);
        KotlinLibraryKt.setInstanceExtensionProperty(instance, LANG);
        System.out.printf("Using Kotlin instance extension property in %s\n",
                          KotlinLibraryKt.getInstanceExtensionProperty(instance));
        instance.instanceMethod(LANG);
        KotlinLibraryKt.instanceExtensionFunction(instance, LANG);

        KotlinObject.INSTANCE.setObjectProperty(LANG);
        System.out.printf("Using Kotlin object property in %s\n", KotlinObject.INSTANCE.getObjectProperty());
        KotlinObject.setStaticProperty(LANG);
        System.out.printf("Using Kotlin @JvmStatic object property in %s\n", KotlinObject.getStaticProperty());
        KotlinObject.staticField = LANG;
        System.out.printf("Using Kotlin object field in %s\n", KotlinObject.staticField);
        KotlinLibraryKt.setObjectExtensionProperty(KotlinObject.INSTANCE, LANG);
        System.out.printf("Using Kotlin object extension property in %s\n",
                          KotlinLibraryKt.getObjectExtensionProperty(KotlinObject.INSTANCE));
        KotlinObject.INSTANCE.objectMethod(LANG);
        KotlinObject.staticMethod(LANG);
        KotlinLibraryKt.objectExtensionFunction(KotlinObject.INSTANCE, LANG);

        // Kotlin does not generate overloads for default arguments.
        // Java callers must know the default values. Adding new default arguments is a breaking change for Java.
        KotlinLibraryKt.defaultArguments(LANG, 1, 2);

        KotlinLibraryKt.varargs(LANG);
        KotlinLibraryKt.varargs(LANG,1 );
        KotlinLibraryKt.varargs(LANG, 1, 2);
        // Varargs can also be called with an array
        KotlinLibraryKt.varargs(LANG, new Object[]{1, 2});

        KotlinLibraryKt.implementedFunction(() -> LANG);
        KotlinLibraryKt.implementedFunctionWithReceiver(it -> LANG);
        KotlinLibraryKt.implementedCurriedFunction(() -> () -> LANG);
        KotlinLibraryKt.implementedInterface(() -> LANG);
        KotlinLibraryKt.extendedBaseClass(new KotlinBaseClass() {
            @NotNull
            @Override
            public String getLanguage() {
                return LANG;
            }
        });

        KotlinLibraryKt.usedClassAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedConstructorAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedFunctionAnnotation(new UseKotlinAnnotations());
        // @KotlinPropertyAnnotation cannot be applied to a Java field or method
        //KotlinLibraryKt.usedPropertyAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedPropertyGetterAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedPropertySetterAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedFieldAnnotation(new UseKotlinAnnotations());
        KotlinLibraryKt.usedValueParameterAnnotation(new UseKotlinAnnotations());
    }
}
