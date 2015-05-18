
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*10.59*/routes/*10.65*/.Assets.at("images/favicon-16x16.png")),format.raw/*10.103*/("""">

        <link rel="stylesheet" media="screen" href=""""),_display_(/*12.54*/routes/*12.60*/.Assets.at("stylesheets/main.css")),format.raw/*12.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*13.54*/routes/*13.60*/.Assets.at("stylesheets/bootstrap.css")),format.raw/*13.99*/("""">

        <script src=""""),_display_(/*15.23*/routes/*15.29*/.Assets.at("javascripts/jquery-2.1.3.min.js")),format.raw/*15.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*16.23*/routes/*16.29*/.Assets.at("javascripts/bootstrap.js")),format.raw/*16.67*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*17.23*/routes/*17.29*/.Assets.at("javascripts/init.js")),format.raw/*17.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*18.23*/routes/*18.29*/.Assets.at("javascripts/Chart.js")),format.raw/*18.63*/("""" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid">
                <div class="row page-header">
                    <div class="col-md-4">
                        <a class="logo-tud" target="_blank" href="http://tu-dresden.de/"></a>
                    </div>
                    <div class="col-md-4">
                        <a class="logo" href="/"><h3>Ontology-Driven Semantic Web Application</h3></a>
                        <h5 class="logo">Institute AIFB of the University of Karlsruhe</h5>
                    </div>
                    <div class="col-md-4">
                        <a class="logo-iccl" target="_blank" href="https://ddll.inf.tu-dresden.de/web/International_Center_for_Computational_Logic"></a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2 list-group mainmenu">
                        <a class="list-group-item" href="/">Browse Ontology</a>
                        <!-- <a class="list-group-item" href="/classes">Classes</a> -->
                        <a class="list-group-item statistics" href="/statistics">Statistics</a>
                    </div>

                    <div class="col-md-10">
                        """),_display_(/*43.26*/content),format.raw/*43.33*/("""
                    """),format.raw/*44.21*/("""</div>
                </div>

                <div class="row footer">
                    <div class="col-md-12">
                        <p>© 2015 <a class="developedby" target="_blank" href="http://stratan.eu">Developed by: Serge Stratan</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon May 18 16:41:50 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/main.scala.html
                  HASH: e90547c4530bbfecb446716b2dc9cc60a9c3e1a4
                  MATRIX: 727->1|845->31|873->33|950->84|975->89|1147->234|1162->240|1222->278|1306->335|1321->341|1376->375|1459->431|1474->437|1534->476|1587->502|1602->508|1668->553|1752->610|1767->616|1826->654|1910->711|1925->717|1979->750|2063->807|2078->813|2133->847|3471->2158|3499->2165|3548->2186
                  LINES: 26->1|29->1|31->3|35->7|35->7|38->10|38->10|38->10|40->12|40->12|40->12|41->13|41->13|41->13|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|71->43|71->43|72->44
                  -- GENERATED --
              */
          