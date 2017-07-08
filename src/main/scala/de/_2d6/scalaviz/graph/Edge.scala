package de._2d6.scalaviz.graph

object Edge {

  def apply(from: Node, to: Node): Edge = {
    new Edge(from, to)
  }

  def apply(from: Node, to: Node, attributes: Map[String, String]): Edge = {
    new Edge(from, to, attributes)
  }

  def apply(from: Node, to: Node, label: String, attributes: Map[String, String] = Map.empty): Edge = {
    new Edge(from, to, attributes ++ Map("label" -> label))
  }
}

case class Edge(from: Node, to: Node, override val attributes: Map[String, String] = Map.empty) extends AttributeHolder {

  override def withAttribute(key: String, value: String): Edge = {
    this.copy(attributes = attributes + (key -> value))
  }

  override def withAttributes(newAttributes: Map[String, String]): Edge = {
    this.copy(attributes = attributes ++ newAttributes)
  }
}
