package com.ua.nure.pay

import akka.actor.Actor
import spray.routing.HttpService

class PaySystemServiceActor extends Actor with PaySystemRoute {
  def receive = runRoute(route)

  def actorRefFactory = context
}

trait PaySystemRoute extends HttpService {

  val route = {
    path("pay") {
      post {
        optionalHeaderValueByName("Referer") { refererLocation =>
          formFields('cash) { cash =>
            complete {
              Thread.sleep(1000)
              <body>
                <h1>Payment success!</h1>
                <h1>{cash}$ </h1>
                <a href={refererLocation.getOrElse("#")}>Go back</a>
              </body>
            }
          }
        }
      }
    }
  }
}
