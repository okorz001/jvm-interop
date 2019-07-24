package interop.groovy.lib

class GroovyExtensionMethods {
    static def instanceExtensionMethod(GroovyClass self, String from) {
        println "Calling Groovy instance extension method from ${from}"
    }
}
