package de._2d6.scalaviz.rendering

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import de._2d6.scalaviz.graph.Graph
import org.apache.commons.lang3.SystemUtils

import scala.sys.process._


object Renderer {

  require(SystemUtils.IS_OS_LINUX, "Scalaviz is only supported on Linux")

  def render(graph: Graph, engine: Engine = Engine.Dot, format: OutputFormat = OutputFormat.pdf): Path = {
    val dot = Dot(graph)
    require(dot.isValid, "The given graph is invalid. Do the edges reference nodes non contained in the graph?")

    renderToOutputFile(dot, engine, format)
  }

  private def renderToOutputFile(dot: Dot, engine: Engine, format: OutputFormat) = {
    val temporaryDotFile = Files.createTempFile("scalaviz", "graph")

    try {
      writeDotFile(dot, temporaryDotFile)
      val (command, outputFile) = buildCommand(engine, format, temporaryDotFile)
      executeCommand(command)
      Paths.get(outputFile)
    } finally {
      Files.delete(temporaryDotFile)
    }
  }

  private def writeDotFile(dot: Dot, dotFile: Path) = {
    val pw = new PrintWriter(dotFile.toFile)
    try {
      pw.write(dot.toString)
    } finally {
      pw.close()
    }
  }

  private def buildCommand(engine: Engine, format: OutputFormat, filePath: Path): (String, String) = {
    (s"$engine -T$format -O $filePath", s"$filePath.$format")
  }

  private def executeCommand(command: String) = {
    try {
      command.!
    } catch {
      case _: Throwable =>
        val errorMsg =
          s"""Failed to execute '$command' -- is Graphviz installed on your system?"""
        throw CommandExecutionException(errorMsg)
    }
  }
}
