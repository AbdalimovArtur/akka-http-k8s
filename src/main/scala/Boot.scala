import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import http.Route
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object Boot {
  def main(args: Array[String]): Unit = {

    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    val route = new Route(actorSystem)
    val log = Logging(actorSystem, "main")

    Http().bindAndHandle(route.routes, "0.0.0.0", 8090).onComplete {
      case Success(s) => log.info(s"successfully running on ${8090}")
      case Failure(e) => log.error(s"error occurred $e")
    }
  }
}
