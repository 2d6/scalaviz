package de._2d6.scalaviz.rendering

/**
  * The engine with which to render a [[de._2d6.scalaviz.graph.Graph]]
  */
sealed trait Engine {
  def name: String

  override def toString: String = name
}

/**
  * The engine with which to render a [[de._2d6.scalaviz.graph.Graph]]
  */
case object Engine {

  case object Dot extends Engine {
    val name = "dot"
  }

  object Neato extends Engine {
    val name = "neato"
  }

  object Twopi extends Engine {
    val name = "twopi"
  }

  object Circo extends Engine {
    val name = "circo"
  }

  object Fdp extends Engine {
    val name = "fdp"
  }

  object Sfdp extends Engine {
    val name = "sfdp"
  }

  object Patchwork extends Engine {
    val name = "patchwork"
  }

  object Osage extends Engine {
    val name = "osage"
  }

}