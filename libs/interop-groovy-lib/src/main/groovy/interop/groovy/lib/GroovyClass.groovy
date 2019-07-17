package interop.groovy.lib

import groovy.transform.CompileStatic

@CompileStatic
class GroovyClass {
    static String staticProperty = ""
    // Access modifier forces a field instead of property.
    public static String staticField = ""

    static staticMethod(from) {
        println "Calling Groovy static method from ${from}"
    }

    String instanceProperty = ""
    public String instanceField = ""

    def instanceMethod(from) {
        println "Calling Groovy instance method from ${from}"
    }

    static implementedInterface(GroovyInterface it) {
        println "Implemented Groovy interface in ${it.getLanguage()}"
    }

    static implementedTrait(GroovyTrait it) {
        println "Implemented Groovy trait in ${it.getLanguage()}"
    }

    static extendedBaseClass(GroovyBaseClass it) {
        println "Extended Groovy class in ${it.getLanguage()}"
    }
}
