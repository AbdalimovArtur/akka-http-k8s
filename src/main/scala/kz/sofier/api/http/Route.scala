package kz.sofier.api.http

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import kz.sofier.api.http.routes.RestRoutes

trait Route extends RestRoutes {
  implicit val system: ActorSystem

  val routes: server.Route = pathPrefix("api") {
    path("check") {
      get {
        complete(StatusCodes.OK)
      }
    } ~ restRoutes
  }
}
