package interop.kotlin.app.java

import interop.java.lib.JavaBaseClass
import interop.java.lib.JavaClass
import interop.java.lib.JavaConstructorAnnotation
import interop.java.lib.JavaFieldAnnotation
import interop.java.lib.JavaLibrary
import interop.java.lib.JavaMethodAnnotation
import interop.java.lib.JavaParameterAnnotation
import interop.java.lib.JavaTypeAnnotation

private const val LANG = "Kotlin"

@JavaTypeAnnotation(LANG)
class UseJavaAnnotations @JavaConstructorAnnotation(LANG) constructor(
    // Must use @JvmField to force a field instead of a property
    @JavaFieldAnnotation(LANG)
    @JvmField
    var field: Int = 0
) {
    @JavaMethodAnnotation(LANG)
    fun method() {}

    fun method(@JavaParameterAnnotation(LANG) param: Int) {}
}

fun main() {
    JavaClass.staticField = LANG
    println("Using Java static field in ${JavaClass.staticField}")
    JavaClass.staticMethod(LANG)

    val instance = JavaClass()
    instance.instanceField = LANG
    println("Using Java instance field in ${instance.instanceField}")
    instance.instanceMethod(LANG)

    JavaLibrary.varargs(LANG)
    JavaLibrary.varargs(LANG, 1)
    JavaLibrary.varargs(LANG, 1, 2)
    // Kotlin Array cannot be directly passed as varargs.
    // The spread operator can be used to unwrap the Array into arguments.
    JavaLibrary.varargs(LANG, *arrayOf(1, 2))

    JavaLibrary.implementedInterface { LANG }
    JavaLibrary.extendedBaseClass(object : JavaBaseClass() {
        override fun getLanguage() = LANG
    })
    JavaLibrary.usedTypeAnnotation(UseJavaAnnotations())
    JavaLibrary.usedConstructorAnnotation(UseJavaAnnotations())
    JavaLibrary.usedMethodAnnotation(UseJavaAnnotations())
    JavaLibrary.usedFieldAnnotation(UseJavaAnnotations())
    JavaLibrary.usedParameterAnnotation(UseJavaAnnotations())
}
