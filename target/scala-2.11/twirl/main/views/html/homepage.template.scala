
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
object homepage extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(comment: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.19*/("""

"""),format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Institute of AIFB of the Karlsruhe Univesity | Ontology-Driven Semantic Web Application")/*6.97*/ {_display_(Seq[Any](format.raw/*6.99*/("""

	"""),format.raw/*8.2*/("""Comment: """),_display_(/*8.12*/comment),format.raw/*8.19*/("""


"""),format.raw/*11.1*/("""<script language="JavaScript">
    $(document).ready(function() """),format.raw/*12.34*/("""{"""),format.raw/*12.35*/("""


    """),format.raw/*15.5*/("""}"""),format.raw/*15.6*/(""");
</script>

""")))}))}
  }

  def render(comment:String): play.twirl.api.HtmlFormat.Appendable = apply(comment)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (comment) => apply(comment)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue May 19 16:17:18 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/homepage.scala.html
                  HASH: 623644f85c2430219cc63fdacbb55f7b7de9ad00
                  MATRIX: 726->1|879->18|907->71|934->73|1037->168|1076->170|1105->173|1141->183|1168->190|1198->193|1290->257|1319->258|1353->265|1381->266
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|35->8|35->8|38->11|39->12|39->12|42->15|42->15
                  -- GENERATED --
              */
          