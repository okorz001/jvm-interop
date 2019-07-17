package interop.kotlin.app.java

import interop.java.lib.JavaBaseClass
import interop.java.lib.JavaClass

private const val LANG = "Kotlin"

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

    JavaClass.implementedInterface { LANG }
    JavaClass.extendedBaseClass(object: JavaBaseClass() {
        override fun getLanguage(): String {
            return LANG
        }
    })
}
