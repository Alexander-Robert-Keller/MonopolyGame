package de.htwg.se.monopoly.util.utilityComponent

import de.htwg.se.monopoly.util.UtilityInterface

class Utility extends UtilityInterface {
  override def createUndoManagerInstance(): UndoManager = UtilityFactory.getInstance("UndoManager").asInstanceOf[UndoManager]
}
