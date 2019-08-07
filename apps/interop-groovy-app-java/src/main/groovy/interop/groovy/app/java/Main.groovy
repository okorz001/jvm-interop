package interop.groovy.app.java

import interop.java.lib.JavaCheckedException
import interop.java.lib.JavaClass
import interop.java.lib.JavaConstructorAnnotation
import interop.java.lib.JavaFieldAnnotation
import interop.java.lib.JavaLibrary
import interop.java.lib.JavaMethodAnnotation
import interop.java.lib.JavaParameterAnnotation
import interop.java.lib.JavaTypeAnnotation
import interop.java.lib.JavaUncheckedException

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
println("Used Java static field in ${JavaClass.staticField}")
JavaClass.staticMethod(LANG)

def instance = new JavaClass()
instance.instanceField = LANG
println("Used Java instance field in ${instance.instanceField}")
instance.instanceMethod(LANG)

JavaLibrary.varargs(LANG)
JavaLibrary.varargs(LANG, 1)
JavaLibrary.varargs(LANG, 1, 2)
// Varargs can also be called with an array
JavaLibrary.varargs(LANG, [1, 2] as Object[])

try {
    JavaLibrary.throwsUnchecked()
}
catch (JavaUncheckedException e) {
    println("Caught Java unchecked exception in Groovy")
}

try {
    JavaLibrary.throwsChecked()
}
catch (JavaCheckedException e) {
    println("Caught Java checked exception in Groovy")
}

JavaLibrary.implementedInterface({ LANG })
JavaLibrary.implementedInterfaceWithMethod({ LANG })
JavaLibrary.extendedBaseClass({ LANG })
JavaLibrary.usedTypeAnnotation(new UseJavaAnnotations())
JavaLibrary.usedConstructorAnnotation(new UseJavaAnnotations())
JavaLibrary.usedMethodAnnotation(new UseJavaAnnotations())
JavaLibrary.usedFieldAnnotation(new UseJavaAnnotations())
JavaLibrary.usedParameterAnnotation(new UseJavaAnnotations())
