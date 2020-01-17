package de.htwg.se.monopoly.util.utilityComponent

class Utility extends UtilityInterface {
  override def createUndoManagerInstance(): UndoManager = UtilityFactory.getInstance("UndoManager").asInstanceOf[UndoManager]
}
