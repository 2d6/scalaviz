package de._2d6.scalaviz

trait AttributeHolder {

  val attributes: Map[String, String] = Map.empty

  def withAttribute(key: String, value: String): AttributeHolder

  def withAttributes(newAttributes: Map[String, String]): AttributeHolder

}
