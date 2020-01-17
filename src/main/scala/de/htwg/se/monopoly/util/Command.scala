package de.htwg.se.monopoly.util

trait Command {
  // def doStep(): Unit

  def redoStep(): Unit

  def undoStep(): Unit
}
