package de.htwg.se.monopoly.aview.guiComponent

import java.awt.Image
import java.io.File

import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.monopoly.util.{ExitCurrentGame, LoadGame, StartGame}
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, MainFrame, _}

class GUI(controller: ControllerInterface) extends MainFrame {
  listenTo(controller)

  resizable = false
  title = "HTWG Monopoly"

  val startGameButton = new Button("Start Game")
  val loadGameButton = new Button("Load Game")
  val exitMainMenuButton = new Button("Exit")
  listenTo(loadGameButton)
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
    contents += loadGameButton
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
    case ButtonClicked(`loadGameButton`) =>
      controller.loadGame()
    case _ =>
  }

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/guiComponent/images/MonopolyMascot.jpg"
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
    case event: LoadGame => startGame()
    case _ =>
  }
}
