package de.htwg.se.monopoly.controller

import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers {

  "A GameState" should {
    "have a variable  which represents the current game state and has a specific state on startup" in {
      GameState.setState("MAIN_MENU")
      GameState.state should be (GameState.MAIN_MENU)
    }
    "have a variable that represents the current player" in {
      GameState.setCurrentPlayer(0)
      GameState.currentPlayer should be (0)
    }
    "have a method that sets the current player" in {
      GameState.setCurrentPlayer(1)
      GameState.currentPlayer should be (1)
    }
    "have a nice Int representation of the current player" in {
      GameState.getCurrentPlayer should be (1)
    }
    "have a variable which represents the total number of players in the current game" in {
      GameState.setNumberOfPlayers(2)
      GameState.numberOfPlayers should be (2)
    }
    "have a method which sets the total number of players in the current game" in {
      GameState.setNumberOfPlayers(100)
      GameState.numberOfPlayers should be (100)
      GameState.setNumberOfPlayers(2)
    }
    "have a method which sets the current player to the next one" in {
      GameState.setCurrentPlayer(0)
      GameState.nextPlayer()
      GameState.getCurrentPlayer should be (1)
      GameState.nextPlayer()
      GameState.getCurrentPlayer should be (0)
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
      GameState.state should be (GameState.ROLL_DICE) //reset when buy and upgrade property is implemented
      GameState.setState("BUY_OR_UPGRADE_PROPERTY")  //delete when above
      GameState.nextState()
      GameState.state should be (GameState.ROLL_DICE)
    }
    "have a method which displays a game state message" in {
      GameState.setState("MAIN_MENU")
      GameState.currentGameStateMessage should be ("Main Menu:")
      GameState.setState("BUY_OR_UPGRADE_PROPERTY")
      GameState.currentGameStateMessage should be ("Buy or Upgrade your property now!")
      GameState.setState("ROLL_DICE")
      GameState.currentGameStateMessage should be ("Roll Dice!")
    }
  }
}