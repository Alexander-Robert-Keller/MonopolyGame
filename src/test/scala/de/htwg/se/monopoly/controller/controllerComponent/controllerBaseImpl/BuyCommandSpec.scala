package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}

class BuyCommandSpec extends WordSpec with Matchers {
  "a BuyCommand" when {
    "new" should {

      val controller = new Controller
      val buyCommand = new BuyCommand(controller)

      "have a board, dice and gameState" in {
        buyCommand.board shouldBe a[Board]
        buyCommand.gameState shouldBe a[GameState]
      }

      "have a method undoStep which undos the command" in {
        controller.board = controller.board.init()
        buyCommand.undoStep()
        controller.board.playerList.length should not be buyCommand.board.playerList.length
      }

      "have a method to redo the last undo command" in {
        controller.board = controller.board.init()
        buyCommand.undoStep()
        buyCommand.redoStep()
        controller.board.playerList.length should be (buyCommand.board.playerList.length)
      }
    }
  }
}

