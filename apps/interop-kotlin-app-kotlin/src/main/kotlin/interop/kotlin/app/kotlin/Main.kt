package interop.kotlin.app.kotlin

import interop.kotlin.lib.KotlinBaseClass
import interop.kotlin.lib.KotlinClass
import interop.kotlin.lib.KotlinClassAnnotation
import interop.kotlin.lib.KotlinConstructorAnnotation
import interop.kotlin.lib.KotlinFieldAnnotation
import interop.kotlin.lib.KotlinFunctionAnnotation
import interop.kotlin.lib.KotlinInterface
import interop.kotlin.lib.KotlinInterfaceWithMethod
import interop.kotlin.lib.KotlinInterfaceWithProperty
import interop.kotlin.lib.KotlinObject
import interop.kotlin.lib.KotlinPropertyAnnotation
import interop.kotlin.lib.KotlinPropertyGetterAnnotation
import interop.kotlin.lib.KotlinPropertySetterAnnotation
import interop.kotlin.lib.KotlinValueParameterAnnotation
// Cannot import package directly, so this gets pretty ugly without *.
import interop.kotlin.lib.companionExtensionFunction
import interop.kotlin.lib.companionExtensionInfixFunction
import interop.kotlin.lib.companionExtensionProperty
import interop.kotlin.lib.defaultArguments
import interop.kotlin.lib.defaultArgumentsOverloads
import interop.kotlin.lib.extendedBaseClass
import interop.kotlin.lib.implementedCurriedFunction
import interop.kotlin.lib.implementedFunction
import interop.kotlin.lib.implementedFunctionWithReceiver
import interop.kotlin.lib.implementedInterface
import interop.kotlin.lib.implementedInterfaceWithMethod
import interop.kotlin.lib.implementedInterfaceWithProperty
import interop.kotlin.lib.instanceExtensionFunction
import interop.kotlin.lib.instanceExtensionInfixFunction
import interop.kotlin.lib.instanceExtensionProperty
import interop.kotlin.lib.objectExtensionFunction
import interop.kotlin.lib.objectExtensionInfixFunction
import interop.kotlin.lib.objectExtensionProperty
import interop.kotlin.lib.packageFunction
import interop.kotlin.lib.packageProperty
import interop.kotlin.lib.usedClassAnnotation
import interop.kotlin.lib.usedConstructorAnnotation
import interop.kotlin.lib.usedFieldAnnotation
import interop.kotlin.lib.usedFunctionAnnotation
import interop.kotlin.lib.usedPropertyAnnotation
import interop.kotlin.lib.usedPropertyGetterAnnotation
import interop.kotlin.lib.usedPropertySetterAnnotation
import interop.kotlin.lib.usedValueParameterAnnotation
import interop.kotlin.lib.varargs

private const val LANG = "Kotlin"

@KotlinClassAnnotation(LANG)
class UseKotlinAnnotations @KotlinConstructorAnnotation(LANG) constructor(
    @KotlinPropertyAnnotation(LANG)
    // get use-site target must be used to avoid annotating the constructor parameter.
    @get:KotlinPropertyGetterAnnotation(LANG)
    // set use-site target must be used to avoid annotating the constructor parameter.
    @set:KotlinPropertySetterAnnotation(LANG)
    var property: Int = 0,

    // Must use @JvmField to force a public field instead of a property.
    // Alternatively, the field use-site target can be used.
    // However, the backing field is always private, so it is not visible with getFields().
    // Private fields are still visible with getDeclaredFields(), but this violates access control.
    @JvmField
    @KotlinFieldAnnotation(LANG)
    var field: Int = 0
) {
    @KotlinFunctionAnnotation(LANG)
    fun method() {}

    fun method(@KotlinValueParameterAnnotation(LANG) param: Int) {}
}

