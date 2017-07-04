package de._2d6.scalaviz

import org.scalatest.{FlatSpec, Matchers}

class DigraphTest extends FlatSpec with Matchers {

  val nodeA = Node("nodeA")
  val nodeB = Node("nodeB")

  val edgeAB = Edge(nodeA, nodeB)
  val edgeBA = Edge(nodeB, nodeA)
  val edgeAA = Edge(nodeA, nodeA)

  val name = "TestGraph"
  val graph: Graph = Graph(name)
  val digraph: Graph = graph.copy(graphType = GraphType.Digraph)

  "A Graph" should "default to 'Graph' type" in {
    graph.graphType shouldBe GraphType.Graph
  }

  it should "contain its type" in {
    graph.asDotString() should include ("graph")
    graph.asDotString() should not include "digraph"
  }

  it should "contain its name" in {
    graph.asDotString() should include (s"graph ${graph.name}")
  }

  it should "contain a node definition" in {
    val graphWithNode = graph.copy(nodes = Seq(nodeA))

    graphWithNode.asDotString() should include (nodeDefinition(0, nodeA))
  }

  it should "contain node definitions" in {
    val graphWithNodes = graph.copy(nodes = Seq(nodeA, nodeB))

    graphWithNodes.asDotString() should include (nodeDefinition(0, nodeA))
    graphWithNodes.asDotString() should include (nodeDefinition(1, nodeB))
  }

  it should "contain node definitions with increasing numerical id" in {
    val graphWithNodes = graph.copy(nodes = Seq(nodeB, nodeA))

    graphWithNodes.asDotString() should include (nodeDefinition(0, nodeB))
    graphWithNodes.asDotString() should include (nodeDefinition(1, nodeA))
  }

  it should "contain an edge definition" in {
    val graphWithEdge = graph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

    graphWithEdge.asDotString() should include (edgeDefinition(0, 1))
  }

  it should "contain edge definitions" in {
    val graphWithEdges = graph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB, edgeBA))

    graphWithEdges.asDotString() should include (edgeDefinition(0, 1))
    graphWithEdges.asDotString() should include (edgeDefinition(1, 0))
  }

  it should "contain self-referential edge definitions" in {
    val graphWithEdge = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeAA))

    graphWithEdge.asDotString() should include (edgeDefinition(0, 0))
  }

  it should "reject edges referencing a node not contained in it" in {
    val graphWithInvalidEdge = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeAB))

    an [IllegalArgumentException] should be thrownBy graphWithInvalidEdge.asDotString()
  }

  it should "reject edges referencing two nodes not contained in it" in {
    val graphWithInvalidEdge = graph.copy(edges = Seq(edgeAA))

    an [IllegalArgumentException] should be thrownBy graphWithInvalidEdge.asDotString()
  }

  it should "not be strict by default" in {
    graph.asDotString() should not include "strict"
  }

  it should "be strict if specified in the options" in {
    val strictGraph = graph.copy(graphOptions = GraphOptions(strict = true))

    strictGraph.asDotString() should startWith ("strict")
  }

  it should "contain edge labels" in {
    val edgeWithLabel = edgeAA.copy(label = Some("edgeLabel"))
    val graphWithEdgeLabel = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeWithLabel))

    graphWithEdgeLabel.asDotString() should include (edgeDefinition(0,0) + """ [label="edgeLabel"]""")
  }

  "A directed Graph" should "contain its type" in {
    digraph.asDotString() should include ("digraph")
  }

  it should "contain its name" in {
    digraph.asDotString() should include (s"digraph ${digraph.name}")
  }

  it should "contain an edge definition" in {
    val digraphWithEdge = digraph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

    digraphWithEdge.asDotString() should include (directedEdgeDefinition(0, 1))
  }

  it should "contain edge definitions" in {
    val digraphWithEdges = digraph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB, edgeBA))

    digraphWithEdges.asDotString() should include (directedEdgeDefinition(0, 1))
    digraphWithEdges.asDotString() should include (directedEdgeDefinition(1, 0))
  }

  it should "contain self-referential edge definitions" in {
    val digraphWithEdge = digraph.copy(nodes = Seq(nodeA), edges = Seq(edgeAA))

    digraphWithEdge.asDotString() should include (directedEdgeDefinition(0, 0))
  }

  private def nodeDefinition(id: Int, node: Node) = {
    s"""\"$id\" [label=\"${node.label}\"]"""
  }

  private def edgeDefinition(fromId: Int, toId: Int) = {
    s"$fromId -- $toId"
  }

  private def directedEdgeDefinition(fromId: Int, toId: Int) = {
    s"$fromId -> $toId"
  }
}