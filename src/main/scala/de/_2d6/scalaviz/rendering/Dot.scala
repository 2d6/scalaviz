package de._2d6.scalaviz.rendering

import de._2d6.scalaviz.graph._

object Dot {

  def apply(graph: Graph): Dot = new Dot(graph)
}

class Dot(graph: Graph) {

  import graph._

  require(isValid)

  private val nodeIdMap: Map[Node, Int] = nodes.zipWithIndex.toMap

  private val edgeOperator: String = graphType match {
    case GraphType.Graph => "--"
    case GraphType.Digraph => "->"
  }

  private val tabbing: String = "  "

  override def toString: String = {

    val nodeDefinitions = formatNodes()
    val edgeDefinitions = formatEdges()
    val graphTypeDefinition = formatGraphType()
    val graphAttributeDefinitions = formatGraphAttributes()
    val strictDefinition = formatStrictness()

    val dotString =
      s"""
         |$strictDefinition$graphTypeDefinition $name {
         |$tabbing$nodeDefinitions
         |$tabbing$edgeDefinitions
         |$tabbing$graphAttributeDefinitions
         |}
      """.stripMargin.trim

    dotString
  }

  private def formatGraphType() = {
    graphType match {
      case GraphType.Graph => "graph"
      case GraphType.Digraph => "digraph"
    }
  }

  private def formatGraphAttributes() = {
    attributes.map(entry => s"""${entry._1}="${entry._2}";""").mkString("\n$tabbing")
  }

  private def formatEdges() = edges.map(formatEdge).mkString(s"\n$tabbing")

  private def formatEdge(edge: Edge) = {
    s"${nodeIdMap(edge.from)} $edgeOperator ${nodeIdMap(edge.to)}${formatAttributes(edge)};"
  }

  private def formatAttributes(entity: AttributeHolder) = {
    if (entity.attributes.isEmpty) {
      ""
    } else {
      " [" + entity.attributes.map(attr => s"""${attr._1}="${attr._2}"""").mkString(", ") + "]"
    }
  }

  private def formatNodes() = {
    nodes.map(node => s""""${nodeIdMap(node)}"${formatAttributes(node)};""").mkString(s"\n$tabbing")
  }

  private def formatStrictness() = {
    mode match {
      case GraphMode.Strict => "strict "
      case _ => ""
    }
  }

  def isValid: Boolean = {
    edges.forall(edge => nodes.contains(edge.from) && nodes.contains(edge.to))
  }

  private def mkLabel(label: Option[String]) = {
    label.map(l => s"""[label="$l"]""").getOrElse("")
  }
}

