package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.{Dice, FreePlayerState, PlayerState}
import de.htwg.se.monopoly.util.{ExitCurrentGame, ExitProgram, RolledDice, StartGame}

import scala.swing.Publisher

object Controller extends Publisher {

  val gameState = new GameState

  def getCurrentDice: Dice = Game.dice

  var playerState: PlayerState = FreePlayerState

  def rollDice(): Unit = {
    Game.dice = Dice()
    playerState = playerState.determinePlayerState(Game.board.players(gameState.getCurrentPlayer))
    playerState.rollDice(getCurrentDice, gameState.getCurrentPlayer)
    publish(new RolledDice)
    gameState.nextState()
  }

  def stringRolledDice: String = {
    playerState.stringRollDice(getCurrentDice, gameState.getCurrentPlayer)
  }

  def stringGameBoard(): String = {
    Game.board.toString
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"


  def nextPlayersRoundMessage(): String = {
    val playerString = Game.board.players(gameState.currentPlayer).toString
    "It´s " + playerString + "´s turn!\n"
  }

  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"

  def exitMainMenu(): Unit = {
    publish(new ExitProgram)
    System.exit(0)
  }

  def exitGameMenu(): Unit = {
    gameState.setState("MAIN_MENU")
    publish(new ExitCurrentGame)
  }

  def initializeGame(): Unit = {
    //change if you can select how much players are playing
    gameState.startGame(Game.numberOfPlayers)
    Game.board.init()
    publish(new StartGame)
  }
}
