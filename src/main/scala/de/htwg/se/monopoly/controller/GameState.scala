package de.htwg.se.monopoly.controller

object GameState extends Enumeration {
  var state: GameState.Value = Value
  val ROLL_DICE, MAIN_MENU, BUY_OR_UPGRADE_PROPERTY = Value
  state = MAIN_MENU

  def setState(e: String): Unit = {
    e match {
      case "ROLL_DICE" => state = ROLL_DICE
      case "BUY_OR_UPGRADE_PROPERTY" => state = BUY_OR_UPGRADE_PROPERTY
      case "MAIN_MENU" => state = MAIN_MENU
    }
  }

  def nextState(): Unit = {
    state match {
      case MAIN_MENU => state = ROLL_DICE
      case ROLL_DICE => state = BUY_OR_UPGRADE_PROPERTY
      case BUY_OR_UPGRADE_PROPERTY => state = ROLL_DICE
    }
  }

  def currentGameStateMessage: String = {
    state match {
      case ROLL_DICE => "Roll Dice!"
      case MAIN_MENU => "Main Menu:"
      case BUY_OR_UPGRADE_PROPERTY => "Buy or Upgrade your property now!"
    }
  }
}
