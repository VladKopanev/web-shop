package com.ua.nure.pay

import spray.json.DefaultJsonProtocol

/**
  * Created by Влад on 18.04.2016.
  */
case class Stuff(id: Int, data: String)

object Stuff extends DefaultJsonProtocol {
    implicit val stuffFormat = jsonFormat2(Stuff.apply)
}
