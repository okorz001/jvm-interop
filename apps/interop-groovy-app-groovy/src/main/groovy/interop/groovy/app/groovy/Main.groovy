package interop.groovy.app.groovy

import interop.groovy.lib.GroovyCategory
import interop.groovy.lib.GroovyClass
import interop.groovy.lib.GroovyConstructorAnnotation
import interop.groovy.lib.GroovyFieldAnnotation
import interop.groovy.lib.GroovyLibrary
import interop.groovy.lib.GroovyMethodAnnotation
import interop.groovy.lib.GroovyParameterAnnotation
import interop.groovy.lib.GroovyTraitWithProperty
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
println("Used Groovy static property in ${GroovyClass.staticProperty}")
GroovyClass.staticField = LANG
println("Used Groovy static field in ${GroovyClass.staticField}")
GroovyClass.staticDynamicProperty = LANG
println("Used Groovy static dynamic property in ${GroovyClass.staticDynamicProperty}")
GroovyClass.staticMethod(LANG)
GroovyClass.staticExtensionMethod(LANG)
GroovyClass.staticDynamicMethod(LANG)

def instance = new GroovyClass()
instance.instanceProperty = LANG
println("Used Groovy instance property in ${instance.instanceProperty}")
instance.instanceField = LANG
println("Used Groovy instance field in ${instance.instanceField}")
instance.instanceDynamicProperty = LANG
println("Used Groovy instance dynamic property in ${instance.instanceDynamicProperty}")
instance.instanceMethod(LANG)
instance.instanceExtensionMethod(LANG)
instance.instanceDynamicMethod(LANG)
use(GroovyCategory) { instance.categoryMethod(LANG) }

GroovyLibrary.defaultArguments(LANG, 1, 2)
GroovyLibrary.defaultArguments(LANG, 1)
GroovyLibrary.defaultArguments(LANG)

GroovyLibrary.namedParameters(LANG, a: 1, b: 2)
// named parameters can be in any order after positional arguments
GroovyLibrary.namedParameters(LANG, b: 2, a: 1)
// named parameters can be omitted
GroovyLibrary.namedParameters(LANG, a: 1)
GroovyLibrary.namedParameters(LANG, b: 2)
// if all named parameters are omitted, an empty map must be explicitly passed
GroovyLibrary.namedParameters([:], LANG)
// alternatively, an overload can be provided without the first map parameter
GroovyLibrary.namedParameters(LANG)

GroovyLibrary.varargs(LANG)
GroovyLibrary.varargs(LANG, 1)
GroovyLibrary.varargs(LANG, 1, 2)
// Varargs can also be called with an array
GroovyLibrary.varargs(LANG, [1, 2] as Object[])

GroovyLibrary.implementedClosure { LANG }
GroovyLibrary.implementedClosureWithDelegate { message.call(LANG) }
GroovyLibrary.implementedInterface { LANG }
GroovyLibrary.implementedTrait { LANG }
// Anonymous classes cannot implement traits
// Groovy automatically implements the property with default getters/setters
class MyTraitWithProperty implements GroovyTraitWithProperty {}
GroovyLibrary.implementedTraitWithProperty(LANG, new MyTraitWithProperty())
GroovyLibrary.implementedTraitWithMethod { LANG }
GroovyLibrary.extendedBaseClass { LANG }
GroovyLibrary.usedTypeAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedConstructorAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedMethodAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedFieldAnnotation(new UseGroovyAnnotations())
GroovyLibrary.usedParameterAnnotation(new UseGroovyAnnotations())
