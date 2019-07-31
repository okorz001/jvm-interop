package interop.scala

import scala.beans.{BeanProperty, BooleanBeanProperty}

package object lib {
  var packageProperty: String = ""

  @BeanProperty
  var packageBeanProperty: String = ""

  @BooleanBeanProperty
  var packageBooleanBeanProperty: Boolean = false

  def packageMethod(from: String): Unit = println(s"Calling Scala package method from $from")
}
