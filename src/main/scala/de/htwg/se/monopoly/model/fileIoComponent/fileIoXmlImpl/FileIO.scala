package de.htwg.se.monopoly.model.fileIoComponent.fileIoXmlImpl


import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes._
import de.htwg.se.monopoly.model.fileIoComponent.FileIOInterface
import de.htwg.se.monopoly.model.gameStateComponent.GameState

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def loadBoard(fileName: String): Board = {
    val file = scala.xml.XML.loadFile("fileIoFiles/" + fileName + ".xml")
    val numberOfPlayers = (file \\ "Monopoly" \ "board" \ "@numberOfPlayers").text.toInt
    val numberOfSpaces = (file \\ "Monopoly" \ "board" \ "@numberOfSpaces").text.toInt
    if (numberOfSpaces != 40) {
      // can be changed to matcher, when different board sizes are allowed
      throw new IllegalArgumentException("Das Spielfeld hat nicht die richtige Größe")
    }
    var cellNodePlayerList = file \\ "Monopoly" \ "board" \ "playerList" \ "player"
    var playerList = Vector[Player]()
    for (player <- cellNodePlayerList) {
      playerList = playerList :+ Player((player \ "@id").text.toInt, (player \ "@location").text.toInt, (player \ "@jailed").text.toBoolean, (player \ "@money").text.toInt)
    }
    val cellNodeSpaces = file \\ "Monopoly" \ "board" \ "spaces" \ "space"
    var spaces = Vector[Space]()
    for (space <- cellNodeSpaces) {
      (space \ "@class").text match {
        case "Go" => spaces = spaces :+ Go()
        case "Property" => spaces = spaces :+ Property((space \ "@name").text, (space \ "@price").text.toInt, (space \ "@ownerId").text.toInt, (space \ "@rent").text.toInt)
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
    val file = scala.xml.XML.loadFile("fileIoFiles/" + fileName + ".xml")
    GameState((file \\ "Monopoly" \ "gameState" \ "@stateindex").text.toInt, (file \\ "Monopoly" \ "gameState" \ "@currentPlayer").text.toInt, (file \\ "Monopoly" \ "gameState" \ "@numberOfPlayers").text.toInt)
  }

  override def save(board: Board, gameState: GameState, fileName: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("fileIoFiles/" + fileName + ".xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameSaveState(board, gameState))
    pw.write(xml)
    pw.close()
  }

  def gameSaveState(board: Board, gameState: GameState): Elem = {
    <Monopoly>
      {boardToXml(board)}{gameStateToXml(gameState)}
    </Monopoly>
  }


  def boardToXml(board: Board): Elem = {
    <board numberOfSpaces={board.getTotalNumberOfSpaces.toString} numberOfPlayers={board.totalNumberOfPlayers.toString}>
      <playerList>
        {for (player <- board.playerList) yield playerToXml(player)}
      </playerList>
      <spaces>
        {for (space <- board.spaces) yield spaceToXml(space)}
      </spaces>
    </board>
  }

  def playerToXml(player: Player): Elem = {
    <player id={player.getId.toString} location={player.getLocation.toString} jailed={player.isJailed.toString} money={player.getMoney.toString}>
    </player>
  }

  def spaceToXml(space: Space): Elem = {
    space match {
      case tmpSpace: Property =>
        <space class={tmpSpace.getClass.toString.substring(72)} price={tmpSpace.price.toString} ownerId={tmpSpace.ownerId.toString} rent={tmpSpace.rent.toString}>
        </space>
      case _ =>
        <space class={space.getClass.toString.substring(72)}>
        </space>
    }
  }

  def gameStateToXml(gameState: GameState): Elem = {
    <gameState stateindex={gameState.getStateIndex.toString} currentPlayer={gameState.getCurrentPlayer.toString} numberOfPlayers={gameState.getNumberOfPlayers.toString}>
    </gameState>
  }
}