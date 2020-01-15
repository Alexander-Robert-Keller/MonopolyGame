package de.htwg.se.monopoly.aview.gui

import java.awt.Dimension

import de.htwg.se.monopoly.controller.Controller
import de.htwg.se.monopoly.util.{Redo, RolledDice, StartGame, Undo}

import scala.swing.MainFrame
import scala.swing._
import scala.swing.event.ButtonClicked



class GameGui(controller: Controller) extends MainFrame {
  //TODO: get a player onto the Field, resize left Menu properly, add Menu bar with start Game, exit Game, add redo feature later on
  listenTo(controller)
  title = "HTWG Monopoly"
  resizable = true

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Undo") { controller.undoCommand() })
      contents += new MenuItem(Action("Redo") { controller.redoCommand() })
      contents += new MenuItem(Action("Info") {/*TODO: Implement action */})
      contents += new MenuItem(Action("Quit") { controller.exitGameMenu()})
    }
  }

  val currentPlayerName = new TextField(15)

  def currentPlayerPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Current Player:"))
    preferredSize = new Dimension(200, 200)
    currentPlayerName.editable = false
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += currentPlayerName
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += gameCommandsPanel
    contents += Swing.VStrut(10)
    contents += Swing.Glue
  }

  def gameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(200, 100)
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Current Commands:"))
    val rollDiceButton = new Button("roll Dice!")
    val buyPropertyButton = new Button("Buy Property")
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += rollDiceButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += buyPropertyButton
    listenTo(rollDiceButton)
    listenTo(buyPropertyButton)
    reactions += {
      case ButtonClicked(`rollDiceButton`) =>
        controller.rollDice()
      case ButtonClicked(`buyPropertyButton`) => //TODO: add controller Commands
    }
  }

  val infoPlayerName = new TextField("")
  infoPlayerName.editable = false

  def nextPrevPlayerPanel: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    preferredSize = new Dimension(200, 42)
    border = Swing.EmptyBorder(10, 10, 10, 10)
    val prevButton = new Button("Prev")
    val nextButton = new Button("Next")
    contents += prevButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += infoPlayerName
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += nextButton
    listenTo(prevButton)
    listenTo(nextButton)
    reactions += {
      case ButtonClicked(`prevButton`) => //TODO: Implement Show Player Info
      case ButtonClicked(`nextButton`) => //TODO: Implement Show Player Info
    }
  }

  def playerInfoTextArea: TextArea = new TextArea(" \n")
  playerInfoTextArea.editable = false

  def playerInfoPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(600, 700)
    contents += playerInfoTextArea
  }

  def playerInfoAndPlayerTextAreaPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(10, 10, 10, 10)
    preferredSize = new Dimension(200, 660)
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Player Info:"))
    contents += nextPrevPlayerPanel
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += playerInfoPanel
    playerInfoTextArea.editable = false

  }

  def combinedCurrentGamePanelAndGameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(10, 10, 10, 10)
    contents += currentPlayerPanel
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += playerInfoAndPlayerTextAreaPanel
  }


  def gameBoardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new BoardCanvas(controller)
  }

  contents = new BoxPanel(Orientation.Horizontal) {
    contents += combinedCurrentGamePanelAndGameCommandsPanel
    contents += gameBoardPanel
  }

  centerOnScreen()
  visible = false

  def updatePlayerInfo(): Unit = { //Into Controller
    if (controller.board.playerList(controller.getCurrentPlayerIndex).isJailed) {
      currentPlayerName.text = controller.board.playerList(controller.getCurrentPlayerIndex).toString + " (jailed!)"
    } else {
      currentPlayerName.text = controller.board.playerList(controller.getCurrentPlayerIndex).toString
    }
  }


  def openGui(): Unit = {
    visible = true
  }

  def closeGui(): Unit = {
    visible = false
  }

  reactions += {
    case event : StartGame =>
      updatePlayerInfo()
      this.repaint()
    case event: RolledDice =>
      updatePlayerInfo()
      this.repaint()
    case event: Undo => this.repaint()
    case event: Redo => this.repaint()
    case _ =>
  }
}



