package kz.sofier.api.http.routes

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, get, pathPrefix}
import akka.http.scaladsl.server.{Route, RouteResult}
import akka.stream.ActorMaterializer
import kz.sofier.api.http.routes.RestRoutes.Request
import kz.sofier.api.http.utils.PerRequestOps

import scala.concurrent.Promise

object RestRoutes {
  trait Request
}

trait RestRoutes extends PerRequestOps {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  lazy val restRoutes: Route = pathPrefix("books") {
    get {
      complete(StatusCodes.OK)
    }
  }

  def completeRequest(body: Request, props: Props): Route = ctx => {
    val p = Promise[RouteResult]
    perRequest(ctx, props, body, p)
    p.future
  }
}
