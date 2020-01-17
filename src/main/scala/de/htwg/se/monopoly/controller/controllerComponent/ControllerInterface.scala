package de.htwg.se.monopoly.controller.controllerComponent

import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.{FreePlayerState, PlayerState, StateMachine}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.Space
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.util.{Command, UndoManager}

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  val numberOfPlayers: Int
  val numberOfSpaces: Int
  val exitCurrentGameMessage: String
  val exitProgramMessage: String
  val rolledDoubletsMessage: String
  val undoManager: UndoManager
  var board: Board
  var dice: Dice
  var stateMachine: StateMachine
  var playerState: PlayerState

  def rollDice(): Unit
  def getCurrentDice: Dice
  def doStep(command: Command): Unit
  def stringRolledDice: String
  def stringGameBoard(): String
  def getCurrentPlayerIndex: Int
  def nextPlayersRoundMessage(): String
  def exitMainMenu(): Unit
  def getPlayerList: Vector[Player]
  def exitGameMenu(): Unit
  def initializeGame(): Unit
  def undoCommand(): Unit
  def redoCommand(): Unit

}
