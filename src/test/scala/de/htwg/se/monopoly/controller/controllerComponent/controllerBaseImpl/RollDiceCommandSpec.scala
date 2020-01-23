package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}

class RollDiceCommandSpec extends WordSpec with Matchers {
  "a RollDiceCommand" when {
    "new" should {

      val controller = new Controller
      val rollDiceCommand = new RollDiceCommand(controller)

      "have a board, dice and gameState" in {
        rollDiceCommand.board shouldBe a[Board]
        rollDiceCommand.dice shouldBe a[Dice]
        rollDiceCommand.gameState shouldBe a[GameState]
      }

      "have a method undoStep which undos the command" in {
        controller.board = controller.board.init()
        rollDiceCommand.undoStep()
        controller.board.playerList.length should not be rollDiceCommand.board.playerList.length
      }

      "have a method to redo the last undo command" in {
        controller.board = controller.board.init()
        rollDiceCommand.undoStep()
        rollDiceCommand.redoStep()
        controller.board.playerList.length should be (rollDiceCommand.board.playerList.length)
      }
    }
  }
}
