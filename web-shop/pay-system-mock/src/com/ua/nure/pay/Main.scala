package com.ua.nure.pay

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

/**
  * Created by Влад on 18.04.2016.
  */
object Main extends App {
  implicit val system = ActorSystem("simple-service")
  val service = system.actorOf(Props[SampleServiceActor], "simple-service")

  lazy val host = Option(System.getenv("VCAP_APP_HOST")).getOrElse("localhost")
  lazy val port = Option(System.getenv("VCAP_APP_PORT")).getOrElse("9000").toInt

  IO(Http) ! Http.Bind(service, host, port = port)
}
