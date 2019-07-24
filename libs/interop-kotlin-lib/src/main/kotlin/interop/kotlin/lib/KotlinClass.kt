package interop.kotlin.lib

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
