package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.Controller
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
      tui.mainMenuOptions()
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
      val x = Game.board.players(0).getLocation
      tui.processInputLineGameMenu("1")
      val y = Game.board.players(0).getLocation
      x < y should be (true)
    }
    "process a Input '2' from the GameMenu" in {
      Game.setRunning(true)
      tui.processInputLineGameMenu("2")
      Game.isRunning should be (false)
    }
    "process a Input _ from the GameMenu" in {
      tui.processInputLineGameMenu("x")
      //do nothing
    }
  }
}
