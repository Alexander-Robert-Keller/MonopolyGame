package de.htwg.se.monopoly.model.fileIoComponent.fileIoJasonImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.{Go, Space}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.JsObject

class FileIOSpec extends WordSpec with Matchers {

  "A file IO class" when {
    "new" should {

      val fileIo = new FileIO
      val board = Board(Vector[Space](), Vector[Player](), 2, 40).init()
      val gameState = GameState(1, 0, 2)

      "have a method which loads Board from a .json file" in {
        val board = fileIo.loadBoard("test")
        board shouldBe a[Board]
      }

      "have a method which loads a GameState" in {
        val gameState = fileIo.loadGameState("test")
        gameState shouldBe a[GameState]
      }

      "have a method to save a game" in {
        fileIo.save(board, gameState, "test")
      }

      "have a method which constructs the json file" in {
        fileIo.gameSaveState(board, gameState) shouldBe a[JsObject]
      }

      "have a method which constructs the palyer in json format" in {
        fileIo.playerToJason(Player(0, 0, jailed = false, 0)) shouldBe a[JsObject]
      }

      "have a method which constructs the Space in json format" in {
        fileIo.spaceToJason(Go()) shouldBe a[JsObject]
      }
    }
  }
}
