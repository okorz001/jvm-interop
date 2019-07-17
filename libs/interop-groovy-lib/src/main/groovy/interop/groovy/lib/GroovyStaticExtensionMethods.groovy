package interop.groovy.lib

class GroovyStaticExtensionMethods {
    static def extensionStaticMethod(GroovyClass self, String from) {
        println "Calling Groovy extension static method from ${from}"
    }
}
