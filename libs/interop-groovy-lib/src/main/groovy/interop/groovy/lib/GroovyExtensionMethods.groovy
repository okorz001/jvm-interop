package interop.groovy.lib

class GroovyExtensionMethods {
    static def extensionInstanceMethod(GroovyClass self, String from) {
        println "Calling Groovy extension instance method from ${from}"
    }
}
