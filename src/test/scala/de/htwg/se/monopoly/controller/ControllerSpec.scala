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
      "have a String that represents the answer when u use a wrong command" in {
        controller.wrongCommand should be ("Command Option does not exist")
      }
      "have a Method that returns the Gameboard as String" in {
        Game.init()
        controller.stringGameBoard() should be (Game.board.toString())
      }
      "have a method that rolls two dice and moves the player to the appropriate field (also creates a String return)" in {
        controller.rollDie()
        Game.board.players(0).getLocation should be > 0
        Game.board.players(0).getLocation should be < 13

        Game.board.init()
        controller.movePlayer(0, Game.board.players(0).getLocation, 39)
        controller.rollDie()
        Game.board.players(0).getLocation should be > 0
        Game.board.players(0).getLocation should be < 40
      }
      "have a method that moves the player to a specific location" in {
        controller.movePlayer(Game.board.players(0).getId, Game.board.players(0).getLocation, 0)
        Game.board.players(0).getLocation should be (0)
        Game.board.spaces(0).getAvailablePlayer should be ("Player 2  Player 1  ")
      }
      "have a method that initializes the game" in {
        controller.initializeGame()
        Game.isRunning should be (true)
      }
      "have a method that exits the current game" in {
        controller.exitCurrentGame() should be (controller.exitCurrentGameMessage)
        Game.isRunning should be (false)
        Game.currentGameState should be ("MainMenu")
      }
      "have a String that contains a message when u exit a Game" in {
        controller.exitCurrentGameMessage should be ("Returns to main menu!")
      }
      "have a String that contains a message when u exit the program" in {
        controller.exitProgramMessage should be ("Exit game!")
      }
      "have a way to notify his Observers" in {
        Game.currentPlayer = 0
        controller.notifyObservers()
        Game.currentPlayer = 1
      }
    }
  }
}
