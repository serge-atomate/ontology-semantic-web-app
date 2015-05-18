
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

        """),format.raw/*42.9*/("""$(".statistics").addClass("active");

        var data = [];

        var colors = [
            "#e53935", "#43A047", "#1E88E5", "#FDD835", "#F4511E", "#6D4C41", "#3949AB", "#D81B60", "#8E24AA", "#00ACC1", "#7CB342", "#FFB300", "#546E7A", "#1E88E5", "#C0CA33", "#757575", "#ff1744", "#2979FF", "#FFC400", "#FF3D00"
        ];

        var highlightColors = [
            "#ef9a9a", "#81C784", "#64B5F6", "#FFF176", "#FF8A65", "#A1887F", "#7986CB", "#F06292", "#BA68C8", "#4DD0E1", "#AED581", "#FFD54F", "#90A4AE", "#64B5F6", "#DCE775", "#BDBDBD", "#ff8a80", "#82B1FF", "#FFE57F", "#FF9E80"
        ];

        var index = 1;
        """),_display_(/*55.10*/if(!numbers.isEmpty)/*55.30*/ {_display_(Seq[Any](format.raw/*55.32*/("""
            """),_display_(/*56.14*/for((key,value) <- numbers) yield /*56.41*/ {_display_(Seq[Any](format.raw/*56.43*/("""
                """),format.raw/*57.17*/("""index = Math.floor(Math.random() * 20) + 1;
                data.push("""),format.raw/*58.27*/("""{"""),format.raw/*58.28*/(""" """),format.raw/*58.29*/("""value: """),_display_(/*58.37*/value),format.raw/*58.42*/(""", color: colors[index], highlight: highlightColors[index], label: '"""),_display_(/*58.110*/key),format.raw/*58.113*/("""' """),format.raw/*58.115*/("""}"""),format.raw/*58.116*/(""");
                //console.log(index);
            """)))}),format.raw/*60.14*/("""
        """)))}),format.raw/*61.10*/("""

        """),format.raw/*63.9*/("""var ctx = document.getElementById("chart-area").getContext("2d");
        var ctx2 = document.getElementById("chart-area2").getContext("2d");
        var ctx3 = document.getElementById("chart-area3").getContext("2d");
        var ctx4 = document.getElementById("chart-area4").getContext("2d");

        var options = """),format.raw/*68.23*/("""{"""),format.raw/*68.24*/("""
            """),format.raw/*69.13*/("""legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++)"""),format.raw/*69.116*/("""{"""),format.raw/*69.117*/("""%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label)"""),format.raw/*69.213*/("""{"""),format.raw/*69.214*/("""%><%=segments[i].label%> (<%=segments[i].value%>)<%"""),format.raw/*69.265*/("""}"""),format.raw/*69.266*/("""%></li><%"""),format.raw/*69.275*/("""}"""),format.raw/*69.276*/("""%></ul>"
        """),format.raw/*70.9*/("""}"""),format.raw/*70.10*/("""

        """),format.raw/*72.9*/("""var helpers = Chart.helpers;

        window.chart = new Chart(ctx).Pie(data, options);

        var legendHolder = document.createElement('div');
        legendHolder.innerHTML = window.chart.generateLegend();

        // Include a html legend template after the module doughnut itself
        helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*80.87*/("""{"""),format.raw/*80.88*/("""
            """),format.raw/*81.13*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*81.67*/("""{"""),format.raw/*81.68*/("""
                """),format.raw/*82.17*/("""var activeSegment = window.chart.segments[index];
                activeSegment.save();
                window.chart.showTooltip([activeSegment]);
                activeSegment.restore();
            """),format.raw/*86.13*/("""}"""),format.raw/*86.14*/(""");
        """),format.raw/*87.9*/("""}"""),format.raw/*87.10*/(""");
        helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*88.75*/("""{"""),format.raw/*88.76*/("""
            """),format.raw/*89.13*/("""window.chart.draw();
        """),format.raw/*90.9*/("""}"""),format.raw/*90.10*/(""");

        window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);

        var labelsArray = [];
        var dataArray = [];

        """),_display_(/*97.10*/if(!numbers.isEmpty)/*97.30*/ {_display_(Seq[Any](format.raw/*97.32*/("""
            """),_display_(/*98.14*/for((key,value) <- numbers) yield /*98.41*/ {_display_(Seq[Any](format.raw/*98.43*/("""
                """),format.raw/*99.17*/("""dataArray.push("""),_display_(/*99.33*/value),format.raw/*99.38*/(""");
                labelsArray.push(""""),_display_(/*100.36*/key),format.raw/*100.39*/("""");
            """)))}),format.raw/*101.14*/("""
        """)))}),format.raw/*102.10*/("""

        """),format.raw/*104.9*/("""var data2 = """),format.raw/*104.21*/("""{"""),format.raw/*104.22*/("""
            """),format.raw/*105.13*/("""labels: labelsArray,
            datasets: [
               /* """),format.raw/*107.19*/("""{"""),format.raw/*107.20*/("""
                    """),format.raw/*108.21*/("""label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                """),format.raw/*114.17*/("""}"""),format.raw/*114.18*/(""", */
                """),format.raw/*115.17*/("""{"""),format.raw/*115.18*/("""
                    """),format.raw/*116.21*/("""label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: dataArray
                """),format.raw/*122.17*/("""}"""),format.raw/*122.18*/("""
            """),format.raw/*123.13*/("""]
        """),format.raw/*124.9*/("""}"""),format.raw/*124.10*/(""";

        $('#chartType a').click(function (e) """),format.raw/*126.46*/("""{"""),format.raw/*126.47*/("""
            """),format.raw/*127.13*/("""e.preventDefault();
            var tabId = $(this).attr('id');
            $(this).tab('show');

            if (tabId == "bar-tab") """),format.raw/*131.37*/("""{"""),format.raw/*131.38*/("""
                """),format.raw/*132.17*/("""window.chart = new Chart(ctx2).Bar(data2);
                if($("#polar").find(".polararea-legend")) """),format.raw/*133.59*/("""{"""),format.raw/*133.60*/("""
                    """),format.raw/*134.21*/("""// console.log($("#polar").find(".polararea-legend"));
                    return false;
                """),format.raw/*136.17*/("""}"""),format.raw/*136.18*/("""
            """),format.raw/*137.13*/("""}"""),format.raw/*137.14*/("""
            """),format.raw/*138.13*/("""else if (tabId == "polar-tab") """),format.raw/*138.44*/("""{"""),format.raw/*138.45*/("""
                """),format.raw/*139.17*/("""window.chart = new Chart(ctx3).PolarArea(data);
                return false;
            """),format.raw/*141.13*/("""}"""),format.raw/*141.14*/("""
            """),format.raw/*142.13*/("""else if (tabId == "radar-tab") """),format.raw/*142.44*/("""{"""),format.raw/*142.45*/("""
                """),format.raw/*143.17*/("""window.chart = new Chart(ctx4).Radar(data2);
                return false;
            """),format.raw/*145.13*/("""}"""),format.raw/*145.14*/("""
            """),format.raw/*146.13*/("""else if (tabId == "pie-tab") """),format.raw/*146.42*/("""{"""),format.raw/*146.43*/("""
                """),format.raw/*147.17*/("""window.chart = new Chart(ctx).Pie(data);
                if($("#pie").find(".pie-legend")) """),format.raw/*148.51*/("""{"""),format.raw/*148.52*/("""
                    """),format.raw/*149.21*/("""return false;
                """),format.raw/*150.17*/("""}"""),format.raw/*150.18*/("""
            """),format.raw/*151.13*/("""}"""),format.raw/*151.14*/("""

            """),format.raw/*153.13*/("""var legendHolder = document.createElement('div');
            legendHolder.innerHTML = window.chart.generateLegend();

            // Include a html legend template after the module doughnut itself
            helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*157.91*/("""{"""),format.raw/*157.92*/("""
                """),format.raw/*158.17*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*158.71*/("""{"""),format.raw/*158.72*/("""
                    """),format.raw/*159.21*/("""var activeSegment = window.chart.segments[index];
                    activeSegment.save();
                    window.chart.showTooltip([activeSegment]);
                    activeSegment.restore();
                """),format.raw/*163.17*/("""}"""),format.raw/*163.18*/(""");
            """),format.raw/*164.13*/("""}"""),format.raw/*164.14*/(""");
            helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*165.79*/("""{"""),format.raw/*165.80*/("""
                """),format.raw/*166.17*/("""window.chart.draw();
            """),format.raw/*167.13*/("""}"""),format.raw/*167.14*/(""");

            window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);
        """),format.raw/*170.9*/("""}"""),format.raw/*170.10*/(""");

    """),format.raw/*172.5*/("""}"""),format.raw/*172.6*/(""");
</script>

""")))}))}
  }

  def render(numbers:Map[String, Integer]): play.twirl.api.HtmlFormat.Appendable = apply(numbers)

  def f:((Map[String, Integer]) => play.twirl.api.HtmlFormat.Appendable) = (numbers) => apply(numbers)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon May 18 16:41:51 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/publications.scala.html
                  HASH: 4149c6858690127c885d56b4e8ce380f0b9bbf34
                  MATRIX: 744->1|911->32|939->85|966->87|1083->195|1123->197|1151->199|2920->1940|2949->1941|2986->1951|3648->2586|3677->2606|3717->2608|3758->2622|3801->2649|3841->2651|3886->2668|3984->2738|4013->2739|4042->2740|4077->2748|4103->2753|4199->2821|4224->2824|4255->2826|4285->2827|4370->2881|4411->2891|4448->2901|4793->3218|4822->3219|4863->3232|4995->3335|5025->3336|5150->3432|5180->3433|5260->3484|5290->3485|5328->3494|5358->3495|5402->3512|5431->3513|5468->3523|5869->3896|5898->3897|5939->3910|6021->3964|6050->3965|6095->3982|6323->4182|6352->4183|6390->4194|6419->4195|6524->4272|6553->4273|6594->4286|6650->4315|6679->4316|6873->4483|6902->4503|6942->4505|6983->4519|7026->4546|7066->4548|7111->4565|7154->4581|7180->4586|7246->4624|7271->4627|7320->4644|7362->4654|7400->4664|7441->4676|7471->4677|7513->4690|7605->4753|7635->4754|7685->4775|8047->5108|8077->5109|8127->5130|8157->5131|8207->5152|8551->5467|8581->5468|8623->5481|8661->5491|8691->5492|8768->5540|8798->5541|8840->5554|9003->5688|9033->5689|9079->5706|9209->5807|9239->5808|9289->5829|9423->5934|9453->5935|9495->5948|9525->5949|9567->5962|9627->5993|9657->5994|9703->6011|9822->6101|9852->6102|9894->6115|9954->6146|9984->6147|10030->6164|10146->6251|10176->6252|10218->6265|10276->6294|10306->6295|10352->6312|10472->6403|10502->6404|10552->6425|10611->6455|10641->6456|10683->6469|10713->6470|10756->6484|11073->6772|11103->6773|11149->6790|11232->6844|11262->6845|11312->6866|11557->7082|11587->7083|11631->7098|11661->7099|11771->7180|11801->7181|11847->7198|11909->7231|11939->7232|12077->7342|12107->7343|12143->7351|12172->7352
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|67->40|67->40|69->42|82->55|82->55|82->55|83->56|83->56|83->56|84->57|85->58|85->58|85->58|85->58|85->58|85->58|85->58|85->58|85->58|87->60|88->61|90->63|95->68|95->68|96->69|96->69|96->69|96->69|96->69|96->69|96->69|96->69|96->69|97->70|97->70|99->72|107->80|107->80|108->81|108->81|108->81|109->82|113->86|113->86|114->87|114->87|115->88|115->88|116->89|117->90|117->90|124->97|124->97|124->97|125->98|125->98|125->98|126->99|126->99|126->99|127->100|127->100|128->101|129->102|131->104|131->104|131->104|132->105|134->107|134->107|135->108|141->114|141->114|142->115|142->115|143->116|149->122|149->122|150->123|151->124|151->124|153->126|153->126|154->127|158->131|158->131|159->132|160->133|160->133|161->134|163->136|163->136|164->137|164->137|165->138|165->138|165->138|166->139|168->141|168->141|169->142|169->142|169->142|170->143|172->145|172->145|173->146|173->146|173->146|174->147|175->148|175->148|176->149|177->150|177->150|178->151|178->151|180->153|184->157|184->157|185->158|185->158|185->158|186->159|190->163|190->163|191->164|191->164|192->165|192->165|193->166|194->167|194->167|197->170|197->170|199->172|199->172
                  -- GENERATED --
              */
          