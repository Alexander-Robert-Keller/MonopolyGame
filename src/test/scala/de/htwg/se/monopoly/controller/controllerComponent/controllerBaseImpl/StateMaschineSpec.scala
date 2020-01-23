package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Guice
import de.htwg.se.monopoly.MonopolyModule
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}

class StateMaschineSpec extends WordSpec with Matchers {
  "a stateMaschine" when {
    "new" should {

      val injector = Guice.createInjector(new MonopolyModule)
      val controller = injector.getInstance(classOf[ControllerInterface])

      "have a gameState Variable" in {
        controller.stateMachine.state shouldBe a[GameState]
      }

      "have a method getCurrentPlayer/-getNumberOfPlayers" in {
        controller.stateMachine.getCurrentPlayer should be (0)
        controller.stateMachine.getNumberOfPlayers should be (2)
      }

      "have a method to set the state" in {
        controller.stateMachine.setState("ROLL_DICE")
        controller.stateMachine.state.state should be (controller.stateMachine.state.ROLL_DICE)
        controller.stateMachine.setState("MAIN_MENU")
        controller.stateMachine.state.state should be (controller.stateMachine.state.MAIN_MENU)
        controller.stateMachine.setState("BUY_PROPERTY")
        controller.stateMachine.state.state should be (controller.stateMachine.state.BUY_PROPERTY)
        controller.stateMachine.setState("FINISHED_GAME")
        controller.stateMachine.state.state should be (controller.stateMachine.state.FINISHED_GAME)
      }

      "have a message for the current gameState" in {
        controller.stateMachine.setState("ROLL_DICE")
        controller.stateMachine.currentGameStateMessage should be ("Roll Dice!")
        controller.stateMachine.setState("MAIN_MENU")
        controller.stateMachine.currentGameStateMessage should be ("Main Menu:")
        controller.stateMachine.setState("BUY_PROPERTY")
        controller.stateMachine.currentGameStateMessage should be ("Buy or Upgrade your property now!")
        controller.stateMachine.setState("FINISHED_GAME")
        controller.board = controller.board.decreasePlayerMoney(2000, 0)
        controller.stateMachine.currentGameStateMessage shouldBe a[String]
      }

      "have a method to start the game" in {
        controller.stateMachine.startGame(2)
        controller.stateMachine.getNumberOfPlayers should be (2)
      }

      "have a method setCurrentPlayer/-numberOfPlayers" in {
        controller.stateMachine.setCurrentPlayer(1)
        controller.stateMachine.getCurrentPlayer should be (1)
        controller.stateMachine.setNumberOfPlayers(1)
        controller.stateMachine.getNumberOfPlayers should be (1)
      }

      "have a method to get to the next state" in {
        controller.board = controller.board.init()
        controller.stateMachine.setState("MAIN_MENU")
        controller.stateMachine.nextState()
        controller.stateMachine.state.state should be (controller.stateMachine.state.ROLL_DICE)
        controller.stateMachine.setState("ROLL_DICE")
        controller.stateMachine.nextState()
        controller.stateMachine.state.state should be (controller.stateMachine.state.ROLL_DICE)
        controller.stateMachine.setState("BUY_PROPERTY")
        controller.stateMachine.nextState()
        controller.stateMachine.state.state should be (controller.stateMachine.state.ROLL_DICE)
        controller.stateMachine.setState("BUY_PROPERTY")
        controller.stateMachine.setCurrentPlayer(0)
        controller.board = controller.board.movePlayer(9, 0)
        controller.stateMachine.nextState()
        controller.stateMachine.state.state should be (controller.stateMachine.state.ROLL_DICE)
        controller.stateMachine.setState("FINISHED_GAME")
        controller.stateMachine.nextState()
        controller.stateMachine.state.state should be (controller.stateMachine.state.FINISHED_GAME)
      }

      "have a method to get the next player" in {
        controller.board = controller.board.init()
        controller.stateMachine.startGame(2)
        controller.stateMachine.nextPlayer() should be (1)
        controller.board.decreasePlayerMoney(2000,0)
        controller.stateMachine.nextPlayer() should be (1)
      }

      "have a method buyProperty" in {
        controller.board = controller.board.init()
        controller.stateMachine.buyProperty()
        controller.board = controller.board.movePlayer(9, 0)
        controller.stateMachine.buyProperty()
      }
    }
  }
}
