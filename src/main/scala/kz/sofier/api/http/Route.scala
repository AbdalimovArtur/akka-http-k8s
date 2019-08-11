package kz.sofier.api.http

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout

import scala.concurrent.duration.DurationInt

trait Route {

  implicit val timeout: Timeout = 3.seconds
  implicit val system: ActorSystem

  val routes: server.Route = path("check") {
    get {
      complete(StatusCodes.OK)
    }
  } ~ path("api") {
    get {
      complete(StatusCodes.OK)
    }
  }
}
