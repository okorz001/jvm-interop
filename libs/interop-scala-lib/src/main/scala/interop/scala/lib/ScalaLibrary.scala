package interop.scala.lib

import scala.annotation.varargs

object ScalaLibrary {
  def multipleParameterLists(from: String)(arg: Any): Unit = {
    println(s"Called Scala method with multiple parameter lists from $from with $arg")
  }

  def defaultArguments(from: String, a: Any = "default", b: Any = "default"): Unit = {
    println(s"Called Scala method with default arguments from $from with a=$a and b=$b")
  }

  def varargs(from: String, args: Any*): Unit = {
    val argsStr = args.mkString("[", ", ", "]")
    println(s"Called Scala method with varargs from $from with $argsStr")
  }

  @varargs
  def varargsWithAnnotation(from: String, args: Any*): Unit = {
    val argsStr = args.mkString("[", ", ", "]")
    println(s"Called Scala @varargs method with varargs from $from with $argsStr")
  }

  def implementedFunction(it: () => String): Unit = {
    println(s"Implemented Scala function in ${it()}")
  }

  def implementedCurriedFunction(it: () => () => String): Unit = {
    println(s"Implemented Scala curried function in ${it()()}")
  }

  def implementedTrait(it: ScalaTrait): Unit = {
    println(s"Implemented Scala trait in ${it.language}")
  }

  def implementedTraitWithProperty(from: String, it: ScalaTraitWithProperty): Unit = {
    it.language = from
    println(s"Implemented Scala trait with property in ${it.language}")
  }

  def extendedBaseClass(it: ScalaBaseClass): Unit = {
    println(s"Extended Scala base class in ${it.language}")
  }
}
