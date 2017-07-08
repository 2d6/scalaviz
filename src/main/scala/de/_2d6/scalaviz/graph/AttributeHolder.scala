package de._2d6.scalaviz.graph {

  /**
    * Entities extending this trait contain a Map of attribute keys and values
    */
  trait AttributeHolder[T] {

    val attributes: Map[String, String] = Map.empty

    /**
      * Function instantiating a copy of the instance with a given Map of attributes
      */
    protected val copyFunction: Map[String, String] => T

    /**
      * Create a copy of this instance with the given attribute key/value pair. The previous value of the attribute will
      * be overridden by the given value.
      *
      * @param key   The attribute key
      * @param value The attribute value
      * @return A copy of this instance with the given attribute key/value pair.
      */
    def withAttribute(key: String, value: String): T = {
      copyWithAttributes(Map(key -> value))
    }

    /**
      * Create a copy of this instance with the given attribute key/value pairs. The previous values of the attributes
      * will be overridden by the given values.
      *
      * @param newAttributes A Map of String attribute keys and values
      * @return A copy of this instance with the given attribute key/value pairs.
      */
    def withAttributes(newAttributes: Map[String, String]): T = {
      copyWithAttributes(newAttributes)
    }

    private def copyWithAttributes(newAttributes: Map[String, String]): T = {
      copyFunction(attributes ++ newAttributes)
    }

  }

}
