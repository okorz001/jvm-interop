package interop.groovy.app.groovy

import interop.groovy.lib.GroovyClass

final String LANG = "Groovy"

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
