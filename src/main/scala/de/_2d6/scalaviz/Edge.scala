package de._2d6.scalaviz

object Edge {

  def apply(from: Node, to: Node): Edge = {
    new Edge(from, to, None)
  }

  def apply(from: Node, to: Node, label: String): Edge = {
    new Edge(from, to, Some(label))
  }
}

case class Edge(from: Node, to: Node, label: Option[String] = None)
