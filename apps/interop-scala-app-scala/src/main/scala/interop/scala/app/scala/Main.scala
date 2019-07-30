package interop.scala.app.scala

import interop.scala.lib.{ScalaBaseClass, ScalaClass, ScalaLibrary, ScalaObject, ScalaTrait, ScalaTraitWithProperty, packageMethod, packageProperty}

object Main extends App {
  // The final is required here to make a constant expression. (???)
  final val LANG = "Scala"

  packageProperty = LANG
  println(s"Using Scala package property in $packageProperty")
  packageMethod(LANG)

  ScalaClass.companionProperty = LANG
  println(s"Using Scala object property in ${ScalaClass.companionProperty}")
  ScalaClass.companionMethod(LANG)

  val instance = new ScalaClass
  instance.instanceProperty = LANG
  println(s"Using Scala instance property in ${instance.instanceProperty}")
  instance.instanceMethod(LANG)

  ScalaObject.objectProperty = LANG
  println(s"Using Scala object property in ${ScalaObject.objectProperty}")
  ScalaObject.objectMethod(LANG)

  ScalaLibrary.multipleParameterLists(LANG)(1)

  ScalaLibrary.defaultArguments(LANG, 1, 2)
  ScalaLibrary.defaultArguments(LANG, 1)
  ScalaLibrary.defaultArguments(LANG)
  // named arguments allow skipping positional arguments with defaults
  ScalaLibrary.defaultArguments(LANG, b = 2)
  // named arguments can be in any order
  ScalaLibrary.defaultArguments(LANG, b = 2, a = 1)

  ScalaLibrary.implementedFunction(() => LANG)
  ScalaLibrary.implementedCurriedFunction(() => () => LANG)
  ScalaLibrary.implementedTrait(new ScalaTrait {
    override def language: String = LANG
  })
  ScalaLibrary.implementedTraitWithProperty(LANG, new ScalaTraitWithProperty {
    override var language: String = ""
  })
  ScalaLibrary.extendedBaseClass(new ScalaBaseClass {
    override def language: String = LANG
  })
}
