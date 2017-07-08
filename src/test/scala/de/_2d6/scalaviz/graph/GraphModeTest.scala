package de._2d6.scalaviz.graph

import org.scalatest.{FlatSpec, Matchers}

class GraphModeTest extends FlatSpec with Matchers {

  val nodeA = Node("Node A")
  val nodeB = Node("Node B")
  val nodeC = Node("Node C")
  val edgeBC = Edge(nodeB, nodeC)

  "Strict mode" should "reject a Graph with an unconnected Node" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA))

    an[IllegalArgumentException] should be thrownBy
      GraphMode.Strict.validate(graph)
  }

  it should "reject a Graph with multiple unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA, nodeB))

    an[IllegalArgumentException] should be thrownBy
      GraphMode.Strict.validate(graph)
  }

  it should "reject a Graph with both connected and unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA, nodeB, nodeC), edges = Seq(edgeBC))

    an[IllegalArgumentException] should be thrownBy
      GraphMode.Strict.validate(graph)
  }

  it should "accept a Graph without unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeB, nodeC), edges = Seq(edgeBC))

    GraphMode.Strict.validate(graph)
  }

  "Default mode" should "accept a Graph with an unconnected Node" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA))

    GraphMode.Default.validate(graph)
  }

  it should "accept a Graph with multiple unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA, nodeB))

    GraphMode.Default.validate(graph)
  }

  it should "accept a Graph with both connected and unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeA, nodeB, nodeC), edges = Seq(edgeBC))

    GraphMode.Default.validate(graph)
  }

  it should "accept a Graph without unconnected Nodes" in {
    val graph = Graph("TestGraph", nodes = Seq(nodeB, nodeC), edges = Seq(edgeBC))

    GraphMode.Default.validate(graph)
  }
}
