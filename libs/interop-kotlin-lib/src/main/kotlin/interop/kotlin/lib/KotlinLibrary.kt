package interop.kotlin.lib

var packageProperty: String = ""

fun packageFunction(from: String) {
    println("Calling Kotlin package function from $from")
}

// extension properties do not have their own backing fields
private var companionExtensionData: String = ""
var KotlinClass.Companion.companionExtensionProperty: String
    get() = companionExtensionData
    set(value) { companionExtensionData = value }

fun KotlinClass.Companion.companionExtensionFunction(from: String) {
    println("Calling Kotlin companion extension function from $from")
}

// extension properties do not have their own backing fields
// note: assigning an instance property to a global property is unexpected behavior, just for demonstration purposes
private var instanceExtensionData: String = ""
var KotlinClass.instanceExtensionProperty: String
    get() = instanceExtensionData
    set(value) { instanceExtensionData = value }

fun KotlinClass.instanceExtensionFunction(from: String) {
    println("Calling Kotlin instance extension function from $from")
}

// extension properties do not have their own backing fields
private var objectExtensionData: String = ""
var KotlinObject.objectExtensionProperty: String
    get() = objectExtensionData
    set(value) { objectExtensionData = value }

fun KotlinObject.objectExtensionFunction(from: String) {
    println("Calling Kotlin object extension function from $from")
}

fun defaultArguments(from: String, a: Any = "default", b: Any = "default") {
    println("Called Kotlin function with default arguments from $from with a=$a and b=$b")
}

fun implementedFunction(it: () -> String) {
    println("Implemented Kotlin function in ${it()}")
}

fun implementedFunctionWithReceiver(it: KotlinClass.() -> String) {
    println("Implemented Kotlin function with receiver in ${it(KotlinClass())}")
}

fun implementedCurriedFunction(it: () -> () -> String) {
    println("Implemented Kotlin curried function in ${it()()}")
}

fun implementedInterface(it: KotlinInterface) {
    println("Implemented Kotlin interface in ${it.getLanguage()}")
}

fun extendedBaseClass(it: KotlinBaseClass) {
    println("Extended Kotlin base class in ${it.getLanguage()}")
}
