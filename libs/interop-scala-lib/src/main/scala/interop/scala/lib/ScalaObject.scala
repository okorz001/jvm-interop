package interop.scala.lib

object ScalaObject {
  var objectProperty: String = ""

  def objectMethod(from: String): Unit = println(s"Calling Scala object method from $from")
}
