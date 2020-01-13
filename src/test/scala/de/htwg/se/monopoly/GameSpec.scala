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
    "have a value which represents the number of players" in {
      Game.numberOfPlayers should be (2)
    }
    "have a way to initialize a new Game" in {
    }
  }
}
