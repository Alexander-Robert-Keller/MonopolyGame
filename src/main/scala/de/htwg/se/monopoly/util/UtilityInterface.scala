package de.htwg.se.monopoly.util

import de.htwg.se.monopoly.util.utilityComponent.Utility

trait UtilityInterface {
  def createUndoManagerInstance(): Utility
}
