package interop.kotlin.app.kotlin

// Cannot import package directly, so this gets pretty ugly without *.
import interop.kotlin.lib.extendedBaseClass
import interop.kotlin.lib.instanceExtensionFunction
import interop.kotlin.lib.implementedFunction
import interop.kotlin.lib.implementedFunctionWithReceiver
import interop.kotlin.lib.implementedInterface
import interop.kotlin.lib.KotlinBaseClass
import interop.kotlin.lib.KotlinClass
import interop.kotlin.lib.KotlinInterface
import interop.kotlin.lib.KotlinObject
import interop.kotlin.lib.companionExtensionFunction
import interop.kotlin.lib.companionExtensionProperty
import interop.kotlin.lib.defaultArguments
import interop.kotlin.lib.implementedCurriedFunction
import interop.kotlin.lib.instanceExtensionProperty
import interop.kotlin.lib.objectExtensionFunction
import interop.kotlin.lib.objectExtensionProperty
import interop.kotlin.lib.packageFunction
import interop.kotlin.lib.packageProperty
import interop.kotlin.lib.varargs

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
    KotlinClass.companionExtensionProperty = LANG
    println("Using Kotlin instance extension property in ${KotlinClass.companionExtensionProperty}")
    KotlinClass.companionMethod(LANG)
    KotlinClass.staticMethod(LANG)
    KotlinClass.companionExtensionFunction(LANG)

    val instance = KotlinClass()
    instance.instanceProperty = LANG
    println("Using Kotlin instance property in ${instance.instanceProperty}")
    instance.instanceField = LANG
    println("Using Kotlin instance field in ${instance.instanceField}")
    instance.instanceExtensionProperty = LANG
    println("Using Kotlin instance extension property in ${instance.instanceExtensionProperty}")
    instance.instanceMethod(LANG)
    instance.instanceExtensionFunction(LANG)

    KotlinObject.objectProperty = LANG
    println("Using Kotlin object property in ${KotlinObject.objectProperty}")
    KotlinObject.staticProperty = LANG
    println("Using Kotlin @JvmStatic object property in ${KotlinObject.staticProperty}")
    KotlinObject.staticField = LANG
    println("Using Kotlin object field in ${KotlinObject.staticField}")
    KotlinObject.objectExtensionProperty = LANG
    println("Using Kotlin object extension property in ${KotlinObject.objectExtensionProperty}")
    KotlinObject.objectMethod(LANG)
    KotlinObject.staticMethod(LANG)
    KotlinObject.objectExtensionFunction(LANG)

    defaultArguments(LANG, 1, 2)
    defaultArguments(LANG, 1)
    defaultArguments(LANG)
    // named arguments allow skipping positional arguments with defaults
    defaultArguments(LANG, b = 2)
    // named arguments can be in any order
    defaultArguments(LANG, b = 2, a = 1)

    varargs(LANG)
    varargs(LANG, 1)
    varargs(LANG, 1, 2)
    // An array cannot be passed directly, but you can use the spread operator.
    varargs(LANG, *arrayOf(1, 2))

    implementedFunction { LANG }
    implementedFunctionWithReceiver { LANG }
    implementedCurriedFunction { { LANG } }
    implementedInterface(object : KotlinInterface {
        override fun getLanguage() = LANG
    })
    extendedBaseClass(object : KotlinBaseClass() {
        override fun getLanguage() = LANG
    })
}
