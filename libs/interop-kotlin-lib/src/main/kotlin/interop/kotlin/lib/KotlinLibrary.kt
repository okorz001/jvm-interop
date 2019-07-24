package interop.kotlin.lib

var packageProperty: String = ""

fun packageFunction(from: String) {
    println("Calling Kotlin package function from $from")
}

fun KotlinClass.extensionMethod(from: String) {
    println("Calling Kotlin extension method from $from")
}

fun implementedFunction(it: () -> String) {
    println("Implemented Kotlin function in ${it()}")
}

fun implementedFunctionWithReceiver(it: KotlinClass.() -> String) {
    println("Implemented Kotlin function with receiver in ${it(KotlinClass())}")
}

fun implementedInterface(it: KotlinInterface) {
    println("Implemented Kotlin interface in ${it.getLanguage()}")
}

fun extendedBaseClass(it: KotlinBaseClass) {
    println("Extended Kotlin base class in ${it.getLanguage()}")
}
