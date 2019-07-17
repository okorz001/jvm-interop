package interop.kotlin.lib

var packageProperty: String = ""

fun packageFunction(from: String) {
    println("Calling Kotlin package function from $from")
}

class KotlinClass {
    companion object {
        var companionProperty: String = ""

        @JvmStatic
        var staticProperty: String = ""

        @JvmField
        var staticField: String = ""

        fun companionMethod(from: String) {
            println("Calling Kotlin companion method from $from")
        }

        @JvmStatic
        fun staticMethod(from: String) {
            println("Calling Kotlin @JvmStatic companion method from $from")
        }
    }

    var instanceProperty: String = ""

    @JvmField
    var instanceField: String = ""

    fun instanceMethod(from: String) {
        println("Calling Kotlin instance method from $from")
    }
}

fun KotlinClass.extensionMethod(from: String) {
    println("Calling Kotlin extension function from $from")
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
