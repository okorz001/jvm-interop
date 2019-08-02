package interop.scala.app.java

import interop.java.lib.{JavaClass, JavaConstructorAnnotation, JavaFieldAnnotation, JavaLibrary, JavaMethodAnnotation, JavaParameterAnnotation, JavaTypeAnnotation}

object Main extends App {
  // The final is required here to make a constant expression. (???)
  final val LANG = "Scala"

  @JavaTypeAnnotation(LANG)
  class UseJavaAnnotations @JavaConstructorAnnotation(LANG) () {
    // This annotates the field, not the generated getter or setter.
    // However, the backing field is always private, so it is not visible with getFields().
    // Private fields are still visible with getDeclaredFields(), but this violates access control.
    @JavaFieldAnnotation(LANG)
    var field: Int = 0

    @JavaMethodAnnotation(LANG)
    def method() = {}

    def method(@JavaParameterAnnotation(LANG) param: Int) = {}
  }

  JavaClass.staticField = LANG
  println(s"Used Java static field in ${JavaClass.staticField}")
  JavaClass.staticMethod(LANG)

  val instance = new JavaClass
  instance.instanceField = LANG
  println(s"Used Java instance field in ${instance.instanceField}")
  instance.instanceMethod(LANG)

  JavaLibrary.varargs(LANG)
  JavaLibrary.varargs(LANG, 1)
  JavaLibrary.varargs(LANG, 1, 2)
  // Scala Array cannot be directly passed as varargs.
  // The vararg type ascription can be used to unwrap the Array (or Seq or other collections) into arguments.
  JavaLibrary.varargs(LANG, Array(1, 2) : _*)
  JavaLibrary.varargs(LANG, Seq(1, 2) : _*)

  JavaLibrary.implementedInterface(() => LANG)
  JavaLibrary.extendedBaseClass(() => LANG)
  JavaLibrary.usedTypeAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedConstructorAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedMethodAnnotation(new UseJavaAnnotations)
  //JavaLibrary.usedFieldAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedParameterAnnotation(new UseJavaAnnotations)
}
