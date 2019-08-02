package interop.kotlin.lib

class KotlinClass {
    companion object {
        var companionProperty: String = ""

        @JvmStatic
        var staticProperty: String = ""

        @JvmField
        var staticField: String = ""

        fun companionMethod(from: String) = println("Called Kotlin companion method from $from")

        @JvmStatic
        fun staticMethod(from: String) = println("Called Kotlin @JvmStatic companion method from $from")
    }

    var instanceProperty: String = ""

    @JvmField
    var instanceField: String = ""

    fun instanceMethod(from: String) = println("Called Kotlin instance method from $from")
}
