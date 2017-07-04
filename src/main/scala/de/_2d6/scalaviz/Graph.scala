package de._2d6.scalaviz

sealed trait GraphType

object GraphType {

  case object Graph extends GraphType
  case object Digraph extends GraphType
}

case class GraphOptions(strict: Boolean = false)

case class Graph(name: String,
                 nodes: Seq[Node] = Nil,
                 edges: Seq[Edge] = Nil,
                 graphType: GraphType = GraphType.Graph,
                 graphOptions: GraphOptions = GraphOptions()) {

  val nodeIdMap: Map[Node, Int] = nodes.zipWithIndex.toMap

  private val connector: String = graphType match {
    case GraphType.Graph => "--"
    case GraphType.Digraph => "->"
  }

  private val tabbing: String = "  "

  def asDotString(): String = {

    require(isValid)

    val nodeDefinitions = formatNodes()
    val edgeDefinitions = formatEdges()
    val graphTypeDefinition = formatGraphType()
    val strictDefinition = if (graphOptions.strict) "strict " else ""

    val dotString =
      s"""
        |$strictDefinition$graphTypeDefinition $name {
        |$tabbing$nodeDefinitions
        |$tabbing$edgeDefinitions
        |}
      """.stripMargin.trim

    dotString
  }

  def isValid: Boolean = {
    edges.forall(edge => nodes.contains(edge.from) && nodes.contains(edge.to))
  }

  private def formatGraphType() = {
    graphType match {
      case GraphType.Graph => "graph"
      case GraphType.Digraph => "digraph"
    }
  }

  private def formatEdges() = edges.map(formatEdge).mkString(s"\n$tabbing")

  private def formatEdge(edge: Edge) = {
    s"${nodeIdMap(edge.from)} $connector ${nodeIdMap(edge.to)} ${mkLabel(edge.label)}"
  }

  private def mkLabel(label: Option[String]) = {
    label.map(l => s"""[label="$l"]""").getOrElse("")
  }

  private def formatNodes() = {
    nodes.map(node => s""""${nodeIdMap(node)}\" [label=\"${node.label}\"]""").mkString(s"\n$tabbing")
  }
}