package interop.groovy.lib

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
}
