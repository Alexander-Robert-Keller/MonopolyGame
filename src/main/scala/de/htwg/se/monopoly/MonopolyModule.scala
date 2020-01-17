package de.htwg.se.monopoly

import com.google.inject.AbstractModule
import de.htwg.se.monopoly.controller.controllerComponent._
import de.htwg.se.monopoly.model.boardComponent._
import de.htwg.se.monopoly.model.fileIoComponent._
import net.codingwell.scalaguice.ScalaModule

class MonopolyModule extends AbstractModule with ScalaModule {


  override def configure(): Unit = {
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    //bind[BoardInterface].to[boardBaseImpl.Board]

    bind[FileIOInterface].to[fileIoJasonImpl.FileIO]
  }
}
