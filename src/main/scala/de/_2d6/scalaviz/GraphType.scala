package de._2d6.scalaviz

sealed trait GraphType

object GraphType {

  case object Graph extends GraphType

  case object Digraph extends GraphType

}
