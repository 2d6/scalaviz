package de._2d6.scalaviz.rendering

case class CommandExecutionException(private val message: String = "",
                                     private val cause: Throwable = None.orNull)
  extends RuntimeException(message, cause)
