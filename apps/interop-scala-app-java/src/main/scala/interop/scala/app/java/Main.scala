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
  println(s"Using Java static field in ${JavaClass.staticField}")
  JavaClass.staticMethod(LANG)

  val instance = new JavaClass
  instance.instanceField = LANG
  println(s"Using Java instance field in ${instance.instanceField}")
  instance.instanceMethod(LANG)

  JavaLibrary.implementedInterface(() => LANG)
  JavaLibrary.extendedBaseClass(() => LANG)
  JavaLibrary.usedTypeAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedConstructorAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedMethodAnnotation(new UseJavaAnnotations)
  //JavaLibrary.usedFieldAnnotation(new UseJavaAnnotations)
  JavaLibrary.usedParameterAnnotation(new UseJavaAnnotations)
}
