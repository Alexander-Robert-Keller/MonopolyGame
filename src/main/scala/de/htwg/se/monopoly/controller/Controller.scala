package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.util.Observable

class Controller extends Observable {

  val mainMenu = "option | description\n [1]   | Start new game\n [2]   | Exit game"

  val gameMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val jailMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val wrongCommand = "Command Option does not exist"

  def StringGameBoard(): String = {
    Game.board.toString
  }

  val exitGameMessage: String = "Exit Game!"

  def rollDie(): String = {
    val eyes1 = Game.board.die.roll
    val eyes2 = Game.board.die.roll
    val currentLocation = Game.board.players(0).getLocation
    var newLocation = currentLocation + eyes1 + eyes2
    val eyes1_eyes2toString: String = "You rolled: %d and %d. Move %d spaces!\n".format(eyes1, eyes2, eyes1 + eyes2)
    if (newLocation > 39) {
      newLocation = newLocation % 40
      movePlayer(Game.board.players(0).getId, currentLocation, newLocation)
      if (eyes1 == eyes2) {
        if (newLocation == 0) {
          notifyObservers()
          return eyes1_eyes2toString + "You went over Go: here are 3000$"
        }
        notifyObservers()
        eyes1_eyes2toString + "You went over Go: here are 1500$"
      } else {
        notifyObservers()
        eyes1_eyes2toString
      }
    } else {
      movePlayer(Game.board.players(0).getId, currentLocation, newLocation)
      notifyObservers()
      eyes1_eyes2toString
    }
  }

  def movePlayer(playerID: Int, currentLocation: Int, newLocation: Int): Unit = {
    Game.board.spaces(currentLocation).removePlayer(Game.board.players(0))
    Game.board.spaces(newLocation).addPlayer(Game.board.players(0))
    Game.board.players(0).setLocation(newLocation)
  }
}
