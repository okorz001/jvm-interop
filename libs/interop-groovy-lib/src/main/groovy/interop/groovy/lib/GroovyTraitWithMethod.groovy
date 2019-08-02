package interop.groovy.lib

trait GroovyTraitWithMethod {
    abstract String getLanguage()

    void traitMethod() {
        println "Implemented Groovy trait with method in ${getLanguage()}"
    }
}
