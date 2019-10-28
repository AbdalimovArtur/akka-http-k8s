package kz.sofier.api.http.utils

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.{ RequestContext, RouteResult }
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import kz.sofier.api.http.routes.RestRoutes.Request
import kz.sofier.api.serializers.Serializers

import scala.concurrent.{ ExecutionContext, Promise }
import scala.concurrent.duration._

object PerRequest {
  case class OfProps(requestContext: RequestContext, props: Props, request: Request, result: Promise[RouteResult])
      extends PerRequest {
    lazy val targetActor: ActorRef = context.actorOf(props)
  }
}

trait PerRequest extends Actor with ActorLogging with Json4sSupport with Serializers {
  val TimeoutDuration: FiniteDuration = 20.seconds
  val requestContext: RequestContext
  val targetActor: ActorRef
  val result: Promise[RouteResult]
  val request: Request

  targetActor ! request

  override def receive: Receive = {
    case _ =>
  }

  def responseWith(obj: => ToResponseMarshallable): Unit = {
    implicit val ex: ExecutionContext = context.dispatcher
    val promiseResult                 = requestContext.complete(ToResponseMarshallable(obj))
    promiseResult.onComplete(x => result.complete(x))
    context.stop(self)
  }

}
