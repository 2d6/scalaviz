package de._2d6.scalaviz.rendering

/**
  * Signifies that the execution of a system level command failed
  * @param message The message
  * @param cause The cause
  */
case class CommandExecutionException(private val message: String = "",
                                     private val cause: Throwable = None.orNull)
  extends RuntimeException(message, cause)
