package interop.groovy.app.java

import interop.java.lib.JavaClass

final String LANG = "Groovy"

JavaClass.staticField = LANG
println("Using Java static field in ${JavaClass.staticField}")
JavaClass.staticProperty = LANG
println("Using Java static property in ${JavaClass.staticProperty}")
JavaClass.staticMethod(LANG)

def instance = new JavaClass()
instance.instanceField = LANG
println("Using Java instance field in ${instance.instanceField}")
instance.instanceProperty = LANG
println("Using Java instance property in ${instance.instanceProperty}")
instance.instanceMethod(LANG)

JavaClass.implementedInterface({ LANG })
JavaClass.extendedBaseClass({ LANG })
