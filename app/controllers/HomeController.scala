package controllers

import javax.inject._
import play.api._
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
    Ok(views.html.index())
  }

  //
  // text/plain
  //
  def hello() = Action { implicit request: Request[AnyContent] =>
    Ok("hello!")
  }

  //
  // application/json
  //
  def hello_json() = Action { implicit request: Request[AnyContent] =>
  var locations = (
    Map("address" -> "135-0033 東京都江東区深川 99-99-99", "email" -> "jimi.hendrix@docomo.ne.jp"),
      Map("address" -> "603-8467 京都府京都市北区鷹峯南鷹峯町 999-999", "email" -> "jimi.hendrix@docomo.ne.jp"),
    )
    val json = Json.toJson(locations)
    Ok(json)
  }
}
