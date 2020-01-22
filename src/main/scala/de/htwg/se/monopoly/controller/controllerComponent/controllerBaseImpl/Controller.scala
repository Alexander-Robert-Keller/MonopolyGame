package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Injector}
import de.htwg.se.monopoly.MonopolyModule
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes._
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.model.fileIoComponent.FileIOInterface
import de.htwg.se.monopoly.util._

import scala.swing.Publisher

class Controller extends Publisher with ControllerInterface {

  val numberOfPlayers = 2
  val numberOfSpaces = 40
  val wentOverGoValue: Int = 200
  val exitCurrentGameMessage: String = "Returns to main menu!"
  val exitProgramMessage: String = "Exit game!"
  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"
  val undoManager = new UndoManager
  var board: Board = Board(Vector[Space](), Vector[Player](), numberOfPlayers, numberOfSpaces)
  var dice: Dice = Dice()
  var stateMachine = new StateMachine(this)
  var playerState: PlayerState = FreePlayerState
  val injector: Injector = Guice.createInjector(new MonopolyModule)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])

  def rollDice(): Unit = {
    doStep(new RollDiceCommand(this))
    dice = Dice()
    playerState = playerState.determinePlayerState(board.playerList(stateMachine.getCurrentPlayer))
    playerState.rollDice(getCurrentDice, stateMachine.getCurrentPlayer, this)
    val space = spaceAction()
    wentOverGo()
    stateMachine.nextState()
    publish(new RolledDice)
    publishSpaceAction(space)
    endFinishedGame()
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

  override def saveGame(): Unit = {
    fileIO.save(board, stateMachine.state, "SaveFile1")
    publish(new SaveGame)
  }

  override def loadGame(): Unit = {
    board = fileIO.loadBoard("SaveFile1")
    stateMachine.state = fileIO.loadGameState("SaveFile1")
    publish(new LoadGame)
  }

  def spaceAction(): Space = {
    val player: Player = board.playerList(stateMachine.getCurrentPlayer)
    val space: Space = board.spaces(player.getLocation)
    space match {
      case property: Property =>
        if (property.ownerId != player.getId && property.ownerId >= 0) {
          val newPlayer = property.action(player)
          board = board.setPayedRent(newPlayer, newPlayer.getLocation)
        }
      case goToJail: GoToJail =>
        val newPlayer = goToJail.action(player)
        board = board.setPlayerJailedOrUnJailed(newPlayer.id, newPlayer.isJailed)
      case _ =>
    }
    space
  }

  def wentOverGo(): Unit = {
    if (board.playerList(stateMachine.getCurrentPlayer).getLocation - dice.getFaceValue < 0 && board.playerList(stateMachine.getCurrentPlayer).getLocation == 0) {
      board = board.increasePlayerMoney(wentOverGoValue * 2 ,stateMachine.getCurrentPlayer)
    } else if (board.playerList(stateMachine.getCurrentPlayer).getLocation - dice.getFaceValue < 0) {
      board = board.increasePlayerMoney(wentOverGoValue ,stateMachine.getCurrentPlayer)
    }
  }

  def publishSpaceAction(space: Space): Unit = {
    space match {
      case property: Property =>
        publish(new PayRent)
      case goToJail: GoToJail =>
        publish(new PlayerJailed)
      case _ =>
    }
  }

  def playerInfo(): Unit = {
    publish(new PlayerInfo)
  }

  def getPlayerInfo(playerIndex: Int): Vector[String] = {
    val player = board.playerList(playerIndex)
    val playerMessage = player.toString
    var infoMessage = "  Money: " + player.getMoney.toString + "\n"
    infoMessage = infoMessage + "  Location: " + player.getLocation.toString + "\n" + "  Jailed: " + player.isJailed.toString + "\n"
    infoMessage = infoMessage + "  Propertys: \n"
    for (space <- board.spaces) {
      space match {
        case property: Property =>
          if (property.ownerId - 1 == playerIndex) {
            infoMessage = infoMessage + "    -" + property.name + "\n"
          }
        case _ =>
      }
    }
    Vector[String](playerMessage, infoMessage)
  }

  def buyProperty(): Unit = {
    doStep(new BuyCommand(this))
    board = board.buySpace(stateMachine.getCurrentPlayer, getPlayerList(stateMachine.getCurrentPlayer).getLocation)
    stateMachine.nextState()
    publish(new BuyProperty)
    endFinishedGame()
  }

  def dontBuyProperty(): Unit = {
    doStep(new BuyCommand(this))
    stateMachine.nextState()
    publish(new DontBuyProperty)
  }

  def endFinishedGame(): Unit = {
    if (gameFinished) {
      stateMachine.setState("FINISHED_GAME")
      publish(new GameFinished)
    }
  }

  def gameFinished: Boolean = {
    var index = 0
    for (player <- getPlayerList) {
      if (player.getMoney >= 0) {
        index += 1
      }
    }
    if (index <= 1) {
      true
    } else {
      false
    }
  }

  def getWinner: String = {
    var winner: String = ""
    for (player <- board.playerList) {
      if (player.getMoney < 0) {
        winner = player.toString + " has won!"
      }
    }
    winner
  }
}
