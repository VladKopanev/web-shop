package com.ua.nure.pay

import akka.actor.Actor
import spray.routing.HttpService

/**
  * Created by Влад on 18.04.2016.
  */
class SampleServiceActor extends Actor with SampleRoute {
  def receive = runRoute(route)

  def actorRefFactory = context
}

trait SampleRoute extends HttpService {
  import spray.httpx.SprayJsonSupport._
  import Stuff._
  import spray.http.MediaTypes

  val route = {
    path("headers") {
      get {
        optionalHeaderValueByName("ct-remote-user") { userId =>
          complete(userId)
        }
      }
    } ~
    path("params") {
      get {
        parameters('req, 'opt.?) { (req, opt) =>
          complete(s"Req: $req, Opt: $opt")
        }
      }
    } ~
    path("stuff") {
        respondWithMediaType(MediaTypes.`application/json`) {
          get {
            complete(Stuff(1, "my stuff"))
          }~
            post {
              entity(as[Stuff]) { stuff =>
                complete(Stuff(stuff.id + 100, stuff.data + " posted"))
              }
            }
        }
      } ~
      pathPrefix("junk") {
        pathPrefix("mine") {
          pathEnd {
            get {
              complete("MINE")
            }
          }
        } ~ pathPrefix("yours") {
          pathEnd {
            get {
              complete("YOURS")
            }
          }
        }
      } ~
      get {
      complete("I exist!")
      }
  }
}
