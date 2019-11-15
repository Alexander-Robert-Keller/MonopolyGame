package de.htwg.se.monopoly.controller

import org.scalatest.{Matchers, WordSpec}

import de.htwg.se.monopoly.Game

class ControllerSpec extends WordSpec with Matchers{
  "A Controller" when {
    "new" should {
      val controller = new Controller()
      "have a String which contains the main menu options" in {
        controller.mainMenu should be("option | description\n [1]   | Start new game\n [2]   | Exit game")
      }
      "have a String which contains the in game menu options" in {
        controller.gameMenu should be("option | description\n [1]   | roll dice\n [2]   | Exit game")
      }
      "have a String which contains the jail menu options" in {
        controller.jailMenu should be("option | description\n [1]   | roll dice\n [2]   | Exit game")
      }
      "have a Method that returns the Gameboard as String" in {
        //TODO write test
      }
      "have a method that rolls two dice and returns a String with the eye count and a message if u went over Go" in {
        //TODO write Test
      }
    }
  }
}
