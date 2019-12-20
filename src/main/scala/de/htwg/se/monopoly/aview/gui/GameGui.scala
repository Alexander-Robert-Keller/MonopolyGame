package de.htwg.se.monopoly.aview.gui

import java.awt.Dimension

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.{Controller, ExitCurrentGame, GameState, RolledDice}


import scala.swing.MainFrame
import scala.swing._
import scala.swing.event.ButtonClicked



class GameGui(controller: Controller) extends MainFrame {
  //TODO: get a player onto the Field, resize left Menu properly, add Menu bar with start Game, exit Game, add redo feature later on
  listenTo(controller)
  title = "HTWG Monopoly"
  resizable = false

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Undo") {/*TODO: Implement action */})
      contents += new MenuItem(Action("Redo") {/*TODO: Implement action */})
      contents += new MenuItem(Action("Info") {/*TODO: Implement action */})
      contents += new MenuItem(Action("Quit") { controller.publish(new ExitCurrentGame)})
    }
  }

  val playerName = new TextField(15)
  val playerMoney = new TextField(15)
  val jailedLabel = new TextField(15)

  def currentPlayerPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Player Info:"))
    preferredSize = new Dimension(200, 200)
    playerName.editable = false
    playerMoney.editable = false
    jailedLabel.editable = false
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += playerName
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += playerMoney
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += jailedLabel
    updatePlayerInfo()

  }

  def gameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
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

  def combinedCurrentGamePanelAndGameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(10, 10, 10, 10)
    contents += gameCommandsPanel
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += currentPlayerPanel
  }


  def gameBoardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new BoardCanvas
  }

  contents = new BoxPanel(Orientation.Horizontal) {
    contents += combinedCurrentGamePanelAndGameCommandsPanel
    contents += gameBoardPanel
  }

  centerOnScreen()
  visible = false

  def updatePlayerInfo(): Unit = { //Into Controller
    playerName.text = Game.board.players(GameState.currentPlayer).toString
    playerMoney.text = "Capital: %d".format(Game.board.players(GameState.currentPlayer).getMoney)
    if (Game.board.players(GameState.currentPlayer).isJailed) {
     jailedLabel.text = "This Player is currently Jailed!"
    }
  }

  def openGui(): Unit = {
    visible = true
  }

  def closeGui(): Unit = {
    visible = false
  }

  reactions += {
      case event: RolledDice => this.repaint()
      case _ =>
  }
}



