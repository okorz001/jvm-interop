(ns interop.clojure.app.java.main
  (:gen-class)
  (:import (interop.java.lib JavaBaseClass
                             JavaCheckedException
                             JavaClass
                             JavaFieldAnnotation
                             JavaInterface
                             JavaInterfaceWithMethod
                             JavaLibrary
                             JavaMethodAnnotation
                             JavaParameterAnnotation
                             JavaTypeAnnotation
                             JavaUncheckedException)))

(def lang "Clojure")

(definterface MyInterface
  (method [])
  (method [x]))

(defrecord
  ; Java annotations are applied as Clojure metadata
  ^{JavaTypeAnnotation "Clojure"}
  UseJavaAnnotations

  [^{JavaFieldAnnotation "Clojure"} field]

  MyInterface

  (^{JavaMethodAnnotation "Clojure"} method [_]
    nil)

  (method [_
           ^{JavaParameterAnnotation "Clojure"} x]
    nil))

(defn -main []
  (set! (JavaClass/staticField) lang)
  (println "Used Java static field in" (JavaClass/staticField))
  (JavaClass/staticMethod lang)

  (let [instance (JavaClass.)]
    (set! (.-instanceField instance) lang)
    (println "Used Java instance field in" (.-instanceField instance))
    (.instanceMethod instance lang))

  ; varargs method must be called with explicit array argument
  (JavaLibrary/varargs lang (object-array []))
  (JavaLibrary/varargs lang (object-array [1]))
  (JavaLibrary/varargs lang (object-array [1 2]))

  (try
    (JavaLibrary/throwsUnchecked)
    (catch JavaUncheckedException _
      (println "Caught Java unchecked exception in Clojure")))

  (try
    (JavaLibrary/throwsChecked)
    (catch JavaCheckedException _
      (println "Caught Java checked exception in Clojure")))

  (JavaLibrary/implementedInterface
    (reify JavaInterface
      ; with reify, you receive this explicitly as the first parameter
      (getLanguage [_] lang)))
  (JavaLibrary/implementedInterfaceWithMethod
    (reify JavaInterfaceWithMethod
      ; with reify, you receive this explicitly as the first parameter
      (getLanguage [_] lang)))
  (JavaLibrary/extendedBaseClass
    ; second vector is the superclass's constructor arguments
    (proxy [JavaBaseClass] []
      ; with proxy, this is already bound implicitly within every function
      (getLanguage [] lang)))
  (JavaLibrary/usedTypeAnnotation (UseJavaAnnotations. 42))
  ; cannot add annotations/metadata to the generated constructors for defrecord/deftype
  ;(JavaLibrary/usedConstructorAnnotation (UseJavaAnnotations. 42))
  (JavaLibrary/usedMethodAnnotation (UseJavaAnnotations. 42))
  (JavaLibrary/usedFieldAnnotation (UseJavaAnnotations. 42))
  (JavaLibrary/usedParameterAnnotation (UseJavaAnnotations. 42)))
