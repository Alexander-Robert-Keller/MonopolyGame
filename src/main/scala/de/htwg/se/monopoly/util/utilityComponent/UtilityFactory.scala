package de.htwg.se.monopoly.util.utilityComponent

object UtilityFactory {
  def getInstance(className: String): Utility = {
    if (className.equals("UndoManager"))
      return new UndoManager
    null
  }
}
