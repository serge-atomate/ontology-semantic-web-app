
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
object publications extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Map[String, Integer],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(numbers: Map[String, Integer]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.33*/("""

"""),format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Statistics | Institute of AIFB of the Karlsruhe Univesity | Ontology-Driven Semantic Web Application")/*6.110*/ {_display_(Seq[Any](format.raw/*6.112*/("""

"""),format.raw/*8.1*/("""<div class="row">
    <div class="col-md-12">

        <h2>Number of Publications by year</h2>

        <ul id="chartType" class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#pie" id="pie-tab" role="tab" data-toggle="tab" aria-controls="pie" aria-expanded="true">Pie Chart</a></li>
            <li role="presentation"><a href="#bar" role="tab" id="bar-tab" data-toggle="tab" aria-controls="bar">Bar Chart</a></li>
            <li role="presentation"><a href="#polar" role="tab" id="polar-tab" data-toggle="tab" aria-controls="polar">Polar Area Chart</a></li>
            <li role="presentation"><a href="#radar" role="tab" id="radar-tab" data-toggle="tab" aria-controls="radar">Radar Chart</a></li>
        </ul>

        <div id="myTabContent" class="tab-content">
            <div role="tabpanel" class="tab-pane in active" id="pie" aria-labelledby="pie-tab">
                <div id="canvas-holder"><canvas id="chart-area" width="500" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="bar" aria-labelledby="bar-tab">
                <div id="canvas-holder"><canvas id="chart-area2" width="700" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="polar" aria-labelledby="polar-tab">
                <div id="canvas-holder"><canvas id="chart-area3" width="500" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="radar" aria-labelledby="radar-tab">
                <div id="canvas-holder"><canvas id="chart-area4" width="500" height="500"/></div>
            </div>
        </div>

    </div>
</div>


<script language="JavaScript">
    $(document).ready(function() """),format.raw/*40.34*/("""{"""),format.raw/*40.35*/("""

        """),format.raw/*42.9*/("""var data = [];

        var colors = [
            "#e53935", "#43A047", "#1E88E5", "#FDD835", "#F4511E", "#6D4C41", "#3949AB", "#D81B60", "#8E24AA", "#00ACC1", "#7CB342", "#FFB300", "#546E7A", "#1E88E5", "#C0CA33", "#757575", "#ff1744", "#2979FF", "#FFC400", "#FF3D00"
        ];

        var highlightColors = [
            "#ef9a9a", "#81C784", "#64B5F6", "#FFF176", "#FF8A65", "#A1887F", "#7986CB", "#F06292", "#BA68C8", "#4DD0E1", "#AED581", "#FFD54F", "#90A4AE", "#64B5F6", "#DCE775", "#BDBDBD", "#ff8a80", "#82B1FF", "#FFE57F", "#FF9E80"
        ];

        var index = 1;
        """),_display_(/*53.10*/if(!numbers.isEmpty)/*53.30*/ {_display_(Seq[Any](format.raw/*53.32*/("""
            """),_display_(/*54.14*/for((key,value) <- numbers) yield /*54.41*/ {_display_(Seq[Any](format.raw/*54.43*/("""
                """),format.raw/*55.17*/("""index = Math.floor(Math.random() * 20) + 1;
                data.push("""),format.raw/*56.27*/("""{"""),format.raw/*56.28*/(""" """),format.raw/*56.29*/("""value: """),_display_(/*56.37*/value),format.raw/*56.42*/(""", color: colors[index], highlight: highlightColors[index], label: '"""),_display_(/*56.110*/key),format.raw/*56.113*/("""' """),format.raw/*56.115*/("""}"""),format.raw/*56.116*/(""");
                //console.log(index);
            """)))}),format.raw/*58.14*/("""
        """)))}),format.raw/*59.10*/("""

        """),format.raw/*61.9*/("""var ctx = document.getElementById("chart-area").getContext("2d");
        var ctx2 = document.getElementById("chart-area2").getContext("2d");
        var ctx3 = document.getElementById("chart-area3").getContext("2d");
        var ctx4 = document.getElementById("chart-area4").getContext("2d");

        var options = """),format.raw/*66.23*/("""{"""),format.raw/*66.24*/("""
            """),format.raw/*67.13*/("""legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++)"""),format.raw/*67.116*/("""{"""),format.raw/*67.117*/("""%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label)"""),format.raw/*67.213*/("""{"""),format.raw/*67.214*/("""%><%=segments[i].label%> (<%=segments[i].value%>)<%"""),format.raw/*67.265*/("""}"""),format.raw/*67.266*/("""%></li><%"""),format.raw/*67.275*/("""}"""),format.raw/*67.276*/("""%></ul>"
        """),format.raw/*68.9*/("""}"""),format.raw/*68.10*/("""

        """),format.raw/*70.9*/("""var helpers = Chart.helpers;

        window.chart = new Chart(ctx).Pie(data, options);

        var legendHolder = document.createElement('div');
        legendHolder.innerHTML = window.chart.generateLegend();

        // Include a html legend template after the module doughnut itself
        helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*78.87*/("""{"""),format.raw/*78.88*/("""
            """),format.raw/*79.13*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*79.67*/("""{"""),format.raw/*79.68*/("""
                """),format.raw/*80.17*/("""var activeSegment = window.chart.segments[index];
                activeSegment.save();
                window.chart.showTooltip([activeSegment]);
                activeSegment.restore();
            """),format.raw/*84.13*/("""}"""),format.raw/*84.14*/(""");
        """),format.raw/*85.9*/("""}"""),format.raw/*85.10*/(""");
        helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*86.75*/("""{"""),format.raw/*86.76*/("""
            """),format.raw/*87.13*/("""window.chart.draw();
        """),format.raw/*88.9*/("""}"""),format.raw/*88.10*/(""");

        window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);

        var labelsArray = [];
        var dataArray = [];

        """),_display_(/*95.10*/if(!numbers.isEmpty)/*95.30*/ {_display_(Seq[Any](format.raw/*95.32*/("""
            """),_display_(/*96.14*/for((key,value) <- numbers) yield /*96.41*/ {_display_(Seq[Any](format.raw/*96.43*/("""
                """),format.raw/*97.17*/("""dataArray.push("""),_display_(/*97.33*/value),format.raw/*97.38*/(""");
                labelsArray.push(""""),_display_(/*98.36*/key),format.raw/*98.39*/("""");
            """)))}),format.raw/*99.14*/("""
        """)))}),format.raw/*100.10*/("""

        """),format.raw/*102.9*/("""var data2 = """),format.raw/*102.21*/("""{"""),format.raw/*102.22*/("""
            """),format.raw/*103.13*/("""labels: labelsArray,
            datasets: [
               /* """),format.raw/*105.19*/("""{"""),format.raw/*105.20*/("""
                    """),format.raw/*106.21*/("""label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                """),format.raw/*112.17*/("""}"""),format.raw/*112.18*/(""", */
                """),format.raw/*113.17*/("""{"""),format.raw/*113.18*/("""
                    """),format.raw/*114.21*/("""label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: dataArray
                """),format.raw/*120.17*/("""}"""),format.raw/*120.18*/("""
            """),format.raw/*121.13*/("""]
        """),format.raw/*122.9*/("""}"""),format.raw/*122.10*/(""";

        $('#chartType a').click(function (e) """),format.raw/*124.46*/("""{"""),format.raw/*124.47*/("""
            """),format.raw/*125.13*/("""e.preventDefault();
            var tabId = $(this).attr('id');
            $(this).tab('show');

            if (tabId == "bar-tab") """),format.raw/*129.37*/("""{"""),format.raw/*129.38*/("""
                """),format.raw/*130.17*/("""window.chart = new Chart(ctx2).Bar(data2);
                if($("#polar").find(".polararea-legend")) """),format.raw/*131.59*/("""{"""),format.raw/*131.60*/("""
                    """),format.raw/*132.21*/("""console.log($("#polar").find(".polararea-legend"));
                    return false;
                """),format.raw/*134.17*/("""}"""),format.raw/*134.18*/("""
            """),format.raw/*135.13*/("""}"""),format.raw/*135.14*/("""
            """),format.raw/*136.13*/("""else if (tabId == "polar-tab") """),format.raw/*136.44*/("""{"""),format.raw/*136.45*/("""
                """),format.raw/*137.17*/("""window.chart = new Chart(ctx3).PolarArea(data);
                return false;
            """),format.raw/*139.13*/("""}"""),format.raw/*139.14*/("""
            """),format.raw/*140.13*/("""else if (tabId == "radar-tab") """),format.raw/*140.44*/("""{"""),format.raw/*140.45*/("""
                """),format.raw/*141.17*/("""window.chart = new Chart(ctx4).Radar(data2);
                return false;
            """),format.raw/*143.13*/("""}"""),format.raw/*143.14*/("""
            """),format.raw/*144.13*/("""else if (tabId == "pie-tab") """),format.raw/*144.42*/("""{"""),format.raw/*144.43*/("""
                """),format.raw/*145.17*/("""window.chart = new Chart(ctx).Pie(data);
                if($("#pie").find(".pie-legend")) """),format.raw/*146.51*/("""{"""),format.raw/*146.52*/("""
                    """),format.raw/*147.21*/("""return false;
                """),format.raw/*148.17*/("""}"""),format.raw/*148.18*/("""
            """),format.raw/*149.13*/("""}"""),format.raw/*149.14*/("""

            """),format.raw/*151.13*/("""var legendHolder = document.createElement('div');
            legendHolder.innerHTML = window.chart.generateLegend();

            // Include a html legend template after the module doughnut itself
            helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*155.91*/("""{"""),format.raw/*155.92*/("""
                """),format.raw/*156.17*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*156.71*/("""{"""),format.raw/*156.72*/("""
                    """),format.raw/*157.21*/("""var activeSegment = window.chart.segments[index];
                    activeSegment.save();
                    window.chart.showTooltip([activeSegment]);
                    activeSegment.restore();
                """),format.raw/*161.17*/("""}"""),format.raw/*161.18*/(""");
            """),format.raw/*162.13*/("""}"""),format.raw/*162.14*/(""");
            helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*163.79*/("""{"""),format.raw/*163.80*/("""
                """),format.raw/*164.17*/("""window.chart.draw();
            """),format.raw/*165.13*/("""}"""),format.raw/*165.14*/(""");

            window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);
        """),format.raw/*168.9*/("""}"""),format.raw/*168.10*/(""");

    """),format.raw/*170.5*/("""}"""),format.raw/*170.6*/(""");
</script>

""")))}))}
  }

  def render(numbers:Map[String, Integer]): play.twirl.api.HtmlFormat.Appendable = apply(numbers)

  def f:((Map[String, Integer]) => play.twirl.api.HtmlFormat.Appendable) = (numbers) => apply(numbers)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 13 14:20:25 CEST 2015
                  SOURCE: /Users/serge/Htdocs/Project-OWL/app/views/publications.scala.html
                  HASH: 13a48a0f440d562d4209ec62f64d95be29e5e090
                  MATRIX: 744->1|911->32|939->85|966->87|1083->195|1123->197|1151->199|2920->1940|2949->1941|2986->1951|3602->2540|3631->2560|3671->2562|3712->2576|3755->2603|3795->2605|3840->2622|3938->2692|3967->2693|3996->2694|4031->2702|4057->2707|4153->2775|4178->2778|4209->2780|4239->2781|4324->2835|4365->2845|4402->2855|4747->3172|4776->3173|4817->3186|4949->3289|4979->3290|5104->3386|5134->3387|5214->3438|5244->3439|5282->3448|5312->3449|5356->3466|5385->3467|5422->3477|5823->3850|5852->3851|5893->3864|5975->3918|6004->3919|6049->3936|6277->4136|6306->4137|6344->4148|6373->4149|6478->4226|6507->4227|6548->4240|6604->4269|6633->4270|6827->4437|6856->4457|6896->4459|6937->4473|6980->4500|7020->4502|7065->4519|7108->4535|7134->4540|7199->4578|7223->4581|7271->4598|7313->4608|7351->4618|7392->4630|7422->4631|7464->4644|7556->4707|7586->4708|7636->4729|7998->5062|8028->5063|8078->5084|8108->5085|8158->5106|8502->5421|8532->5422|8574->5435|8612->5445|8642->5446|8719->5494|8749->5495|8791->5508|8954->5642|8984->5643|9030->5660|9160->5761|9190->5762|9240->5783|9371->5885|9401->5886|9443->5899|9473->5900|9515->5913|9575->5944|9605->5945|9651->5962|9770->6052|9800->6053|9842->6066|9902->6097|9932->6098|9978->6115|10094->6202|10124->6203|10166->6216|10224->6245|10254->6246|10300->6263|10420->6354|10450->6355|10500->6376|10559->6406|10589->6407|10631->6420|10661->6421|10704->6435|11021->6723|11051->6724|11097->6741|11180->6795|11210->6796|11260->6817|11505->7033|11535->7034|11579->7049|11609->7050|11719->7131|11749->7132|11795->7149|11857->7182|11887->7183|12025->7293|12055->7294|12091->7302|12120->7303
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|67->40|67->40|69->42|80->53|80->53|80->53|81->54|81->54|81->54|82->55|83->56|83->56|83->56|83->56|83->56|83->56|83->56|83->56|83->56|85->58|86->59|88->61|93->66|93->66|94->67|94->67|94->67|94->67|94->67|94->67|94->67|94->67|94->67|95->68|95->68|97->70|105->78|105->78|106->79|106->79|106->79|107->80|111->84|111->84|112->85|112->85|113->86|113->86|114->87|115->88|115->88|122->95|122->95|122->95|123->96|123->96|123->96|124->97|124->97|124->97|125->98|125->98|126->99|127->100|129->102|129->102|129->102|130->103|132->105|132->105|133->106|139->112|139->112|140->113|140->113|141->114|147->120|147->120|148->121|149->122|149->122|151->124|151->124|152->125|156->129|156->129|157->130|158->131|158->131|159->132|161->134|161->134|162->135|162->135|163->136|163->136|163->136|164->137|166->139|166->139|167->140|167->140|167->140|168->141|170->143|170->143|171->144|171->144|171->144|172->145|173->146|173->146|174->147|175->148|175->148|176->149|176->149|178->151|182->155|182->155|183->156|183->156|183->156|184->157|188->161|188->161|189->162|189->162|190->163|190->163|191->164|192->165|192->165|195->168|195->168|197->170|197->170
                  -- GENERATED --
              */
          