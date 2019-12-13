package de.htwg.se.monopoly.aview.gui

import java.awt.{Dimension, Image}
import java.io.File
import javax.imageio.ImageIO

import scala.swing.{Component, Graphics2D}

class BoardCanvas extends Component{

  preferredSize = new Dimension(900, 900)

  override def paintComponent(g: Graphics2D): Unit = {
    preferredSize = new Dimension(900, 900)
    val gameBoardImage = getMonopolyBoardImage
    g.drawImage(gameBoardImage, 0, 0, null)
    //TODO: add algorithm that draws at certain positions players -> maybe with an Image
  }

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/MonopolygameBoard.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(900, 900, Image.SCALE_DEFAULT)
    resized
  }
}