package de.htwg.se.monopoly.model.fileIoComponent.fileIoJasonImpl

import de.htwg.se.monopoly.model.fileIoComponent.FileIOInterface
import de.htwg.se.monopoly.model.spacetypes._
import de.htwg.se.monopoly.model.{Board, GameState, Player}
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def loadBoard(fileName: String): Board = {
    val sourceBuffered = Source.fromFile("fileIoFiles/" + fileName + ".json")
    val source = sourceBuffered.getLines.mkString
    val json = Json.parse(source)
    val numberOfPlayers = (json \ "Monopoly" \ "board" \ "numberOfPlayers").get.toString().toInt
    val numberOfSpaces = (json \ "Monopoly" \ "board" \ "numberOfSpaces").get.toString().toInt
    if (numberOfSpaces != 40) {
      // can be changed to matcher, when different board sizes are allowed
      throw new IllegalArgumentException("Das Spielfeld hat nicht die richtige Größe")
    }
    var playerList = Vector[Player]()
    for (index <- 0 until numberOfPlayers) {
      val id = (json \\ "id") (index).as[Int]
      val location = (json \\ "location") (index).as[Int]
      val jailed = (json \\ "jailed") (index).as[Boolean]
      val money = (json \\ "money") (index).as[Int]
      playerList = playerList :+ Player(id, location, jailed, money)
    }
    var spaces = Vector[Space]()
    for (index <- 0 until numberOfSpaces) {
      val space: String = (json \\ "class") (index).as[String]
      space match {
        case "Go" => spaces = spaces :+ Go()
        case "Property" => spaces = spaces :+ Property()
        case "CommunityChest" => spaces = spaces :+ CommunityChest()
        case "Tax" => spaces = spaces :+ Tax()
        case "Railroad" => spaces = spaces :+ Railroad()
        case "Jail" => spaces = spaces :+ Jail()
        case "ElectricCompany" => spaces = spaces :+ ElectricCompany()
        case "Chance" => spaces = spaces :+ Chance()
        case "FreeParking" => spaces = spaces :+ FreeParking()
        case "GoToJail" => spaces = spaces :+ GoToJail()
        case "WaterWorks" => spaces = spaces :+ WaterWorks()
      }
    }
    Board(spaces, playerList, numberOfPlayers, numberOfSpaces)
  }

  override def loadGameState(fileName: String): GameState = {
    val sourceBuffered = Source.fromFile("fileIoFiles/" + fileName + ".json")
    val source = sourceBuffered.getLines.mkString
    val json = Json.parse(source)
    val gameStateJason = json \ "Monopoly" \ "gameState"
    val stateindex = (gameStateJason \ "stateindex").get.toString.toInt
    val currentPlayer = (gameStateJason \ "currentPlayer").get.toString.toInt
    val numberOfPlayers = (gameStateJason \ "numberOfPlayers").get.toString.toInt
    GameState(stateindex, currentPlayer, numberOfPlayers)
  }

  override def save(board: Board, gameState: GameState, fileName: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("fileIoFiles/" + fileName + ".json"))
    pw.write(Json.prettyPrint(gameSaveState(board, gameState)))
    pw.close()
  }

  def gameSaveState(board: Board, gameState: GameState): JsObject = {
    Json.obj(
      "Monopoly" -> Json.obj(
        "board" -> Json.obj(
          "numberOfSpaces" -> JsNumber(board.getTotalNumberOfSpaces),
          "numberOfPlayers" -> JsNumber(board.totalNumberOfPlayers),
          "playerList" -> Json.obj(
            "player" -> Json.toJson(
              for {
                player <- board.playerList
              } yield {
                playerToJason(player)
              }
            )
          ),
          "spaces" -> Json.obj(
            "space" -> Json.toJson(
              for {
                space <- board.spaces
              } yield {
                spaceToJason(space)
              }
            )
          )
        ),
        "gameState" -> Json.obj(
          "stateindex" -> JsNumber(gameState.getStateIndex),
          "currentPlayer" -> JsNumber(gameState.getCurrentPlayer),
          "numberOfPlayers" -> JsNumber(gameState.getNumberOfPlayers),
        )
      )
    )
  }

  def playerToJason(player: Player): JsObject = {
    Json.obj(
      "id" -> JsNumber(player.getId),
      "location" -> JsNumber(player.getLocation),
      "jailed" -> JsBoolean(player.isJailed),
      "money" -> JsNumber(player.getMoney)
    )
  }

  def spaceToJason(space: Space): JsObject = {
    Json.obj(
      "class" -> JsString(space.getClass.toString.substring(43))
    )
  }
}
