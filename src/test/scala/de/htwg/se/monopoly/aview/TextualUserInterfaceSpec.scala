package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.{Controller, GameState}
import de.htwg.se.monopoly.model.Player
import org.scalatest.{Matchers, WordSpec}

class TextualUserInterfaceSpec extends WordSpec with Matchers {

  "A Monopoly Tui" should {
    val controller = new Controller
    val tui = new TextualUserInterface(controller)
    Game.init()
    "should be added to the list of notified observers" in {
      controller.subscribers.contains(tui) should be (true)
    }
    "have a method that prints the main menu" in {
      tui.displayMainMenuOptions()
    }
    "have a method that prints the ingame menu" in {
      tui.gameMenuOptions()
    }
    "process a Input '1' from the MainMenu" in {
      tui.processInputLineMainMenu("1")
      //TODO: Game.currentGameState should be ("RollDice")
    }
    "end program at Input '2' from the MainMenu" in {
      tui.processInputLineMainMenu("2")
      //end program
    }
    "process a Input _ from the MainMenu" in {
      tui.processInputLineMainMenu("x")
      //do nothing
    }
    "process a Input '1' from the GameMenu" in {
      GameState.setCurrentPlayer(0)
      Game.board.players(0) = Player(0, 0, Game.board.players(0).isJailed, Game.board.players(0).getMoney)
      val x = Game.board.players(0).getLocation
      tui.processInputLineGameMenu("1")
      val y = Game.board.players(0).getLocation
      (x < y) should be (true)
    }
    "process a Input _ from the GameMenu" in {
      tui.processInputLineGameMenu("x")
      //do nothing
    }
  }
}
