package de._2d6.scalaviz.graph

object Edge {

  /**
    * A graph edge, spanning between two Nodes.
    *
    * @param from The [[Node]] from which the edge originates
    * @param to   The [[Node]] at which the edge ends
    * @return An Edge spanning between the given Nodes.
    */
  def apply(from: Node, to: Node): Edge = {
    new Edge(from, to)
  }

  /**
    * A graph edge, spanning between two Nodes, possessing the given attributes
    *
    * @param from       The [[Node]] from which the edge originates
    * @param to         The [[Node]] at which the edge ends
    * @param attributes A Map of String keys and values corresponding to graphviz edge attributes
    * @return An Edge spanning between the given Nodes, possessing the given attributes
    */
  def apply(from: Node, to: Node, attributes: Map[String, String]): Edge = {
    new Edge(from, to, attributes)
  }

  /**
    * A graph edge, spanning between two Nodes, possessing the given label and attributes
    *
    * @param from       The [[Node]] from which the edge originates
    * @param to         The [[Node]] at which the edge ends
    * @param label      The label of the Edge. This parameter overrides the value of the attribute 'label' if such an
    *                   attribute is defined in the attribute Map.
    * @param attributes A Map of String keys and values corresponding to graphviz edge attributes
    * @return An Edge spanning between the given Nodes, possessing the given label and attributes
    */
  def apply(from: Node, to: Node, label: String, attributes: Map[String, String] = Map.empty): Edge = {
    new Edge(from, to, attributes ++ Map("label" -> label))
  }
}

/**
  * A graph edge, spanning between two Nodes, possessing attributes.
  *
  * @param from       The [[Node]] from which the edge originates
  * @param to         The [[Node]] at which the edge ends
  * @param attributes A Map of String keys and values corresponding to graphviz edge attributes
  */
case class Edge(from: Node, to: Node, override val attributes: Map[String, String] = Map.empty) extends AttributeHolder[Edge] {

  protected override val copyFunction: (Map[String, String]) => Edge = a => copy(attributes = a)
}
