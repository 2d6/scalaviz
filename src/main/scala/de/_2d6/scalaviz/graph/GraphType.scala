package de._2d6.scalaviz.graph

/**
  * The type of a [[Graph]]
  */
sealed trait GraphType

/**
  * The type of a [[Graph]]
  */
object GraphType {

  /**
    * An undirected [[Graph]]
    */
  case object Graph extends GraphType

  /**
    * A directed [[Graph]]
    */
  case object Digraph extends GraphType

}
