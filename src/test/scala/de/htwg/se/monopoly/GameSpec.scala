package de.htwg.se.monopoly

import de.htwg.se.monopoly.aview.TextualUserInterface
import de.htwg.se.monopoly.controller.Controller
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
    "be able to update the gameboard state, if the controller notifies him" in {
      Game.currentGameState = "RollDice"
      Game.currentPlayer = 0
      Game.update()
      Game.currentPlayer should be (1)
      Game.update()
      Game.currentPlayer should be (0)
    }
  }
}
