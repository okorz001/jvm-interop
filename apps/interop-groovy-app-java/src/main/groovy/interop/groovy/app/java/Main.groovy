package interop.groovy.app.java

import interop.java.lib.JavaClass
import interop.java.lib.JavaConstructorAnnotation
import interop.java.lib.JavaFieldAnnotation
import interop.java.lib.JavaMethodAnnotation
import interop.java.lib.JavaParameterAnnotation
import interop.java.lib.JavaTypeAnnotation

// This is a script variable, so it's not a constant expression
def LANG = "Groovy"

@JavaTypeAnnotation(LANG2)
class UseJavaAnnotations {
    // LANG2 is a class variable with a literal value, so it's a constant expression
    public static final String LANG2 = "Groovy"

    // Must use access modifier to force a field instead of a property
    @JavaFieldAnnotation(LANG2)
    public int field

    @JavaConstructorAnnotation(LANG2)
    UseJavaAnnotations() {}

    @JavaMethodAnnotation(LANG2)
    def method() {}

    def method(@JavaParameterAnnotation(LANG2) param) {}
}

JavaClass.staticField = LANG
println("Using Java static field in ${JavaClass.staticField}")
JavaClass.staticProperty = LANG
println("Using Java static property in ${JavaClass.staticProperty}")
JavaClass.staticMethod(LANG)

def instance = new JavaClass()
instance.instanceField = LANG
println("Using Java instance field in ${instance.instanceField}")
instance.instanceProperty = LANG
println("Using Java instance property in ${instance.instanceProperty}")
instance.instanceMethod(LANG)

JavaClass.implementedInterface({ LANG })
JavaClass.extendedBaseClass({ LANG })
JavaClass.usedTypeAnnotation(new UseJavaAnnotations())
JavaClass.usedConstructorAnnotation(new UseJavaAnnotations())
JavaClass.usedMethodAnnotation(new UseJavaAnnotations())
JavaClass.usedFieldAnnotation(new UseJavaAnnotations())
JavaClass.usedParameterAnnotation(new UseJavaAnnotations())
