package de.htwg.se.monopoly.aview.guiComponent

import java.awt.Dimension

import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.util.{BuyProperty, DontBuyProperty, LoadGame, PlayerInfo, Redo, RolledDice, SaveGame, StartGame, Undo}

import scala.swing.event.ButtonClicked
import scala.swing.{MainFrame, _}


class GameGui(controller: ControllerInterface) extends MainFrame {
  //TODO: get a player onto the Field, resize left Menu properly, add Menu bar with start Game, exit Game, add redo feature later on
  listenTo(controller)
  title = "HTWG Monopoly"
  resizable = true

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += new MenuItem(Action("SaveGame") {
        controller.saveGame()
      })
      contents += new MenuItem(Action("Undo") {
        controller.undoCommand()
      })
      contents += new MenuItem(Action("Redo") {
        controller.redoCommand()
      })
      contents += new MenuItem(Action("Quit") {
        controller.exitGameMenu()
      })
    }
  }

  val currentPlayerName = new TextField(15)
  val infoPlayerName = new TextField("")

  def combinedCurrentGamePanelAndGameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(10, 10, 10, 10)
    contents += currentPlayerPanel
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += playerInfoAndPlayerTextAreaPanel
  }

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

  infoPlayerName.editable = false
  val buyPropertyButton = new Button("Buy property!")
  val dontBuyPropertyButton = new Button("Dont´t buy property!")

  def en_disableBuyProperty(): Unit = {
    if (controller.stateMachine.state.stateIndex == 2) {
      buyPropertyButton.enabled = true
      dontBuyPropertyButton.enabled = true
    } else {
      buyPropertyButton.enabled = false
      dontBuyPropertyButton.enabled = false
    }
  }

  val rollDiceButton = new Button("Roll Dice!")

  def en_disableRollDice(): Unit = {
    if (controller.stateMachine.state.stateIndex == 1) {
      rollDiceButton.enabled = true
    } else {
      rollDiceButton.enabled = false
    }
  }

  def gameCommandsPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(200, 150)
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Current Commands:"))

    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += rollDiceButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += buyPropertyButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += dontBuyPropertyButton
    listenTo(rollDiceButton)
    listenTo(buyPropertyButton)
    listenTo(dontBuyPropertyButton)
    reactions += {
      case ButtonClicked(`rollDiceButton`) =>
        controller.rollDice()
      case ButtonClicked(`buyPropertyButton`) =>
        controller.buyProperty()
      case ButtonClicked(`dontBuyPropertyButton`) =>
        controller.dontBuyProperty()
    }
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

  var currentPlayerInfoIndex: Int = 0

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
      case ButtonClicked(`prevButton`) =>
        currentPlayerInfoIndex = (currentPlayerInfoIndex + 1) % controller.numberOfPlayers
        controller.playerInfo()
      case ButtonClicked(`nextButton`) =>
        currentPlayerInfoIndex = currentPlayerInfoIndex - 1
        if (currentPlayerInfoIndex < 0) {
          currentPlayerInfoIndex = currentPlayerInfoIndex + controller.numberOfPlayers
        }
        controller.playerInfo()
    }
  }

  def playerInfoPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(600, 700)
    contents += playerInfoTextArea
  }

  val playerInfoTextArea: TextArea = new TextArea(" \n")

  def setPlayerInfo(info: Vector[String]): Unit = {
    playerInfoTextArea.text = info(1)
    infoPlayerName.text = info(0)
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

  def updatePlayerInfo(): Unit = {
    if (controller.board.playerList(controller.getCurrentPlayerIndex).isJailed) {
      currentPlayerName.text = controller.board.playerList(controller.getCurrentPlayerIndex).toString + " (jailed!)"
    } else {
      currentPlayerName.text = controller.board.playerList(controller.getCurrentPlayerIndex).toString
    }
  }

  def openGui(): Unit = {
    visible = true
    setPlayerInfo(controller.getPlayerInfo(currentPlayerInfoIndex))
    en_disableRollDice()
  }

  def closeGui(): Unit = {
    visible = false
  }

  def updateGui(): Unit = {
    en_disableBuyProperty()
    en_disableRollDice()
    updatePlayerInfo()
    setPlayerInfo(controller.getPlayerInfo(currentPlayerInfoIndex))
    this.repaint()
  }

  reactions += {
    case event: StartGame =>
      updateGui()
    case event: RolledDice =>
      updateGui()
    case event: Undo => updateGui()
    case event: Redo => updateGui()
    case event: LoadGame => updateGui()
    case event: SaveGame =>
    case event: PlayerInfo =>
      setPlayerInfo(controller.getPlayerInfo(currentPlayerInfoIndex))
      updateGui()
    case event: BuyProperty => updateGui()
    case event: DontBuyProperty => updateGui()
    case _ =>
  }
}



