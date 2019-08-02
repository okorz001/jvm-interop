package interop.scala.lib

import scala.beans.{BeanProperty, BooleanBeanProperty}

class ScalaClass {
  var instanceProperty: String = ""

  @BeanProperty
  var instanceBeanProperty: String = ""

  @BooleanBeanProperty
  var instanceBooleanBeanProperty: Boolean = false

  def instanceMethod(from: String): Unit = println(s"Called Scala instance method from $from")
}

object ScalaClass {
  var companionProperty: String = ""

  @BeanProperty
  var companionBeanProperty: String = ""

  @BooleanBeanProperty
  var companionBooleanBeanProperty: Boolean = false

  def companionMethod(from: String): Unit = println(s"Called Scala companion method from $from")
}
