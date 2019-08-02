package interop.scala.lib

import scala.beans.{BeanProperty, BooleanBeanProperty}

object ScalaObject {
  var objectProperty: String = ""

  @BeanProperty
  var objectBeanProperty: String = ""

  @BooleanBeanProperty
  var objectBooleanBeanProperty: Boolean = false

  def objectMethod(from: String): Unit = println(s"Called Scala object method from $from")
}
