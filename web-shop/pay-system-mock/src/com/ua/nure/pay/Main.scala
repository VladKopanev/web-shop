package com.ua.nure.pay

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Main extends App {
  val serviceName = "pay-system-service"
  implicit val system = ActorSystem(serviceName)
  val service = system.actorOf(Props[PaySystemServiceActor], serviceName)

  lazy val host = Option(System.getenv("APP_HOST")).getOrElse("localhost")
  lazy val port = Option(System.getenv("APP_PORT")).getOrElse("9000").toInt

  IO(Http) ! Http.Bind(service, host, port = port)
}
