package de._2d6.scalaviz.graph

/**
  * A Graphviz graph
  *
  * @param name       The name of the Graph
  * @param nodes      A Seq of [[Node]]s making up the graph. Take care that if you select the strict [[GraphMode]]
  *                   as graph mode, every node must at least be connected to one other node within the graph.
  * @param edges      A Seq of [[Edge]]s making up the graph. Any nodes referenced by these Edges '''must''' be
  *                   contained by the list of nodes from which this graph is constructed.
  * @param graphType  The type of the Graph.
  * @param mode       The mode of the Graph.
  * @param attributes A Map of String keys and values corresponding to Graphviz node attributes.
  */
case class Graph(name: String,
                 nodes: Seq[Node] = Nil,
                 edges: Seq[Edge] = Nil,
                 graphType: GraphType = GraphType.Graph,
                 mode: GraphMode = GraphMode.Default,
                 override val attributes: Map[String, String] = Map.empty)
  extends AttributeHolder[Graph] {

  validateGraph()

  private def edgesOnlyReferenceNodesWithinTheGraph: Boolean = {
    edges.forall(edge => nodes.contains(edge.from) && nodes.contains(edge.to))
  }

  private def validateGraph(): Unit = {
    require(edgesOnlyReferenceNodesWithinTheGraph)

    mode.validate(this)
  }

  protected override val copyFunction: (Map[String, String]) => Graph = a => copy(attributes = a)
}