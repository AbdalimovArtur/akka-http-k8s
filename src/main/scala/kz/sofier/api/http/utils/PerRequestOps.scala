package kz.sofier.api.http.utils

import akka.actor.{ ActorSystem, Props }
import akka.http.scaladsl.server.{ RequestContext, RouteResult }
import kz.sofier.api.http.routes.RestRoutes.Request

import scala.concurrent.Promise

trait PerRequestOps {
  def perRequest(rc: RequestContext, p: Props, r: Request, rr: Promise[RouteResult])(implicit s: ActorSystem): Unit =
    s.actorOf(Props(PerRequest.OfProps(rc, p, r, rr)))
}
