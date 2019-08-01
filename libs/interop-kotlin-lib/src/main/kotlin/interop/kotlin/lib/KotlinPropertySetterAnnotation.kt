package interop.kotlin.lib

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class KotlinPropertySetterAnnotation(val from: String)
