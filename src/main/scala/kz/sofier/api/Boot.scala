package kz.sofier.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import cats.effect.{ ExitCode, IO, IOApp }
import com.typesafe.scalalogging.LazyLogging
import kz.sofier.api.db.Database
import kz.sofier.api.http.Route
import kz.sofier.api.utils.config.Config

import scala.concurrent.ExecutionContext

object Boot extends IOApp with LazyLogging with Route {
  implicit val system: ActorSystem             = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ex: ExecutionContext            = system.dispatcher

  override def run(args: List[String]): IO[ExitCode] =
    for {
      config <- Config.parse()
      _      <- Database.initialize(config.database)
      server <- IO.fromFuture(IO(Http().bindAndHandle(routes, config.server.host, config.server.port)))
    } yield ExitCode.Success
}
