package de.htwg.se.monopoly.controller

import com.sun.jdi.Value
import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers {

  "A GameState" should {
    "have a variable  which represents the current game state and has a specific state on startup" in {
      GameState.state should be (GameState.MAIN_MENU)
    }
    "have a method which can set the game state" in {
      GameState.setState("MAIN_MENU")
      GameState.state should be (GameState.MAIN_MENU)
      GameState.setState("BUY_OR_UPGRADE_PROPERTY")
      GameState.state should be (GameState.BUY_OR_UPGRADE_PROPERTY)
      GameState.setState("ROLL_DICE")
      GameState.state should be (GameState.ROLL_DICE)
    }
    "have a method which sets the next game state" in {
      GameState.setState("MAIN_MENU")
      GameState.nextState()
      GameState.state should be (GameState.ROLL_DICE)
      GameState.nextState()
      GameState.state should be (GameState.BUY_OR_UPGRADE_PROPERTY)
      GameState.nextState()
      GameState.state should be (GameState.ROLL_DICE)
    }
    "have a method which displays a game state message" in {
      GameState.setState("MAIN_MENU")
      GameState.currentGameStateMessage should be ("Main Menu:")
      GameState.setState("BUY_OR_UPGRADE_PROPERTY")
      GameState.currentGameStateMessage should be ("Buy or Upgrade your property now!")
      GameState.setState("ROLL_DICE")
      GameState.state should be ("Roll Dice!")
    }
  }
}
