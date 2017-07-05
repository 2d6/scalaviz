package de._2d6.scalaviz

import org.scalatest.{FlatSpec, Matchers}

class DotTest extends FlatSpec with Matchers {

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
    Dot(graph).toString() should include("graph")
    Dot(graph).toString() should not include "digraph"
  }

  it should "contain its name" in {
    Dot(graph).toString() should include(s"graph ${graph.name}")
  }

  it should "contain a node definition" in {
    val graphWithNode = graph.copy(nodes = Seq(nodeA))

    Dot(graphWithNode).toString() should include(nodeDefinition(0, Some("nodeA")))
  }

  it should "contain node definitions" in {
    val graphWithNodes = graph.copy(nodes = Seq(nodeA, nodeB))

    Dot(graphWithNodes).toString() should include(nodeDefinition(0, Some("nodeA")))
    Dot(graphWithNodes).toString() should include(nodeDefinition(1, Some("nodeB")))
  }

  it should "contain node definitions with increasing numerical id" in {
    val graphWithNodes = graph.copy(nodes = Seq(nodeB, nodeA))

    Dot(graphWithNodes).toString() should include(nodeDefinition(0, Some("nodeB")))
    Dot(graphWithNodes).toString() should include(nodeDefinition(1, Some("nodeA")))
  }

  it should "contain an edge definition" in {
    val graphWithEdge = graph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

    Dot(graphWithEdge).toString() should include(edgeDefinition(0, 1))
  }

  it should "contain edge definitions" in {
    val graphWithEdges = graph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB, edgeBA))

    Dot(graphWithEdges).toString() should include(edgeDefinition(0, 1))
    Dot(graphWithEdges).toString() should include(edgeDefinition(1, 0))
  }

  it should "contain self-referential edge definitions" in {
    val graphWithEdge = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeAA))

    Dot(graphWithEdge).toString() should include(edgeDefinition(0, 0))
  }

  it should "reject edges referencing a node not contained in it" in {
    val graphWithInvalidEdge = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeAB))

    an[IllegalArgumentException] should be thrownBy Dot(graphWithInvalidEdge).toString()
  }

  it should "reject edges referencing two nodes not contained in it" in {
    val graphWithInvalidEdge = graph.copy(edges = Seq(edgeAA))

    an[IllegalArgumentException] should be thrownBy Dot(graphWithInvalidEdge).toString()
  }

  it should "not be strict by default" in {
    Dot(graph).toString() should not include "strict"
  }

  it should "be strict if specified in the options" in {
    val strictGraph = graph.copy(mode = GraphMode.Strict)

    Dot(strictGraph).toString() should startWith("strict")
  }

  it should "contain an edge attribute" in {
    val edgeWithAttribute = edgeAA.withAttribute("edgeAttributeKey", "edgeAttributeValue")
    val graphWithEdgeAttribute = graph.copy(nodes = Seq(nodeA), edges = Seq(edgeWithAttribute))

    Dot(graphWithEdgeAttribute).toString() should include(edgeDefinition(0, 0) + """ [edgeAttributeKey="edgeAttributeValue"]""")
  }

  it should "contain a node attribute" in {
    val graphWithNodeAtttribute = graph.copy(nodes = Seq(nodeA.copy(attributes = Map("nodeAttributeKey" -> "nodeAttributeValue"))))

    Dot(graphWithNodeAtttribute).toString() should include(s""""0" [nodeAttributeKey="nodeAttributeValue"]""")
  }

  it should "contain a graph attribute" in {
    val graphWithAttribute = graph.copy(attributes = Map[String, String]("attributeKey" -> "attributeValue"))

    Dot(graphWithAttribute).toString() should include("""attributeKey="attributeValue";""")
  }

  it should "contain graph attributes" in {
    val graphWithAttribute = graph.copy(attributes = Map[String, String](
      "attributeKey1" -> "attributeValue1",
      "attributeKey2" -> "attributeValue2"))

    Dot(graphWithAttribute).toString() should include("""attributeKey1="attributeValue1";""")
    Dot(graphWithAttribute).toString() should include("""attributeKey2="attributeValue2";""")
  }

  "A directed Graph" should "contain its type" in {
    Dot(digraph).toString() should include("digraph")
  }

  it should "contain its name" in {
    Dot(digraph).toString() should include(s"digraph ${digraph.name}")
  }

  it should "contain an edge definition" in {
    val digraphWithEdge = digraph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

    Dot(digraphWithEdge).toString() should include(directedEdgeDefinition(0, 1))
  }

  it should "contain edge definitions" in {
    val digraphWithEdges = digraph.copy(nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB, edgeBA))

    Dot(digraphWithEdges).toString() should include(directedEdgeDefinition(0, 1))
    Dot(digraphWithEdges).toString() should include(directedEdgeDefinition(1, 0))
  }

  it should "contain self-referential edge definitions" in {
    val digraphWithEdge = digraph.copy(nodes = Seq(nodeA), edges = Seq(edgeAA))

    Dot(digraphWithEdge).toString() should include(directedEdgeDefinition(0, 0))
  }

  private def nodeDefinition(id: Int, label: Option[String]) = {
    val attributes = label.map(l => s""" [label="$l"]""").getOrElse("")
    s""""$id"$attributes;"""
  }

  private def edgeDefinition(fromId: Int, toId: Int) = {
    s"$fromId -- $toId"
  }

  private def directedEdgeDefinition(fromId: Int, toId: Int) = {
    s"$fromId -> $toId"
  }
}