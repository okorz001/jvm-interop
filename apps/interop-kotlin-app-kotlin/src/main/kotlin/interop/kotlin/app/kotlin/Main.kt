package interop.kotlin.app.kotlin

// Cannot import package directly, so this gets pretty ugly without *.
import interop.kotlin.lib.extendedBaseClass
import interop.kotlin.lib.extensionMethod
import interop.kotlin.lib.implementedFunction
import interop.kotlin.lib.implementedFunctionWithReceiver
import interop.kotlin.lib.implementedInterface
import interop.kotlin.lib.KotlinBaseClass
import interop.kotlin.lib.KotlinClass
import interop.kotlin.lib.KotlinInterface
import interop.kotlin.lib.KotlinObject
import interop.kotlin.lib.packageFunction
import interop.kotlin.lib.packageProperty

private const val LANG = "Kotlin"

fun main() {
    packageProperty = LANG
    println("Using Kotlin package property in $packageProperty")
    packageFunction(LANG)

    KotlinClass.companionProperty = LANG
    println("Using Kotlin companion property in ${KotlinClass.companionProperty}")
    KotlinClass.staticProperty = LANG
    println("Using Kotlin @JvmStatic companion property in ${KotlinClass.staticProperty}")
    KotlinClass.staticField = LANG
    println("Using Kotlin companion field in ${KotlinClass.staticField}")
    KotlinClass.companionMethod(LANG)
    KotlinClass.staticMethod(LANG)

    val instance = KotlinClass()
    instance.instanceProperty = LANG
    println("Using Kotlin instance property in ${instance.instanceProperty}")
    instance.instanceField = LANG
    println("Using Kotlin instance field in ${instance.instanceField}")
    instance.instanceMethod(LANG)
    instance.extensionMethod(LANG)

    KotlinObject.objectProperty = LANG
    println("Using Kotlin object property in ${KotlinObject.objectProperty}")
    KotlinObject.staticProperty = LANG
    println("Using Kotlin @JvmStatic object property in ${KotlinObject.staticProperty}")
    KotlinObject.staticField = LANG
    println("Using Kotlin object field in ${KotlinObject.staticField}")
    KotlinObject.objectMethod(LANG)
    KotlinObject.staticMethod(LANG)

    implementedFunction { LANG }
    implementedFunctionWithReceiver { LANG }
    implementedInterface(object : KotlinInterface {
        override fun getLanguage(): String {
            return LANG
        }
    })
    extendedBaseClass(object : KotlinBaseClass() {
        override fun getLanguage(): String {
            return LANG
        }
    })
}
