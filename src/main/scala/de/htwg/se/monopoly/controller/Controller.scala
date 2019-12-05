package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.util.Publisher

class Controller extends Publisher {

  val mainMenu = "option | description\n [1]   | Start new game\n [2]   | Exit game"

  val gameMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val jailMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val wrongCommand = "Command Option does not exist"

  def stringGameBoard(): String = {
    Game.board.toString
  }

  def rollDie(): String = {
    Game.board.rollDice()
    val currentDice = Game.board.getDice
    if (Game.board.getDice.gotDoublets()) {
      if (currentDice.getEyes + Game.board.players(GameState.currentPlayer).getLocation == Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
        notifyObservers()
        GameState.nextState()
        return currentDice.toString + "You landed on Go: here are 400$\n"
      }
      if (currentDice.getEyes + Game.board.players(GameState.currentPlayer).getLocation > Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
        notifyObservers()
        GameState.nextState()
        return currentDice.toString + "You went over Go: here are 200$\n"
      }
      Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
      notifyObservers()
      GameState.nextState()
      currentDice.toString
    } else {
      if (currentDice.getEyes + Game.board.players(GameState.currentPlayer).getLocation == Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
        notifyObservers()
        GameState.nextState()
        return currentDice.toString + "You went over Go: here are 400$\n"
      }
      if (currentDice.getEyes + Game.board.players(GameState.currentPlayer).getLocation > Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
        notifyObservers()
        GameState.nextState()
        return currentDice.toString + "You went over Go: here are 200$\n"
      }
      Game.board.movePlayer(currentDice.getEyes, GameState.currentPlayer)
      notifyObservers()
      GameState.nextState()
      currentDice.toString
    }
  }

  def exitCurrentGame(): String = {
    Game.setRunning(false)
    GameState.setState("MAIN_MENU")
    exitCurrentGameMessage
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"

  def initializeGame(): Unit = {
    Game.init()
  }
}
