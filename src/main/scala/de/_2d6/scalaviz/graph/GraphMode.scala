package de._2d6.scalaviz.graph

/**
  * The mode with which a [[Graph]] should be rendered
  */
sealed trait GraphMode {

  /**
    * Validate the given graph.
    * @throws IllegalArgumentException if the graph does not conform to this mode
    * @param graph The graph to validate
    */
  def validate(graph: Graph): Unit
}

/**
  * The mode with which a [[Graph]] should be rendered
  */
object GraphMode {

  /**
    * The default mode, allowing unconnected [[Node]]s within the [[Graph]]
    */
  case object Default extends GraphMode {

    override def validate(graph: Graph): Unit = {
      // Default graphs do not need mode validation
    }
  }

  /**
    * Strict mode, prohibiting unconnected [[Node]]s within the [[Graph]]
    */
  case object Strict extends GraphMode {

    override def validate(graph: Graph): Unit = {
      val connectedNodes = graph.edges.flatMap(edge => Seq(edge.from, edge.to))
      val allNodesAreConnected = graph.nodes.forall(connectedNodes.contains)
      require(allNodesAreConnected, "Graphs with strict mode must not contain unconnected nodes")
    }
  }

}