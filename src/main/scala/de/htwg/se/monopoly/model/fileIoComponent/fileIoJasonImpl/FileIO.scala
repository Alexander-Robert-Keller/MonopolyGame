package de.htwg.se.monopoly.model.fileIoComponent.fileIoJasonImpl

import de.htwg.se.monopoly.model.{Board, GameState}
import de.htwg.se.monopoly.model.fileIoComponent.FileIOInterface
import play.api.libs.json._
import scala.io.Source

class FileIO extends FileIOInterface{

  override def loadBoard(fileName: String): Board = ???

  override def loadGameState(fileName: String): GameState = ???

  override def save(board: Board, gameState: GameState, fileName: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("fileIoFiles" + fileName + ".json"))
    pw.write(Json.prettyPrint(gameSaveState(board, gameState)))
    pw.close()
  }

  def gameSaveState(board: Board, gameState: GameState): JsObject = {
    Json.obj(
      "Monopoly"-> Json.obj(
        "board" -> Json.obj(
          "playerList" -> Json.obj(

          )
        )
      )
    )
  }
}
