package interop.kotlin.lib

interface KotlinInterfaceWithMethod {
    fun getLanguage(): String

    fun interfaceMethod() = println("Implemented Kotlin interface with  method in ${getLanguage()}")
}
