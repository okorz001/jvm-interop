package interop.scala

package object lib {
  var packageProperty: String = ""

  def packageMethod(from: String): Unit = println(s"Calling Scala package method from $from")
}
