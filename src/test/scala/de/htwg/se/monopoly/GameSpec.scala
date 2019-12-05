package de.htwg.se.monopoly

import de.htwg.se.monopoly.aview.TextualUserInterface
import de.htwg.se.monopoly.controller.{Controller, GameState}
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A game" should {
    val controller = new Controller
    val tui = new TextualUserInterface(controller)
    val args = Array[String]("1", "2")
    "have a central method run for the main menu loop, which u can give the args parameter" in {
      Game.run(args)
    }
    "have a method gameLoop, that runs the current game. It can be given a parameter for testing" in {
      Game.gameLoop(tui, args(1))
    }
    "have a value which represents the number of players" in {
      Game.numberOfPlayers should be (2)
    }
    "have a boolean which indicates if the game is running or in the main menu" in {
      Game.setRunning(false)
      Game.running should be (false)
    }
    "have a method to set running to a specific value" in {
      Game.setRunning(true)
      Game.running should be (true)
      Game.setRunning(false)
    }
    "have a nice boolean representation for running" in {
      Game.isRunning should be (false)
    }
    "have a way to initialize a new Game" in {
      GameState.setState("MAIN_MENU")
      Game.init()
      GameState.getCurrentPlayer should be (0)
      GameState.numberOfPlayer should be (2)
      GameState.state should be (GameState.ROLL_DICE)
    }
  }
}
