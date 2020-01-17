package de.htwg.se.monopoly.controller.controllerComponent

import de.htwg.se.monopoly.model.gameComponent.spacetypes.Space
import de.htwg.se.monopoly.model.gameComponent.{Board, Dice, Player}
import de.htwg.se.monopoly.util.utilityComponent._

import scala.swing.Publisher

case class Controller(numPlayers: Int = 2, numSpaces: Int = 40) extends Publisher with ControllerInterface {

  val numberOfPlayers: Int = numPlayers
  val numberOfSpaces: Int = numberOfSpaces
  val exitCurrentGameMessage: String = "Returns to main menu!"
  val exitProgramMessage: String = "Exit game!"
  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"
  private val undoManager = (new Utility).createUndoManagerInstance()
  var board: Board = Board(this)
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
