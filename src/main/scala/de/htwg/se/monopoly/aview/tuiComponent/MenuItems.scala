package de.htwg.se.monopoly.aview.tuiComponent

import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.Controller

trait MenuItems {
  val name: String

  def action(controller: ControllerInterface): Unit

  override def toString: String = name
}

object ExitMainMenuItem extends MenuItems {
  override val name: String = "Exit Game!"

  override def action(controller: ControllerInterface): Unit = controller.exitMainMenu()
}

object StartGameMenuItem extends MenuItems {
  override val name: String = "Start Game!"

  override def action(controller: ControllerInterface): Unit = controller.initializeGame()
}

object WrongCommandMenuItem extends MenuItems {
  override val name: String = "Wrong Command!"

  val wrongCommand: String = "Command Option does not exist"

  override def action(controller: ControllerInterface): Unit = println(wrongCommand)
}

object RollDiceMenuItem extends MenuItems {
  override val name: String = "Roll Dice!"

  override def action(controller: ControllerInterface): Unit = controller.rollDice()
}

object ExitGameMenuItem extends MenuItems {
  override val name: String = "Exit Game!"

  override def action(controller: ControllerInterface): Unit = controller.exitGameMenu()
}

object UndoMenuItem extends MenuItems {
  override val name: String = "Undo last Command!"

  override def action(controller: ControllerInterface): Unit = controller.undoCommand()
}

object RedoMenuItem extends MenuItems {
  override val name: String = "Redo last Undo Command!"

  override def action(controller: ControllerInterface): Unit = controller.redoCommand()
}

object SaveGameMenuItem extends MenuItems {
  override val name: String = "Save Game!"

  override def action(controller: ControllerInterface): Unit = controller.saveGame()
}

object LoadGameMenuItem extends MenuItems {
  override val name: String = "Load Game!"

  override def action(controller: ControllerInterface): Unit = controller.loadGame()
}

object PlayerInfoMenuItem extends MenuItems {
  override val name: String = "Player Info!"

  override def action(controller: ControllerInterface): Unit = controller.playerInfo()
}

object BuyPropertyMenuItem extends MenuItems {
  override val name: String = "Buy Property"

  override def action(controller: ControllerInterface): Unit = controller.buyProperty()
}

object DontBuyPropertyMenuItem extends MenuItems {
  override val name: String = "Dont buy Property"

  override def action(controller: ControllerInterface): Unit = controller.dontBuyProperty()
}