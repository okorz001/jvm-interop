package interop.groovy.lib

import java.lang.reflect.Executable
import java.lang.reflect.Parameter

import groovy.transform.CompileStatic

@CompileStatic
class GroovyLibrary {

    static implementedInterface(GroovyInterface it) {
        println "Implemented Groovy interface in ${it.getLanguage()}"
    }

    static implementedTrait(GroovyTrait it) {
        println "Implemented Groovy trait in ${it.getLanguage()}"
    }

    static extendedBaseClass(GroovyBaseClass it) {
        println "Extended Groovy class in ${it.getLanguage()}"
    }

    static usedTypeAnnotation(it) {
        String from = it.class.getAnnotation(GroovyTypeAnnotation).value()
        println("Used Groovy type annotation in ${from}")
    }

    static usedConstructorAnnotation(it) {
        String from = it.class.constructors
            .collect { it.getAnnotation(GroovyConstructorAnnotation) }
            .find { it }
            .value()
        println("Used Groovy constructor annotation in ${from}")
    }

    static usedMethodAnnotation(it) {
        String from = it.class.methods
            .collect { it.getAnnotation(GroovyMethodAnnotation) }
            .find { it }
            .value()
        println("Used Groovy method annotation in ${from}")
    }

    static usedFieldAnnotation(it) {
        String from = it.class.fields
            .collect { it.getAnnotation(GroovyFieldAnnotation) }
            .find { it }
            .value()
        println("Used Groovy field annotation in ${from}")
    }

    static usedParameterAnnotation(it) {
        // TODO: seems verbose
        List<Executable> executables = []
        executables.addAll(it.class.constructors)
        executables.addAll(it.class.methods)

        String from = executables
            .collect { it.parameters }
            .flatten()
            .collect { (it as Parameter).getAnnotation(GroovyParameterAnnotation) }
            .find { it }
            .value()
        println("Used Groovy parameter annotation in ${from}")
    }
}
