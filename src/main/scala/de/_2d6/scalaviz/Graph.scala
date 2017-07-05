package de._2d6.scalaviz

case class Graph(name: String,
                 nodes: Seq[Node] = Nil,
                 edges: Seq[Edge] = Nil,
                 graphType: GraphType = GraphType.Graph,
                 mode: GraphMode = GraphMode.Default,
                 override val attributes: Map[String, String] = Map.empty)
  extends AttributeHolder {

  override def withAttribute(key: String, value: String): Graph = {
    this.copy(attributes = attributes + (key -> value))
  }

  override def withAttributes(newAttributes: Map[String, String]): Graph = {
    this.copy(attributes = attributes ++ newAttributes)
  }
}