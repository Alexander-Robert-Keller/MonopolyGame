package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Board, Dice, FreePlayerState, PlayerState}
import de.htwg.se.monopoly.util.{ExitCurrentGame, ExitProgram, RolledDice, StartGame}

import scala.swing.Publisher

object Controller extends Publisher {

  val numberOfPlayers = 2
  val numberOfSpaces = 40
  val board: Board = new Board(numberOfPlayers, numberOfSpaces)
  var dice: Dice = Dice()

  val gameState = new GameState

  def getCurrentDice: Dice = dice

  var playerState: PlayerState = FreePlayerState

  def rollDice(): Unit = {
    dice = Dice()
    playerState = playerState.determinePlayerState(board.players(gameState.getCurrentPlayer))
    playerState.rollDice(getCurrentDice, gameState.getCurrentPlayer)
    publish(new RolledDice)
    if (!getCurrentDice.hasDoublets) {
      gameState.nextState()
    }
  }

  def stringRolledDice: String = {
    playerState.stringRollDice(getCurrentDice, gameState.getCurrentPlayer)
  }

  def stringGameBoard(): String = {
    board.toString
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"


  def nextPlayersRoundMessage(): String = {
    val playerString = board.players(gameState.currentPlayer).toString
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
    gameState.startGame(numberOfPlayers)
    board.init()
    publish(new StartGame)
  }
}
