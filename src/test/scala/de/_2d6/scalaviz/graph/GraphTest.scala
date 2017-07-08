package de._2d6.scalaviz.graph

import org.scalatest.{FlatSpec, Matchers}

class GraphTest extends FlatSpec with Matchers {

  val nodeA = Node("Node A")
  val nodeB = Node("Node B")
  val nodeC = Node("Node C")
  val edgeBC = Edge(nodeB, nodeC)

  "A Graph" can "not be instantiated if an Edge references a node outside the graph" in {
    an[IllegalArgumentException] should be thrownBy
      Graph("TestGraph", nodes = Seq(nodeB), edges = Seq(edgeBC))
  }

  it can "not be instantiated if an Edge references multiple nodes outside the graph" in {
    an[IllegalArgumentException] should be thrownBy
      Graph("TestGraph", nodes = Nil, edges = Seq(edgeBC))
  }

  it can "be instantiated if all edges only reference nodes inside the graph" in {
      val graph = Graph("TestGraph", nodes = Seq(nodeB, nodeC), edges = Seq(edgeBC))

    graph should not be null
    graph.getClass shouldBe classOf[Graph]
  }

  it can "not be instantiated if strict mode is selected and the graph contains an unconnected node" in {
    an[IllegalArgumentException] should be thrownBy
      Graph("TestGraph", mode = GraphMode.Strict, nodes = Seq(nodeA), edges = Nil)
  }
}
