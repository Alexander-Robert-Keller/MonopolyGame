package de.htwg.se.monopoly.aview.gui

import java.awt.Image
import java.io.File

import de.htwg.se.monopoly.controller.Controller
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, MainFrame}

class GUI(controller: Controller) extends MainFrame {
  title = "HTWG Monopoly"
  resizable = false

  val startGameButton = new Button("Start Game")
  val infoButton = new Button("Info")
  val exitMainMenuButton = new Button("Exit")
  listenTo(infoButton)
  listenTo(exitMainMenuButton)
  listenTo(startGameButton)

  contents = new BoxPanel(Orientation.Vertical){
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

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/MonopolyMascot.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(300, 300, Image.SCALE_DEFAULT)
    resized
  }

  reactions += {
    case ButtonClicked(`startGameButton`) => startGame()
    case ButtonClicked(`exitMainMenuButton`) => System.exit(0)
    case ButtonClicked(`infoButton`) => //TODO: Popup Field with some info
  }

  val inGameGui = new GameGui(controller, this)

  def startGame(): Unit = {
    visible = false
    inGameGui.openGui()
  }

  def endGame(): Unit = {
    visible = true
    inGameGui.closeGui()
  }
}
