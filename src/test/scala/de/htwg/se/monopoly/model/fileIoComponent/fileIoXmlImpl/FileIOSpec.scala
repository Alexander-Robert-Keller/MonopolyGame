package de.htwg.se.monopoly.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.{Go, Space}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}

import scala.xml.Elem

class FileIOSpec extends WordSpec with Matchers {

  "A file IO class" when {
    "new" should {

      val fileIo = new FileIO
      val board = Board(Vector[Space](), Vector[Player](), 2, 40).init()
      val gameState = GameState(1, 0, 2)

      "have a method which loads Board from a .xml file" in {
        val board = fileIo.loadBoard("test")
        board shouldBe a[Board]
        try {
          fileIo.loadBoard("test2")
        } catch {
          case e: IllegalArgumentException => e shouldBe a [IllegalArgumentException]
        }
      }

      "have a method which loads a GameState" in {
        val gameState = fileIo.loadGameState("test")
        gameState shouldBe a[GameState]
      }

      "have a method to save a game" in {
        fileIo.save(board, gameState, "test")
      }

      "have a method which constructs the xml file" in {
        fileIo.gameSaveState(board, gameState) shouldBe a[Elem]
      }

      "have a method which constructs the board in json format" in {
        fileIo.boardToXml(board) shouldBe a[Elem]
      }

      "have a method which constructs the Space in json format" in {
        fileIo.spaceToXml(Go()) shouldBe a[Elem]
      }

      "have a method which constructs the player in json format" in {
        fileIo.playerToXml(Player(0, 0, jailed = false, 0)) shouldBe a[Elem]
      }

      "have a method which constructs the GameState in json format" in {
        fileIo.gameStateToXml(gameState) shouldBe a[Elem]
      }
    }
  }
}

