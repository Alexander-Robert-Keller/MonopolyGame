package de.htwg.se.monopoly.aview.guiComponent.gui

import java.awt.{Dimension, Image}
import java.io.File

import de.htwg.se.monopoly.controller.controllerComponent.Controller
import javax.imageio.ImageIO

import scala.swing.{Component, Graphics2D}

class BoardCanvas(controller: Controller) extends Component {

  preferredSize = new Dimension(900, 900)

  override def paintComponent(g: Graphics2D): Unit = {
    preferredSize = new Dimension(900, 900)
    val gameBoardImage = getMonopolyBoardImage
    g.drawImage(gameBoardImage, 0, 0, null)

    val numberofPlayers = controller.numberOfPlayers
    var yOffSet = 0

    for (player <- controller.getPlayerList) {
      val location: Int = player.getLocation
      val playerImage = getPlayerImage(player.getId, numberofPlayers)
      if (location < 10) {
        val x = 794 - (location * 74)
        val y = 820 + yOffSet
        g.drawImage(playerImage, x, y, null)
        yOffSet += 10
      } else if (location < 20) {
        val x = 25
        val y = 794 - ((location - 10) * 74) + yOffSet
        g.drawImage(playerImage, x, y, null)
        yOffSet += 10
      } else if (location < 30) {
        val x = 56 + ((location - 20) * 74)
        val y = 20 + yOffSet
        g.drawImage(playerImage, x, y, null)
        yOffSet += 10
      } else if (location < 40) {
        val x = 834
        val y = 50 + ((location - 30) * 74) + yOffSet
        g.drawImage(playerImage, x, y, null)
        yOffSet += 10
      }
    }
  }

  def getPlayerImage(currentPlayer: Int, totalNumberOfPlayers: Int): Image = {
    /* if (currentPlayer > totalNumberOfPlayers) {
       val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/PlayerImage" + currentPlayer.toString + ".jpg"
     } else {
       //Default pic
     }*/
    val path = "src/main/scala/de/htwg/se/monopoly/aview/guiComponent/gui/images/MonopolyMascot.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    resized
  }

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/guiComponent/gui/images/MonopolygameBoard.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(900, 900, Image.SCALE_DEFAULT)
    resized
  }
}