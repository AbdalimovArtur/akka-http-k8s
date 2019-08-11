package kz.sofier.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import kz.sofier.api.http.Route

import scala.concurrent.ExecutionContext
import scala.util.{ Failure, Success }

object Boot extends App with LazyLogging with Route {
  implicit val system: ActorSystem             = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ex: ExecutionContext            = system.dispatcher
  val config                                   = ConfigFactory.load()
  val host                                     = config.getString("http.host")
  val port                                     = config.getInt("http.port")

  Http().bindAndHandle(routes, host, port).onComplete {
    case Success(s) => logger.info(s"successfully running on $host:$port")
    case Failure(e) => logger.error(s"error occurred $e")
  }
}
