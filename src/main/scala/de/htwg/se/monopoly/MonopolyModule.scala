package de.htwg.se.monopoly

import com.google.inject.AbstractModule
import de.htwg.se.monopoly.aview.guiComponent.GuiInterface
import de.htwg.se.monopoly.aview.guiComponent.guiBaseImpl.GUI
import de.htwg.se.monopoly.aview.tuiComponent.TuiInterface
import de.htwg.se.monopoly.aview.tuiComponent.tuiBaseImpl.TextualUserInterface
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.monopoly.model.boardComponent.BoardInterface
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.fileIoComponent._
import net.codingwell.scalaguice.ScalaModule

class MonopolyModule extends AbstractModule with ScalaModule {
  bind[GuiInterface].to[GUI]
  bind[TuiInterface].to[TextualUserInterface]
  bind[ControllerInterface].to[Controller]
  bind[BoardInterface].to[Board]

  bind[FileIOInterface].to[fileIoJasonImpl.FileIO]
}
