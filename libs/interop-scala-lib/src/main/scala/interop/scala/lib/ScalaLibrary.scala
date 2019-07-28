package interop.scala.lib

object ScalaLibrary {
  def methodWithMultipleParameterLists(from: String)(arg: Any): Unit = {
    println(s"Called Scala method with multiple parameter lists from $from with $arg")
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
