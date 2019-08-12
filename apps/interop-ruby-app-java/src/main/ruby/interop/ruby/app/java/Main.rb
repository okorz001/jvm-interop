java_package 'interop.ruby.app.java'

java_import 'interop.java.lib.JavaBaseClass'
java_import 'interop.java.lib.JavaCheckedException'
java_import 'interop.java.lib.JavaClass'
java_import 'interop.java.lib.JavaLibrary'
java_import 'interop.java.lib.JavaUncheckedException'

LANG = 'Ruby'

JavaClass::staticField = LANG
puts "Used Java static field in #{JavaClass::staticField}"
JavaClass.staticMethod(LANG)

instance = JavaClass.new
instance.instanceField = LANG
puts "Used Java instance field in #{instance.instanceField}"
instance.instanceMethod(LANG)

JavaLibrary.varargs(LANG)
JavaLibrary.varargs(LANG, 1)
JavaLibrary.varargs(LANG, 1, 2)

begin
  JavaLibrary.throwsUnchecked()
rescue JavaUncheckedException
  puts "Caught Java unchecked exception in Ruby"
end

begin
  JavaLibrary.throwsChecked()
rescue JavaCheckedException
  puts "Caught Java checked exception in Ruby"
end

JavaLibrary.implementedInterface { LANG }
JavaLibrary.implementedInterfaceWithMethod { LANG }
JavaLibrary.extendedBaseClass(Class.new(JavaBaseClass) do
  def getLanguage
    LANG
  end
end.new)
