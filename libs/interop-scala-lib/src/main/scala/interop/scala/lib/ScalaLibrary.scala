package interop.scala.lib

object ScalaLibrary {
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
