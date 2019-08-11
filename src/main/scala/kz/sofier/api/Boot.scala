package kz.sofier.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import kz.sofier.api.http.Route

import scala.concurrent.ExecutionContext
import scala.util.{ Failure, Success }

object Boot extends App with LazyLogging {
  implicit val actorSystem: ActorSystem        = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ex: ExecutionContext            = actorSystem.dispatcher
  val route                                    = new Route(actorSystem)

  Http().bindAndHandle(route.routes, "0.0.0.0", 8090).onComplete {
    case Success(s) => logger.info(s"successfully running on ${8090}")
    case Failure(e) => logger.error(s"error occurred $e")
  }
}
