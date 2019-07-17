package interop.kotlin.lib

object KotlinObject {
    var objectProperty: String = ""

    @JvmStatic
    var staticProperty: String = ""

    @JvmField
    var staticField: String = ""

    fun objectMethod(from: String) {
        println("Calling Kotlin object method from $from")
    }

    @JvmStatic
    fun staticMethod(from: String) {
        println("Calling Kotlin @JvmStatic object method from $from")
    }
}
