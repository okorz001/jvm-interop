package interop.groovy.lib

class GroovyStaticExtensionMethods {
    static def staticExtensionMethod(GroovyClass self, String from) {
        println "Called Groovy static extension method from ${from}"
    }
}
