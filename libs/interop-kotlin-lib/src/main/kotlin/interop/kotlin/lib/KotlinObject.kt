package interop.kotlin.lib

object KotlinObject {
    var objectProperty: String = ""

    @JvmStatic
    var staticProperty: String = ""

    @JvmField
    var staticField: String = ""

    fun objectMethod(from: String) = println("Called Kotlin object method from $from")

    infix fun objectInfixMethod(from: String) = println("Called Kotlin @JvmStatic object infix method from $from")

    @JvmStatic
    fun staticMethod(from: String) = println("Called Kotlin @JvmStatic object method from $from")

    @JvmStatic
    infix fun staticInfixMethod(from: String) = println("Called Kotlin @JvmStatic object infix method from $from")
}
