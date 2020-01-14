package de.htwg.se.monopoly.aview.tui

import de.htwg.se.monopoly.controller.Controller

trait MenuItems {
  val name: String
  def action(): Unit
  override def toString: String = name
}

object ExitMainMenuItem extends MenuItems {
  override val name: String = "Exit Game!"

  override def action(): Unit = Controller.exitMainMenu()
}

object StartGameMenuItem extends MenuItems {
  override val name: String = "Start Game!"

  override def action(): Unit = Controller.initializeGame()
}

object WrongCommandMenuItem extends MenuItems {
  override val name: String = "Wrong Command!"

  val wrongCommand: String = "Command Option does not exist"
  override def action(): Unit = println(wrongCommand)
}

object RollDiceMenuItem extends MenuItems {
  override val name: String = "Roll Dice!"

  override def action(): Unit = Controller.rollDice()
}

object ExitGameMenuItem extends MenuItems {
  override val name: String = "Exit Game!"

  override def action(): Unit = Controller.exitGameMenu()
}
