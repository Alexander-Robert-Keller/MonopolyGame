package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Injector
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.{Chance, GoToJail, Property, Space}
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.model.fileIoComponent.FileIOInterface
import de.htwg.se.monopoly.util.UndoManager
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
  "A controller" when {
    "new" should {
      val controller = new Controller
      "have a value numberOfPlayers, numberOfSpaces and wentOverGoValue" in {
        controller.numberOfPlayers should be (2)
        controller.numberOfSpaces should be (40)
        controller.wentOverGoValue  should be (200)
      }
      "have a exitCurrentGameMessage, exitProgrammMessage and rolledDoubletsMessage" in {
        controller.exitCurrentGameMessage should be ("Returns to main menu!")
        controller.exitProgramMessage should be ("Exit game!")
        controller.rolledDoubletsMessage should be ("You rolled doublets! Roll a second time")
      }
      "have a val undoMenager, board, dice, stateMachine and playerState" in {
        controller.undoManager shouldBe a[UndoManager]
        controller.board shouldBe a[Board]
        controller.dice shouldBe a[Dice]
        controller.stateMachine shouldBe a[StateMachine]
        controller.playerState shouldBe a[PlayerState]
      }
      "have an injector to create a fileIO variable" in {
        controller.injector shouldBe a[Injector]
        controller.fileIO shouldBe a[FileIOInterface]
      }
      "have a method roll Dice" in {
        controller.board = controller.board.init()
        controller.stateMachine.nextState()
        controller.rollDice()
        controller.getPlayerList(0).getLocation should be > 0
      }
      "have a method getCurrentDice" in {
        controller.dice shouldBe a [Dice]
      }
      "have a method do step" in {
        controller.doStep(new RollDiceCommand(controller))
        controller.undoManager.undoStackEmpty() should be (false)
      }
      "have a method stringrolledDice, stringGameBoard and nextPlayersRoundMessage" in {
        controller.board = controller.board.init()
        controller.stringGameBoard() shouldBe a[String]
        controller.stringRolledDice shouldBe a[String]
        controller.nextPlayersRoundMessage() shouldBe a[String]
      }
      "have a method getCurrentPlayerIndex" in {
        controller.initializeGame()
        controller.getCurrentPlayerIndex should be (0)
      }
      "have a method ExitGameMenu and exitMainMenu" in {
        controller.initializeGame()
        controller.exitGameMenu()
        controller.exitMainMenu()
      }
      "have a method getPlayerList" in {
        controller.initializeGame()
        controller.getPlayerList.isEmpty should be (false)
      }
      "have a method to initialize the game" in {
        controller.initializeGame()
        controller.board.spaces.isEmpty should be (false)
      }
      "have a method undoCommand and redo Command" in {
        controller.doStep(new BuyCommand(controller))
        controller.undoCommand()
        controller.undoCommand()
        controller.redoCommand()
        controller.redoCommand()
        controller.undoManager.redoStackEmpty() should be (true)
      }
      "have a method save-/loadGame" in {
        controller.initializeGame()
        controller.saveGame()
        val test = controller.board
        controller.loadGame()
        controller.board should not be theSameInstanceAs (test)
      }
      "have a method spaceAction" in {
        controller.initializeGame()
        controller.spaceAction()
        controller.board = controller.board.movePlayer(9, 0)
        controller.board = controller.board.buySpace(1, 9)
        controller.spaceAction()
        controller.board = controller.board.buySpace(0, 9)
        controller.spaceAction()
        controller.board.playerList(0).getMoney should be < 1500
        controller.board = controller.board.movePlayer(21, 0)
        controller.spaceAction()
        controller.board.playerList(0).isJailed should be (true)
      }
      "have a method wentOverGo" in {
        controller.initializeGame()
        controller.wentOverGo()
        controller.board.playerList(0).getMoney should be > 1500
        controller.board = controller.board.movePlayer(1 ,0)
        controller.wentOverGo()
        controller.board.playerList(0).getMoney should be > 1900
      }
      "have a method publishSpaceAction" in {
        controller.publishSpaceAction(Property("test", 0, -1, 0))
        controller.publishSpaceAction(GoToJail())
        controller.publishSpaceAction(Chance())
      }
      "have a method playerInfo and getPlayerInfo" in {
        controller.initializeGame()
        controller.getPlayerInfo(0)(0) shouldBe a[String]
        controller.playerInfo()
      }
      "have a method buyProperty and dontBuyProperty" in {
        controller.initializeGame()
        val state = controller.stateMachine.state
        controller.board = controller.board.movePlayer(9, 0)
        controller.buyProperty()
        controller.stateMachine.state should not be theSameInstanceAs (state)
      }
      "have a method endFinishedGame" in {
        controller.endFinishedGame()
        controller.board = controller.board.decreasePlayerMoney(1600, 0)
        controller.endFinishedGame()
        controller.stateMachine.state.getStateIndex should be (3)
      }
      "have a method gameFinished" in {
        controller.initializeGame()
        controller.gameFinished should be (false)
        controller.board = controller.board.decreasePlayerMoney(1600, 0)
        controller.gameFinished should be (true)
      }
      "have a method getWinner" in {
        controller.initializeGame()
        controller.board = controller.board.decreasePlayerMoney(1600, 0)
        controller.getWinner shouldBe a[String]
      }
    }
  }
}
