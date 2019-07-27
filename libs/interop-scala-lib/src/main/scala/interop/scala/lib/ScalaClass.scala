package interop.scala.lib

class ScalaClass {
  var instanceProperty: String = ""

  def instanceMethod(from: String): Unit = {
    println(s"Calling Scala instance method from $from")
  }
}

object ScalaClass {
  var companionProperty: String = ""

  def companionMethod(from: String): Unit = {
    println(s"Calling Scala companion method from $from")
  }
}
