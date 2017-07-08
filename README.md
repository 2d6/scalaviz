# Scalaviz

Scala bindings for [Graphviz](http://www.graphviz.org). Enables constructing Graphviz-compatible graph definitions and rendering to different output formats using Graphviz. Scalaviz relies on Graphviz being present on your system for rendering. However, graph definitions (Dot file contents) may be created without having the Graphviz binary available.

Scalaviz does some basic sanity checking for graphs, e.g. disallowing unconnected nodes when constructing a graph in `strict` mode.

## Prerequisites

The Graphviz binary `dot` needs to be in your Path in order to render graphs.

## Examples

### Creating a simple graph
```scala
import de._2d6.scalaviz.graph.{Edge, Graph, Node}

val nodeA = Node("Node A")
val nodeB = Node("Node B")
val edgeAB = Edge(from = nodeA, to = nodeB, "Edge")

val graph = Graph("Testgraph", nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))
```

### Creating a Dotfile definition from a graph
```scala
import de._2d6.scalaviz.graph.{Edge, Graph, Node}
import de._2d6.scalaviz.rendering.Dot

val nodeA = Node("Node A")
val nodeB = Node("Node B")
val edgeAB = Edge(from = nodeA, to = nodeB, "Edge")

val graph = Graph("Testgraph", nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

println(Dot(graph).toString)
```

This results in the following output:

```
graph Testgraph {
  "0" [label="Node A"];
  "1" [label="Node B"];
  0 -- 1 [label="Edge"];
}
```

### Rendering a graph to an output file
```scala
import de._2d6.scalaviz.graph.{Edge, Graph, Node}
import de._2d6.scalaviz.rendering.{Engine, Renderer}

val nodeA = Node("Node A")
val nodeB = Node("Node B")
val edgeAB = Edge(from = nodeA, to = nodeB, "Edge")

val graph = Graph("Testgraph", nodes = Seq(nodeA, nodeB), edges = Seq(edgeAB))

Renderer.render(graph, Engine.Circo)
```

### Rendering a directed graph with attributes as PNG file
```scala
import de._2d6.scalaviz.graph.{Edge, Graph, GraphType, Node}
import de._2d6.scalaviz.rendering.{Engine, OutputFormat, Renderer}

val nodeA = Node("Node A", attributes = Map("shape" -> "box"))
val nodeB = Node("Node B")
val edgeAB = Edge(from = nodeA, to = nodeB, "Edge", attributes = Map("arrowhead" -> "diamond"))

val graph = Graph("Testgraph",
  graphType = GraphType.Digraph,
  nodes = Seq(nodeA, nodeB),
  edges = Seq(edgeAB), attributes = Map("rankdir" -> "LR"))

Renderer.render(graph, Engine.Dot, format = OutputFormat.png)
```


## Known issues

- Scalaviz is currently only supported on Linux
- Constructing graphs with a Graphviz keyword as name (e.g. 'graph') is currently permitted but will result in an Exception being thrown when trying to rendering such a graph.
- Subgraphs are not supported yet.