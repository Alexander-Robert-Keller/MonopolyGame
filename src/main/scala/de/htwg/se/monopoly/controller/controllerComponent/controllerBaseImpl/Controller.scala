package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes._
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.util._

import scala.swing.Publisher

class Controller extends Publisher with ControllerInterface {

  val numberOfPlayers = 2
  val numberOfSpaces = 40
  val exitCurrentGameMessage: String = "Returns to main menu!"
  val exitProgramMessage: String = "Exit game!"
  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"
  private val undoManager = new UndoManager
  var board: Board = Board(Vector[Space](), Vector[Player](), numberOfPlayers, numberOfSpaces)
  var dice: Dice = Dice()
  var stateMachine = new StateMachine(this)
  var playerState: PlayerState = FreePlayerState

  def rollDice(): Unit = {
    doStep(new RollDiceCommand(this))
    dice = Dice()
    playerState = playerState.determinePlayerState(board.playerList(stateMachine.getCurrentPlayer))
    playerState.rollDice(getCurrentDice, stateMachine.getCurrentPlayer, this)
    stateMachine.nextState()
    publish(new RolledDice)
  }

  def getCurrentDice: Dice = dice

  def doStep(command: Command): Unit = {
    undoManager.doStep(command)
  }

  def stringRolledDice: String = {
    playerState.stringRollDice(getCurrentDice, stateMachine.getCurrentPlayer, this)
  }

  def stringGameBoard(): String = {
    board.toString
  }

  def getCurrentPlayerIndex: Int = stateMachine.getCurrentPlayer

  def nextPlayersRoundMessage(): String = {
    val playerString: String = board.playerList(stateMachine.getCurrentPlayer).toString
    "It´s " + playerString + "´s turn!\n"
  }

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
