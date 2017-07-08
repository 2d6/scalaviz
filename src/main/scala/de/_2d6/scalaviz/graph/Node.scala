package de._2d6.scalaviz.graph

object Node {

  /**
    * A graph node with a label. This is a convenience constructor inserting the label into the resulting Node's
    * attributes
    *
    * @param label The label of the Node
    * @return A Node with the given label
    */
  def apply(label: String): Node = {
    Node(Map("label" -> label))
  }

  /**
    * A graph node with a label and attributes. This is a convenience constructor inserting the label into the resulting
    * Node's attributes.
    *
    * @param label The label of the Node. This parameter overrides the value of the attribute 'label' if such an
    *              attribute is defined in the attribute Map.
    * @param attributes A Map of String keys and values corresponding to graphviz node attributes
    * @return A Node with the given attributes and label
    */
  def apply(label: String, attributes: Map[String, String]): Node = {
    Node(attributes ++ Map("label" -> label))
  }
}

/**
  * A graph node with attributes
  *
  * @param attributes A Map of String keys and values corresponding to graphviz node attributes
  */
case class Node(override val attributes: Map[String, String] = Map.empty) extends AttributeHolder[Node] {

  protected override val copyFunction: (Map[String, String]) => Node = a => copy(attributes = a)
}
