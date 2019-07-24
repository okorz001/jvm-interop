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
    JavaClass.setStaticProperty(LANG)
    println("Using Java static property in ${JavaClass.getStaticProperty()}")
    JavaClass.staticMethod(LANG)

    val instance = JavaClass()
    instance.instanceField = LANG
    println("Using Java instance field in ${instance.instanceField}")
    instance.instanceProperty = LANG
    println("Using Java instance property in ${instance.instanceProperty}")
    instance.instanceMethod(LANG)

    JavaLibrary.implementedInterface { LANG }
    JavaLibrary.extendedBaseClass(object : JavaBaseClass() {
        override fun getLanguage(): String {
            return LANG
        }
    })
    JavaLibrary.usedTypeAnnotation(UseJavaAnnotations())
    JavaLibrary.usedConstructorAnnotation(UseJavaAnnotations())
    JavaLibrary.usedMethodAnnotation(UseJavaAnnotations())
    JavaLibrary.usedFieldAnnotation(UseJavaAnnotations())
    JavaLibrary.usedParameterAnnotation(UseJavaAnnotations())
}
