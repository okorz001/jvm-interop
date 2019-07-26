package interop.groovy.app.groovy

import interop.groovy.lib.GroovyClass
import interop.groovy.lib.GroovyConstructorAnnotation
import interop.groovy.lib.GroovyFieldAnnotation
import interop.groovy.lib.GroovyLibrary
import interop.groovy.lib.GroovyMethodAnnotation
import interop.groovy.lib.GroovyParameterAnnotation
import interop.groovy.lib.GroovyTypeAnnotation

final String LANG = "Groovy"

@GroovyTypeAnnotation(LANG2)
class UseGroovyAnnotations {
    // LANG2 is a class variable with a literal value, so it's a constant expression
    public static final String LANG2 = "Groovy"

    // Must use access modifier to force a field instead of a property
    @GroovyFieldAnnotation(LANG2)
    public int field

    @GroovyConstructorAnnotation(LANG2)
    UseGroovyAnnotations() {}

    @GroovyMethodAnnotation(LANG2)
    def method() {}

    def method(@GroovyParameterAnnotation(LANG2) param) {}
}

GroovyClass.staticProperty = LANG
println("Using Groovy static property in ${GroovyClass.staticProperty}")
GroovyClass.staticField = LANG
println("Using Groovy static field in ${GroovyClass.staticField}")
GroovyClass.staticDynamicProperty = LANG
println("Using Groovy static dynamic property in ${GroovyClass.staticDynamicProperty}")
GroovyClass.staticMethod(LANG)
GroovyClass.staticExtensionMethod(LANG)
GroovyClass.staticDynamicMethod(LANG)

def instance = new GroovyClass()
instance.instanceProperty = LANG
println("Using Groovy instance property in ${instance.instanceProperty}")
instance.instanceField = LANG
println("Using Groovy instance field in ${instance.instanceField}")
instance.instanceDynamicProperty = LANG
println("Using Groovy instance dynamic property in ${instance.instanceDynamicProperty}")
instance.instanceMethod(LANG)
instance.instanceExtensionMethod(LANG)
instance.instanceDynamicMethod(LANG)

GroovyLibrary.implementedClosure { LANG }
GroovyLibrary.implementedClosureWithDelegate { message.call(LANG) }
GroovyLibrary.implementedInterface { LANG }
GroovyLibrary.implementedTrait { LANG }
GroovyLibrary.extendedBaseClass { LANG }
GroovyLibrary.usedTypeAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedConstructorAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedMethodAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedFieldAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedParameterAnnotation(new UseGroovyAnnotations())
