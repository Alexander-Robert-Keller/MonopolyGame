package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.spacetypes.Space
import de.htwg.se.monopoly.model.{Board, Dice, Player}
import de.htwg.se.monopoly.util.{Command, ExitCurrentGame, ExitProgram, FailedRedo, FailedUndo, Redo, RolledDice, StartGame, Undo, UndoManager}

import scala.swing.Publisher

class Controller extends Publisher {

  val numberOfPlayers = 2
  val numberOfSpaces = 40
  var board: Board = Board(Vector[Space](), Vector[Player](), numberOfPlayers, numberOfSpaces)
  var dice: Dice = Dice()

  var stateMachine = new StateMachine(this)

  def getCurrentDice: Dice = dice

  var playerState: PlayerState = FreePlayerState

  def rollDice(): Unit = {
    doStep(new RollDiceCommand(this))
    dice = Dice()
    playerState = playerState.determinePlayerState(board.playerList(stateMachine.getCurrentPlayer))
    playerState.rollDice(getCurrentDice, stateMachine.getCurrentPlayer, this)
    stateMachine.nextState()
    publish(new RolledDice)
  }

  def stringRolledDice: String = {
    playerState.stringRollDice(getCurrentDice, stateMachine.getCurrentPlayer, this)
  }

  def stringGameBoard(): String = {
    board.toString
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"

  def getCurrentPlayerIndex: Int = stateMachine.getCurrentPlayer

  def nextPlayersRoundMessage(): String = {
    val playerString: String = board.playerList(stateMachine.getCurrentPlayer).toString
    "It´s " + playerString + "´s turn!\n"
  }

  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"

  def exitMainMenu(): Unit = {
    publish(new ExitProgram)
    System.exit(0)
  }

  def getPlayerList: Vector[Player] = board.playerList

  def exitGameMenu(): Unit = {
    stateMachine.setState("MAIN_MENU")
    publish(new ExitCurrentGame)
  }

  def initializeGame(): Unit = {
    //change if you can select how much players are playing
    board = board.init()
    stateMachine.startGame(numberOfPlayers)
    publish(new StartGame)
  }

  private val undoManager = new UndoManager

  def doStep(command: Command): Unit = {
    undoManager.doStep(command)
  }

  def undoCommand(): Unit = {
    if (undoManager.undoStackEmpty()) {
      publish(new FailedUndo)
    } else {
      undoManager.undoStep()
      publish(new Undo)
    }
  }

  def redoCommand(): Unit = {
    if (undoManager.redoStackEmpty()) {
      publish(new FailedRedo)
    } else {
      undoManager.redoStep()
      publish(new Redo)
    }
  }
}
