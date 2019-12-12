package de.htwg.se.monopoly.aview.gui

import java.awt.{Dimension, Image}
import java.awt.image.BufferedImage

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.{Controller, GameState}
import de.htwg.se.monopoly.util.Subscriber
import java.io.File

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import javax.swing.ImageIcon

import scala.swing.MainFrame
import scala.swing._


class Gui(controller: Controller) extends MainFrame with Subscriber {

  controller.add(this)
  title = "HTWG Monopoly"
  resizable = false
  //size = new Dimension(1000, 500)

  val playerName = new TextField(15)
  val playerMoney = new TextField(15)
  val jailedLabel = new TextField(15)

  def currentPlayerPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.CompoundBorder(Swing.LineBorder(java.awt.Color.BLACK, 1), Swing.TitledBorder(Swing.EmptyBorder(10, 10, 10, 10), "Player Info:"))
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

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/MonopolygameBoard.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(800, 800, Image.SCALE_DEFAULT)
    resized
  }

  val gameBoardImageLabel = new Label()

  def gameBoardPanel: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    gameBoardImageLabel.icon = new ImageIcon(getMonopolyBoardImage)
    gameBoardImageLabel.opaque = true
    contents += gameBoardImageLabel
  }

  contents = new BoxPanel(Orientation.Horizontal) {
    contents += currentPlayerPanel
    contents += gameBoardPanel
  }

  centerOnScreen()
  visible = true

  def updatePlayerInfo(): Unit = { //Into Controller
    playerName.text = Game.board.players(GameState.currentPlayer).toString
    playerMoney.text = "Capital: %d".format(Game.board.players(GameState.currentPlayer).getMoney)
    if (Game.board.players(GameState.currentPlayer).isJailed) {
     jailedLabel.text = "This Player is currently Jailed!"
    }
  }

  override def update(): Unit = {
    //TODO: Switch case for different updates depending on gamestate and current player
  }
}



