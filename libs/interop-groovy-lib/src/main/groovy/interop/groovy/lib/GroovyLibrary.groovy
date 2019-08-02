package interop.groovy.lib

import java.lang.reflect.Executable
import java.lang.reflect.Parameter

import groovy.transform.CompileStatic

@CompileStatic
class GroovyLibrary {

    static defaultArguments(String from, a = "default", b = "default") {
        println("Called Groovy method with default arguments from ${from} with a=${a} b=${b}")
    }

    static namedParameters(Map args, String from) {
        def a = args.a ?: "default"
        def b = args.b ?: "default"
        println("Called Groovy method with named parameters from ${from} with a=${a} b=${b}")
    }

    // convenience overload instead of namedParameters([:], from)
    static namedParameters(String from) {
        // different println to clarify what is being called
        println("Called Groovy method with named parameters from ${from} with no named parameters")
    }

    static varargs(String from, Object... args) {
        println("Called Groovy method with varargs from ${from} with ${args}")
    }

    static implementedClosure(Closure<String> c) {
        println "Implemented Groovy closure in ${c()}"
    }

    static class MyDelegate {
        def message = {
            println "Implemented Groovy closure with delegate in ${it}"
        }
    }

    static implementedClosureWithDelegate(@DelegatesTo(MyDelegate) Closure<?> c) {
        c = (Closure) c.clone()
        c.resolveStrategy = Closure.DELEGATE_ONLY
        c.delegate = new MyDelegate()
        c()
    }

    static implementedInterface(GroovyInterface it) {
        println "Implemented Groovy interface in ${it.getLanguage()}"
    }

    static implementedTrait(GroovyTrait it) {
        println "Implemented Groovy trait in ${it.getLanguage()}"
    }

    static implementedTraitWithProperty(String from, GroovyTraitWithProperty it) {
        it.language = from
        println "Implemented Groovy trait with property in ${it.language}"
    }

    static implementedTraitWithMethod(GroovyTraitWithMethod it) {
        it.traitMethod()
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
