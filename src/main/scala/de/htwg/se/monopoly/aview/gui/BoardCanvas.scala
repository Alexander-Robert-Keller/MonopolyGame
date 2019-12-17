package de.htwg.se.monopoly.aview.gui

import java.awt.{Dimension, Image}
import java.io.File

import de.htwg.se.monopoly
import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.GameState
import de.htwg.se.monopoly.model.Player
import javax.imageio.ImageIO

import scala.swing.{Component, Graphics2D}

class BoardCanvas extends Component{

  preferredSize = new Dimension(1000, 1000)

  override def paintComponent(g: Graphics2D): Unit = {
    preferredSize = new Dimension(1000, 1000)
    val gameBoardImage = getMonopolyBoardImage
    g.drawImage(gameBoardImage, 0, 0, null)

    val numberofPlayers = GameState.getNumberOfPlayers
    var currentPlayer = 0
    var yOffSet = 0
    //TODO: adjust prefeerd size back to 900 x 900 and tweak x and y calculation
    do {
      val location = Game.board.players(currentPlayer).getLocation  //TODO: implement in controller getplayers -> returns list
      val playerImage = getPlayerImage(currentPlayer, numberofPlayers)
      if (location < 10) {
        val x = 925 - (location * 100)
        val y = 900 + yOffSet
        g.drawImage(playerImage, x, y, null)
        currentPlayer += 1
        yOffSet += 10
      } else if (location < 20) {
        val x = 25
        val y = 900 - ((location - 10) * 100) + yOffSet
        g.drawImage(playerImage, x, y, null)
        currentPlayer += 1
        yOffSet += 10
      } else if (location < 30) {
        val x = 25 + ((location - 20) * 100)
        val y = 25 + yOffSet
        g.drawImage(playerImage, x, y, null)
        currentPlayer += 1
        yOffSet += 10
      } else if (location < 40) {
        val x = 925
        val y = 25  + ((location - 30) * 100) + yOffSet
        g.drawImage(playerImage, x, y, null)
        currentPlayer += 1
        yOffSet += 10
      }
    } while (currentPlayer <  numberofPlayers)
  }

  def getPlayerImage(currentPlayer: Int, totalNumberOfPlayers: Int): Image = {
   /* if (currentPlayer > totalNumberOfPlayers) {
      val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/PlayerImage" + currentPlayer.toString + ".jpg"
    } else {
      //Default pic
    }*/
    val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/MonopolyMascot.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    resized
  }

  def getMonopolyBoardImage: Image = {
    val path = "src/main/scala/de/htwg/se/monopoly/aview/gui/images/MonopolygameBoard.jpg"
    val gameBoardImage = ImageIO.read(new File(path))
    val resized = gameBoardImage.getScaledInstance(1000, 1000, Image.SCALE_DEFAULT)
    resized
  }
}