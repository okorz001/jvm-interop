java_package 'interop.ruby.app.java'

java_import 'interop.java.lib.JavaClass'

LANG = 'Ruby'

JavaClass::staticField = LANG
puts "Used Java static field in #{JavaClass::staticField}"
JavaClass.staticMethod(LANG)

instance = JavaClass.new
instance.instanceField = LANG
puts "Used Java instance field in #{instance.instanceField}"
instance.instanceMethod(LANG)
