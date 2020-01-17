package de.htwg.se.monopoly.aview.guiComponent.gui

import java.awt.Image
import java.io.File

import de.htwg.se.monopoly.controller.controllerComponent.Controller
import de.htwg.se.monopoly.util.utilityComponent.{ExitCurrentGame, StartGame}
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, MainFrame, _}

class GUI(controller: Controller) extends MainFrame {
  listenTo(controller)

  resizable = false
  title = "HTWG Monopoly"

  val startGameButton = new Button("Start Game")
  val infoButton = new Button("Info")
  val exitMainMenuButton = new Button("Exit")
  listenTo(infoButton)
  listenTo(exitMainMenuButton)
  listenTo(startGameButton)

  contents = new BoxPanel(Orientation.Vertical) {
    val image = new Label()
    image.icon = new ImageIcon(getMonopolyBoardImage)
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += image
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += startGameButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += infoButton
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += exitMainMenuButton
  }

  visible = true
  centerOnScreen()
  val inGameGui = new GameGui(controller)

  reactions += {
    case ButtonClicked(`startGameButton`) =>
      controller.initializeGame()
    case ButtonClicked(`exitMainMenuButton`) =>
      controller.exitMainMenu()
    case ButtonClicked(`infoButton`) => //TODO: Popup Field with some info
    case _ =>
  }

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/guiComponent/gui/images/MonopolyMascot.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resizedImage = gameBoardImage.getScaledInstance(300, 300, Image.SCALE_DEFAULT)
    resizedImage
  }

  def startGame(): Unit = {
    visible = false
    inGameGui.openGui()
  }

  def endGame(): Unit = {
    visible = true
    inGameGui.closeGui()
  }

  reactions += {
    case event: ExitCurrentGame => endGame()
    case event: StartGame => startGame()
    case _ =>
  }
}