fun main() {
    packageProperty = LANG
    println("Used Kotlin package property in $packageProperty")
    packageFunction(LANG)

    KotlinClass.companionProperty = LANG
    println("Used Kotlin companion property in ${KotlinClass.companionProperty}")
    KotlinClass.staticProperty = LANG
    println("Used Kotlin @JvmStatic companion property in ${KotlinClass.staticProperty}")
    KotlinClass.staticField = LANG
    println("Used Kotlin companion field in ${KotlinClass.staticField}")
    KotlinClass.companionExtensionProperty = LANG
    println("Used Kotlin instance extension property in ${KotlinClass.companionExtensionProperty}")
    KotlinClass.companionMethod(LANG)
    KotlinClass companionInfixMethod LANG
    // infix function can still be called in normal manner
    KotlinClass.companionInfixMethod(LANG)
    KotlinClass.staticMethod(LANG)
    KotlinClass staticInfixMethod LANG
    // infix function can still be called in normal manner
    KotlinClass.staticInfixMethod(LANG)
    KotlinClass.companionExtensionFunction(LANG)
    KotlinClass companionExtensionInfixFunction  LANG
    // infix function can still be called in normal manner
    KotlinClass.companionExtensionInfixFunction(LANG)

    val instance = KotlinClass()
    instance.instanceProperty = LANG
    println("Used Kotlin instance property in ${instance.instanceProperty}")
    instance.instanceField = LANG
    println("Used Kotlin instance field in ${instance.instanceField}")
    instance.instanceExtensionProperty = LANG
    println("Used Kotlin instance extension property in ${instance.instanceExtensionProperty}")
    instance.instanceMethod(LANG)
    instance instanceInfixMethod LANG
    // infix function can still be called in normal manner
    instance.instanceInfixMethod(LANG)
    instance.instanceExtensionFunction(LANG)
    instance instanceExtensionInfixFunction LANG
    // infix function can still be called in normal manner
    instance.instanceExtensionInfixFunction(LANG)

    KotlinObject.objectProperty = LANG
    println("Used Kotlin object property in ${KotlinObject.objectProperty}")
    KotlinObject.staticProperty = LANG
    println("Used Kotlin @JvmStatic object property in ${KotlinObject.staticProperty}")
    KotlinObject.staticField = LANG
    println("Used Kotlin object field in ${KotlinObject.staticField}")
    KotlinObject.objectExtensionProperty = LANG
    println("Used Kotlin object extension property in ${KotlinObject.objectExtensionProperty}")
    KotlinObject.objectMethod(LANG)
    KotlinObject objectInfixMethod LANG
    // infix function can still be called in normal manner
    KotlinObject.objectInfixMethod(LANG)
    KotlinObject.staticMethod(LANG)
    KotlinObject staticInfixMethod LANG
    // infix function can still be called in normal manner
    KotlinObject.staticInfixMethod(LANG)
    KotlinObject.objectExtensionFunction(LANG)
    KotlinObject objectExtensionInfixFunction LANG
    // infix function can still be called in normal manner
    KotlinObject.objectExtensionInfixFunction(LANG)

    defaultArguments(LANG, 1, 2)
    defaultArguments(LANG, 1)
    defaultArguments(LANG)
    // named arguments allow skipping positional arguments with defaults
    defaultArguments(LANG, b = 2)
    // named arguments can be in any order
    defaultArguments(LANG, b = 2, a = 1)

    defaultArgumentsOverloads(LANG, 1, 2)
    defaultArgumentsOverloads(LANG, 1)
    defaultArgumentsOverloads(LANG)
    // named arguments allow skipping positional arguments with defaults
    defaultArgumentsOverloads(LANG, b = 2)
    // named arguments can be in any order
    defaultArgumentsOverloads(LANG, b = 2, a = 1)

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
    implementedInterfaceWithProperty(LANG, object : KotlinInterfaceWithProperty {
        override var language: String = ""
    })
    implementedInterfaceWithMethod(object : KotlinInterfaceWithMethod {
        override fun getLanguage() = LANG
    })
    extendedBaseClass(object : KotlinBaseClass() {
        override fun getLanguage() = LANG
    })

    usedClassAnnotation(UseKotlinAnnotations())
    usedConstructorAnnotation(UseKotlinAnnotations())
    usedFunctionAnnotation(UseKotlinAnnotations())
    usedPropertyAnnotation(UseKotlinAnnotations())
    usedPropertyGetterAnnotation(UseKotlinAnnotations())
    usedPropertySetterAnnotation(UseKotlinAnnotations())
    usedFieldAnnotation(UseKotlinAnnotations())
    usedValueParameterAnnotation(UseKotlinAnnotations())
}
