package de._2d6.scalaviz

sealed trait GraphMode

object GraphMode {

  case object Default extends GraphMode

  case object Strict extends GraphMode

}