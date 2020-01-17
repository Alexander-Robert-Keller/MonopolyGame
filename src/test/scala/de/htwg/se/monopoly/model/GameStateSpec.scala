package de.htwg.se.monopoly.model.gameComponent

import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers {

  "A GameState" when {
    "new" should {

      val gameState = GameState(0, 0, 2)

      "have Integers for stateIndex, currentplayer and numberOfPlayers" in {
        gameState.stateIndex should be(0)
        gameState.currentPlayer should be(0)
        gameState.numberOfPlayers should be(2)
      }

      "have an Enumeration" in {
        gameState.ValueSet shouldBe a[Enumeration]
      }

      "have a state" in {
        gameState.state should be(gameState.MAIN_MENU)
        GameState(1, 0, 2).state should be(gameState.ROLL_DICE)
        GameState(2, 0, 2).state should be(gameState.BUY_OR_UPGRADE_PROPERTY)
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
