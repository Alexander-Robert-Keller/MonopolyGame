package de.htwg.se.monopoly.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.monopoly.controller.controllerComponent.Controller
import de.htwg.se.monopoly.model.gameComponent.Board


// Temporery file for Testing porposes

object XmlTest {
  def main(args: Array[String]): Unit = {
    testSave()
    testLoadBoard()
    testLoadGameState()
  }

  def testSave(): Unit = {
    val controller = new Controller
    controller.board = controller.board.init()
    controller.stateMachine.startGame(controller.numberOfPlayers)
    controller.dice = controller.dice.rollDice()
    val fileIo = new FileIO
    fileIo.save(controller.board, controller.stateMachine.state, "test")
  }

  def testLoadBoard(): Unit = {
    val fileIo = new FileIO
    var board = Board(new Controller(10, 10))
    board = fileIo.loadBoard("test")
    print(board.toString)
  }

  def testLoadGameState(): Unit = {
    val fileIo = new FileIO
    val gameState = fileIo.loadGameState("test")
    println("stateIndex = " + gameState.getStateIndex.toString)
    println("currentPlayer = " + gameState.currentPlayer.toString)
    println("numberOfPlayers = " + gameState.numberOfPlayers.toString)
  }

}
