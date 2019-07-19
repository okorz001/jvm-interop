package interop.groovy.app.groovy

import interop.groovy.lib.GroovyClass
import interop.groovy.lib.GroovyConstructorAnnotation
import interop.groovy.lib.GroovyFieldAnnotation
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
GroovyClass.staticMethod(LANG)
GroovyClass.extensionStaticMethod(LANG)

def instance = new GroovyClass()
instance.instanceProperty = LANG
println("Using Groovy instance property in ${instance.instanceProperty}")
instance.instanceField = LANG
println("Using Groovy instance field in ${instance.instanceField}")
instance.instanceMethod(LANG)
instance.extensionInstanceMethod(LANG)

GroovyClass.implementedInterface({ LANG })
GroovyClass.implementedTrait({ LANG })
GroovyClass.extendedBaseClass({ LANG })
GroovyClass.usedTypeAnnotation(new UseGroovyAnnotations())
GroovyClass.usedConstructorAnnotation(new UseGroovyAnnotations())
GroovyClass.usedMethodAnnotation(new UseGroovyAnnotations())
GroovyClass.usedFieldAnnotation(new UseGroovyAnnotations())
GroovyClass.usedParameterAnnotation(new UseGroovyAnnotations())
