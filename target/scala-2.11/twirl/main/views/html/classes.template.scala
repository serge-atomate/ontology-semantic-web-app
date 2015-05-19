
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
object classes extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(htmlClasses: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.23*/("""

"""),format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Class Hierarchy | Institute of AIFB of the Karlsruhe Univesity | Ontology-Driven Semantic Web Application")/*6.115*/ {_display_(Seq[Any](format.raw/*6.117*/("""

"""),format.raw/*8.1*/("""<div class="row">
    <div class="col-md-4 classes">
        <div class="mainclasses" data-toggle="popover" data-trigger="focus" data-content="Please select a class to browse data">
            """),_display_(/*11.14*/if(!htmlClasses.isEmpty)/*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
                """),format.raw/*12.17*/("""<h4>Class Hierarchy</h4>
                """),_display_(/*13.18*/Html(htmlClasses)),format.raw/*13.35*/("""
            """)))}),format.raw/*14.14*/("""
            """),format.raw/*15.13*/("""<div class="unavailable" style="display:none;"></div>
        </div>
    </div>
    <div class="col-md-8 individs">
        <img style="display:none;" id="loaderInds" src=""""),_display_(/*19.58*/routes/*19.64*/.Assets.at("images/ajax-loader.gif")),format.raw/*19.100*/("""" />
        <div class="individuals">
            <ul><li class='noData'>Please select a class from the right, to browse ontology data</li></ul>
        </div>
    </div>
</div>


<script language="JavaScript">
    $(document).ready(function() """),format.raw/*28.34*/("""{"""),format.raw/*28.35*/("""

        """),format.raw/*30.9*/("""$(".level1").show();

        // $('.mainclasses').popover('show');

        $('.cls').click(function(e)"""),format.raw/*34.36*/("""{"""),format.raw/*34.37*/("""
            """),format.raw/*35.13*/("""e.preventDefault();

            // $('.mainclasses').popover('hide');

            // show subclasses if not yet opened
            if(!$(this).parent( "li" ).hasClass('selected')) """),format.raw/*40.62*/("""{"""),format.raw/*40.63*/("""
                """),format.raw/*41.17*/("""$(this).parent( "li" ).find('ul').first().addClass('toggled').slideToggle();
            """),format.raw/*42.13*/("""}"""),format.raw/*42.14*/(""" 

            """),format.raw/*44.13*/("""var value = $(this).attr('href');

            getIndividuals($(this),value);

        """),format.raw/*48.9*/("""}"""),format.raw/*48.10*/(""");

    """),format.raw/*50.5*/("""}"""),format.raw/*50.6*/(""");
</script>

""")))}))}
  }

  def render(htmlClasses:String): play.twirl.api.HtmlFormat.Appendable = apply(htmlClasses)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (htmlClasses) => apply(htmlClasses)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue May 19 16:17:18 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/classes.scala.html
                  HASH: 89b52f5633b68151cf64c2c3b65cf9339f30320c
                  MATRIX: 725->1|882->22|910->75|937->77|1059->190|1099->192|1127->194|1349->389|1382->413|1422->415|1467->432|1536->474|1574->491|1619->505|1660->518|1860->691|1875->697|1933->733|2206->978|2235->979|2272->989|2404->1093|2433->1094|2474->1107|2684->1289|2713->1290|2758->1307|2875->1396|2904->1397|2947->1412|3061->1499|3090->1500|3125->1508|3153->1509
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|38->11|38->11|38->11|39->12|40->13|40->13|41->14|42->15|46->19|46->19|46->19|55->28|55->28|57->30|61->34|61->34|62->35|67->40|67->40|68->41|69->42|69->42|71->44|75->48|75->48|77->50|77->50
                  -- GENERATED --
              */
          