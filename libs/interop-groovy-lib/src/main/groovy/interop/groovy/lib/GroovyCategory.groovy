package interop.groovy.lib

class GroovyCategory {
    static categoryMethod(GroovyClass it, String from) {
        println("Called Groovy category method from $from")
    }
}
