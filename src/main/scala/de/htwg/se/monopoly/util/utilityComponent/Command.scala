package de.htwg.se.monopoly.util.utilityComponent

trait Command {
  // def doStep(): Unit

  def redoStep(): Unit

  def undoStep(): Unit
}
