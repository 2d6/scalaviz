package de._2d6.scalaviz.graph

object Node {

  def apply(label: String): Node = {
    Node(Map("label" -> label))
  }

  def apply(label: String, attributes: Map[String, String]): Node = {
    Node(attributes ++ Map("label" -> label))
  }
}

case class Node(override val attributes: Map[String, String] = Map.empty) extends AttributeHolder {

  override def withAttribute(key: String, value: String): Node = {
    this.copy(attributes = attributes + (key -> value))
  }

  override def withAttributes(newAttributes: Map[String, String]): Node = {
    this.copy(attributes = attributes ++ newAttributes)
  }
}
