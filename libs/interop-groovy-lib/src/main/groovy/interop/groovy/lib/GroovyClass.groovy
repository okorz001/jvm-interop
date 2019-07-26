package interop.groovy.lib

class GroovyClass {

    static String staticProperty = ""
    // Access modifier forces a field instead of property.
    public static String staticField = ""
    private static Map staticDynamicProperties = [:]

    static staticMethod(from) {
        println "Calling Groovy static method from ${from}"
    }

    @SuppressWarnings("unused") // called by reflection
    static $static_propertyMissing(String name) {
        return staticDynamicProperties[name]
    }

    @SuppressWarnings("unused") // called by reflection
    static $static_propertyMissing(String name, value) {
        return staticDynamicProperties[name] = value
    }

    // note that $static_propertyMissing gets called first, so this won't be called unless that returns null
    // unlike regular methodMissing, this one doesn't *require* the String type
    @SuppressWarnings("unused") // called by reflection
    static $static_methodMissing(String name, args) {
        println "Calling Groovy static dynamic method from ${args[0]}"
    }

    String instanceProperty = ""
    public String instanceField = ""
    private Map instanceDynamicProperties = [:]

    def instanceMethod(from) {
        println "Calling Groovy instance method from ${from}"
    }

    def propertyMissing(String name) {
        return instanceDynamicProperties[name]
    }

    def propertyMissing(String name, value) {
        return instanceDynamicProperties[name] = value
    }

    // this doesn't work without the String type...
    def methodMissing(String name, args) {
        println "Calling Groovy instance dynamic method from ${args[0]}"
    }
}
