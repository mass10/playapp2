package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index()).as(contentType = "text/html")
  }

  //
  // 単純な GET リクエストに対して text/plain を返します。
  //
  def hello() = Action { implicit request: Request[AnyContent] =>
    Ok("<!DOCTYPE html><html>hello!</html>").as(contentType = "text/html")
  }

  //
  // 単純な GET リクエストに対して application/json を返します。
  //
  def hello_json() = Action { implicit request: Request[AnyContent] =>
    val locations = (
      Map("address" -> "135-0033 東京都江東区深川 99-99-99", "email" -> "jimi.hendrix@docomo.ne.jp"),
      Map("address" -> "603-8467 京都府京都市北区鷹峯南鷹峯町 999-999", "email" -> "jimi.hendrix@docomo.ne.jp"),
    )
    val json = Json.toJson(locations)
    Ok(json).as(contentType = "application/json")
  }

  //
  // 単純な JSON リクエストに対して application/json を返します。
  //
  def search() = Action { implicit request: Request[AnyContent] =>
    val locations = List(
      MapEntry(address = "135-0033 東京都江東区深川 99-99-99", email = "jimi.hendrix@docomo.ne.jp"),
      MapEntry(address = "603-8467 京都府京都市北区鷹峯南鷹峯町 999-999", email = "jimi.hendrix@docomo.ne.jp"),
    )
    val result = locations.map(e => { e.address })
    implicit val mapEntryWriter = Json.writes[MapEntry]
    val json = Json.toJson(result)
    Ok(json).as(contentType = "application/json")
  }
}

case class MapEntry(address: String, email: String) {
  //  def asJson() = Json.toJson(this)
}

object MapEntry {
  implicit val writes: Writes[MapEntry] = Json.writes[MapEntry]
}
