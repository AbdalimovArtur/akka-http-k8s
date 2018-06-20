package http

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout

import scala.concurrent.duration.DurationInt

class Route(system: ActorSystem) {

  implicit val timeout: Timeout = 3.seconds

  val routes: server.Route = path("health") {
    get {
      complete(StatusCodes.OK)
    }
  } ~ path("api") {
    get {
      complete(StatusCodes.OK)
    }
  }
}
