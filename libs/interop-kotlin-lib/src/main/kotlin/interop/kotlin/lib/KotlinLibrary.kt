package interop.kotlin.lib

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberExtensionProperties
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties

var packageProperty: String = ""

fun packageFunction(from: String) = println("Calling Kotlin package function from $from")

// extension properties do not have their own backing fields
private var companionExtensionData: String = ""
var KotlinClass.Companion.companionExtensionProperty: String
    get() = companionExtensionData
    set(value) { companionExtensionData = value }

fun KotlinClass.Companion.companionExtensionFunction(from: String) =
    println("Calling Kotlin companion extension function from $from")

// extension properties do not have their own backing fields
// note: assigning an instance property to a global property is unexpected behavior, just for demonstration purposes
private var instanceExtensionData: String = ""
var KotlinClass.instanceExtensionProperty: String
    get() = instanceExtensionData
    set(value) { instanceExtensionData = value }

fun KotlinClass.instanceExtensionFunction(from: String) =
    println("Calling Kotlin instance extension function from $from")

// extension properties do not have their own backing fields
private var objectExtensionData: String = ""
var KotlinObject.objectExtensionProperty: String
    get() = objectExtensionData
    set(value) { objectExtensionData = value }

fun KotlinObject.objectExtensionFunction(from: String) = println("Calling Kotlin object extension function from $from")

fun defaultArguments(from: String, a: Any = "default", b: Any = "default") =
    println("Called Kotlin function with default arguments from $from with a=$a and b=$b")

fun varargs(from: String, vararg args: Any) =
    println("Called Kotlin function with varargs from $from with ${listOf(*args)}")

fun implementedFunction(it: () -> String) = println("Implemented Kotlin function in ${it()}")

fun implementedFunctionWithReceiver(it: KotlinClass.() -> String) =
    println("Implemented Kotlin function with receiver in ${it(KotlinClass())}")

fun implementedCurriedFunction(it: () -> () -> String) = println("Implemented Kotlin curried function in ${it()()}")

fun implementedInterface(it: KotlinInterface) = println("Implemented Kotlin interface in ${it.getLanguage()}")

fun extendedBaseClass(it: KotlinBaseClass) = println("Extended Kotlin base class in ${it.getLanguage()}")

fun usedClassAnnotation(it: Any) {
    val from = it::class.findAnnotation<KotlinClassAnnotation>()?.from
    println("Used Kotlin class annotation in $from")
}

fun usedConstructorAnnotation(it: Any) {
    val from = it::class.constructors
        .mapNotNull { it.findAnnotation<KotlinConstructorAnnotation>() }
        .first()
        .from
    println("Used Kotlin constructor annotation in $from")
}

fun usedFunctionAnnotation(it: Any) {
    val from = it::class.functions
        .mapNotNull { it.findAnnotation<KotlinFunctionAnnotation>() }
        .first()
        .from
    println("Used Kotlin function annotation in $from")
}

private val KClass<*>.properties
    get() = this.memberProperties
        .union(this.memberExtensionProperties)
        .union(this.staticProperties)
        as Set<KProperty<*>>

fun usedPropertyAnnotation(it: Any) {
    val from = it::class.properties
        .mapNotNull { it.findAnnotation<KotlinPropertyAnnotation>() }
        .first()
        .from
    println("Used Kotlin property annotation in $from")
}

fun usedPropertyGetterAnnotation(it: Any) {
    val from = it::class.properties
        .mapNotNull { it.getter }
        // Kotlin will not allow annotation on method, but Java will
        .union(it::class.memberFunctions)
        .mapNotNull { it.findAnnotation<KotlinPropertyGetterAnnotation>() }
        .first()
        .from
    println("Used Kotlin property getter annotation in $from")
}

fun usedPropertySetterAnnotation(it: Any) {
    val from = it::class.properties
        .mapNotNull { (it as? KMutableProperty)?.setter }
        // Kotlin will not allow annotation on method, but Java will
        .union(it::class.memberFunctions)
        .mapNotNull { it.findAnnotation<KotlinPropertySetterAnnotation>() }
        .first()
        .from
    println("Used Kotlin property setter annotation in $from")
}

fun usedFieldAnnotation(it: Any) {
    // read fields from Java Class
    val from = it::class.java.fields
        .mapNotNull { it.annotations.filterIsInstance<KotlinFieldAnnotation>().firstOrNull() }
        .first()
        .from
    println("Used Kotlin field annotation in $from")
}

fun usedValueParameterAnnotation(it: Any) {
    val from = it::class.functions
        .union(it::class.constructors)
        .mapNotNull {
            it.parameters.mapNotNull { it.findAnnotation<KotlinValueParameterAnnotation>() }.firstOrNull()
        }
        .first()
        .from
    println("Used Kotlin value parameter annotation in $from")
}
