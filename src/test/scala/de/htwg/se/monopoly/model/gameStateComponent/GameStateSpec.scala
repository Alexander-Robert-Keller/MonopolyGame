package de.htwg.se.monopoly.model.gameStateComponent

import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers {

  "A GameState" when {
    "new" should {

      var gameState = GameState(0, 0, 2)

      "have Integers for stateIndex, currentplayer and numberOfPlayers" in {
        gameState.stateIndex should be(0)
        gameState.currentPlayer should be(0)
        gameState.numberOfPlayers should be(2)
      }

      "have a state" in {
        gameState.state should be(gameState.MAIN_MENU)
        gameState = GameState(1, 0, 2)
        gameState.state should be(gameState.ROLL_DICE)
        gameState = GameState(2, 0, 2)
        gameState.state should be(gameState.BUY_OR_UPGRADE_PROPERTY)
        gameState = GameState(0, 0, 2)
      }

      "have getters for currentplayer, numberOFPlayers, state and stateIndex" in {
        gameState.getStateIndex should be(0)
        gameState.getState should be(gameState.MAIN_MENU)
        gameState.getCurrentPlayer should be(0)
        gameState.getNumberOfPlayers should be(2)
      }
    }
  }
}
