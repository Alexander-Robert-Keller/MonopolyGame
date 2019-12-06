package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.util.Publisher
import de.htwg.se.monopoly.util.InitializerFacade

class Controller extends Publisher {

  val mainMenu = "option | description\n [1]   | Start new game\n [2]   | Exit game"

  val gameMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val jailMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val wrongCommand = "Command Option does not exist"

  def stringGameBoard(): String = {
    Game.board.toString
  }

  var playerState: PlayerState = FreePlayerState

  def rollDice(): String = {
    Game.board.rollDice()
    val currentDice = Game.board.getDice
    playerState = playerState.determinePlayerState(Game.board.players(GameState.currentPlayer))
    val message = playerState.rollDice(currentDice)
    notifyObservers()
    GameState.nextState()
    message
  }

  def exitCurrentGame(): String = {
    Game.setRunning(false)
    GameState.setState("MAIN_MENU")
    exitCurrentGameMessage
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"

  def nextPlayersRoundMessage(): String = {
    val playerString = Game.board.players(GameState.currentPlayer).toString
    "It´s " + playerString + "´s turn!\n"
  }

  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"

  def initializeGame(): Unit = {
    InitializerFacade.initializeGame()
  }
}
