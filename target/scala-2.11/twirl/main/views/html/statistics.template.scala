
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
object statistics extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[List[String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(numbers: List[String], classes: List[String]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.48*/("""

"""),format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Statistics | Institute of AIFB of the Karlsruhe Univesity | Ontology-Driven Semantic Web Application")/*6.110*/ {_display_(Seq[Any](format.raw/*6.112*/("""

"""),format.raw/*8.1*/("""<div class="row">
    <div class="col-md-6">
        <select class="form-control" id="selectedClass" data-toggle="popover" data-trigger="focus" data-content="Please select a class to generate charts">
            <option value="">Select a Class</option>
            <option value="Person">Person</option>
            <option value="Employee">Employee</option>
            <option value="Student">Student</option>
            <option value="Publication">Publication</option>
        </select>
    </div>

    <div class="col-md-6">
        <a class="stats_link" href="/statistics-publications">Number of Publications by year (Charts)</a>
    </div>
</div>

<div class="row charts">
    <div class="col-md-12">

        <h2>Number of Individuals by Class</h2>

        <ul id="chartType" class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#pie" id="pie-tab" role="tab" data-toggle="tab" aria-controls="pie" aria-expanded="true">Pie Chart</a></li>
            <li role="presentation"><a href="#polar" role="tab" id="polar-tab" data-toggle="tab" aria-controls="polar">Bar Chart</a></li>
            <li role="presentation"><a href="#bar" role="tab" id="bar-tab" data-toggle="tab" aria-controls="bar">Polar Area Chart</a></li>
            <li role="presentation"><a href="#radar" role="tab" id="radar-tab" data-toggle="tab" aria-controls="radar">Radar Chart</a></li>
        </ul>

        <div id="myTabContent" class="tab-content">
            <div role="tabpanel" class="tab-pane in active" id="pie" aria-labelledby="pie-tab">
                <div id="canvas-holder"><canvas id="chart-area" width="500" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="polar" aria-labelledby="polar-tab">
                <div id="canvas-holder"><canvas id="chart-area2" width="700" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="bar" aria-labelledby="bar-tab">
                <div id="canvas-holder"><canvas id="chart-area3" width="500" height="500"/></div>
            </div>
            <div role="tabpanel" class="tab-pane" id="radar" aria-labelledby="radar-tab">
                <div id="canvas-holder"><canvas id="chart-area4" width="500" height="500"/></div>
            </div>
        </div>
    </div>
</div>


<script language="JavaScript">

    $(document).ready(function() """),format.raw/*56.34*/("""{"""),format.raw/*56.35*/("""

        """),format.raw/*58.9*/("""var urlClass = getParameterByName('q');
        if(urlClass != "") """),format.raw/*59.28*/("""{"""),format.raw/*59.29*/("""
            """),format.raw/*60.13*/("""$("#selectedClass").val(urlClass);
        """),format.raw/*61.9*/("""}"""),format.raw/*61.10*/(""" """),format.raw/*61.11*/("""else """),format.raw/*61.16*/("""{"""),format.raw/*61.17*/("""
            """),format.raw/*62.13*/("""$('#selectedClass').popover('toggle');
        """),format.raw/*63.9*/("""}"""),format.raw/*63.10*/("""

        """),format.raw/*65.9*/("""$("#selectedClass").on('change', function () """),format.raw/*65.54*/("""{"""),format.raw/*65.55*/("""
            """),format.raw/*66.13*/("""if($("#selectedClass").val()!="") """),format.raw/*66.47*/("""{"""),format.raw/*66.48*/("""
                """),format.raw/*67.17*/("""window.location.replace("http://xen9.helios.atomate.net:9000/statistics?q="+$("#selectedClass").val());
            """),format.raw/*68.13*/("""}"""),format.raw/*68.14*/("""
        """),format.raw/*69.9*/("""}"""),format.raw/*69.10*/(""");

        // ===== Initialize Charts ======
        var data = [];
        var labelsArray = [];
        var dataArray = [];
        var ctx = document.getElementById("chart-area").getContext("2d");
        var ctx2 = document.getElementById("chart-area2").getContext("2d");
        var ctx3 = document.getElementById("chart-area3").getContext("2d");
        var ctx4 = document.getElementById("chart-area4").getContext("2d");

        var colors = [
            "#e53935", "#43A047", "#1E88E5", "#FDD835", "#F4511E", "#6D4C41", "#3949AB", "#D81B60", "#8E24AA", "#00ACC1", "#7CB342", "#FFB300", "#546E7A", "#1E88E5", "#C0CA33", "#757575", "#ff1744", "#2979FF", "#FFC400", "#FF3D00"
        ];

        var highlightColors = [
            "#ef9a9a", "#81C784", "#64B5F6", "#FFF176", "#FF8A65", "#A1887F", "#7986CB", "#F06292", "#BA68C8", "#4DD0E1", "#AED581", "#FFD54F", "#90A4AE", "#64B5F6", "#DCE775", "#BDBDBD", "#ff8a80", "#82B1FF", "#FFE57F", "#FF9E80"
        ];

        """),_display_(/*88.10*/if(!numbers.isEmpty && !classes.isEmpty)/*88.50*/ {_display_(Seq[Any](format.raw/*88.52*/("""
            """),_display_(/*89.14*/for(index <- 0 until numbers.size) yield /*89.48*/ {_display_(Seq[Any](format.raw/*89.50*/("""
                """),format.raw/*90.17*/("""data.push("""),format.raw/*90.27*/("""{"""),format.raw/*90.28*/(""" """),format.raw/*90.29*/("""value: """),_display_(/*90.37*/numbers(index)),format.raw/*90.51*/(""", color: colors["""),_display_(/*90.68*/index),format.raw/*90.73*/("""], highlight: highlightColors["""),_display_(/*90.104*/index),format.raw/*90.109*/("""], label: '"""),_display_(/*90.121*/classes(index)),format.raw/*90.135*/("""' """),format.raw/*90.137*/("""}"""),format.raw/*90.138*/(""");

                dataArray.push("""),_display_(/*92.33*/numbers(index)),format.raw/*92.47*/(""");
                labelsArray.push(""""),_display_(/*93.36*/classes(index)),format.raw/*93.50*/("""");
            """)))}),format.raw/*94.14*/("""
        """)))}),format.raw/*95.10*/("""

        """),format.raw/*97.9*/("""var options = """),format.raw/*97.23*/("""{"""),format.raw/*97.24*/("""
            """),format.raw/*98.13*/("""legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++)"""),format.raw/*98.116*/("""{"""),format.raw/*98.117*/("""%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label)"""),format.raw/*98.213*/("""{"""),format.raw/*98.214*/("""%><%=segments[i].label%> (<%=segments[i].value%>)<%"""),format.raw/*98.265*/("""}"""),format.raw/*98.266*/("""%></li><%"""),format.raw/*98.275*/("""}"""),format.raw/*98.276*/("""%></ul>"
        """),format.raw/*99.9*/("""}"""),format.raw/*99.10*/("""

        """),format.raw/*101.9*/("""var helpers = Chart.helpers;

        window.chart = new Chart(ctx).Pie(data, options);

        if(urlClass != "") """),format.raw/*105.28*/("""{"""),format.raw/*105.29*/("""
            """),format.raw/*106.13*/("""var legendHolder = document.createElement('div');
            legendHolder.innerHTML = window.chart.generateLegend();

            // Include a html legend template after the module doughnut itself
            helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*110.91*/("""{"""),format.raw/*110.92*/("""
                """),format.raw/*111.17*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*111.71*/("""{"""),format.raw/*111.72*/("""
                    """),format.raw/*112.21*/("""var activeSegment = window.chart.segments[index];
                    activeSegment.save();
                    window.chart.showTooltip([activeSegment]);
                    activeSegment.restore();
                """),format.raw/*116.17*/("""}"""),format.raw/*116.18*/(""");
            """),format.raw/*117.13*/("""}"""),format.raw/*117.14*/(""");
            helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*118.79*/("""{"""),format.raw/*118.80*/("""
                """),format.raw/*119.17*/("""window.chart.draw();
            """),format.raw/*120.13*/("""}"""),format.raw/*120.14*/(""");

            window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);
        """),format.raw/*123.9*/("""}"""),format.raw/*123.10*/("""

        """),format.raw/*125.9*/("""var data2 = """),format.raw/*125.21*/("""{"""),format.raw/*125.22*/("""
            """),format.raw/*126.13*/("""labels: labelsArray,
            datasets: [
                """),format.raw/*128.17*/("""{"""),format.raw/*128.18*/("""
                    """),format.raw/*129.21*/("""label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: dataArray
                """),format.raw/*135.17*/("""}"""),format.raw/*135.18*/("""
            """),format.raw/*136.13*/("""]
        """),format.raw/*137.9*/("""}"""),format.raw/*137.10*/(""";

        $('#chartType a').click(function (e) """),format.raw/*139.46*/("""{"""),format.raw/*139.47*/("""
            """),format.raw/*140.13*/("""e.preventDefault();
            var tabId = $(this).attr('id');
            $(this).tab('show');

            if (tabId == "bar-tab") """),format.raw/*144.37*/("""{"""),format.raw/*144.38*/("""
                """),format.raw/*145.17*/("""window.chart = new Chart(ctx3).PolarArea(data, options);
                if($("#polar").find(".polararea-legend")) """),format.raw/*146.59*/("""{"""),format.raw/*146.60*/("""
                    """),format.raw/*147.21*/("""// console.log($("#polar").find(".polararea-legend"));
                    return false;
                """),format.raw/*149.17*/("""}"""),format.raw/*149.18*/("""
            """),format.raw/*150.13*/("""}"""),format.raw/*150.14*/("""
            """),format.raw/*151.13*/("""else if (tabId == "polar-tab") """),format.raw/*151.44*/("""{"""),format.raw/*151.45*/("""
                """),format.raw/*152.17*/("""window.chart = new Chart(ctx2).Bar(data2);
                return false;
            """),format.raw/*154.13*/("""}"""),format.raw/*154.14*/("""
            """),format.raw/*155.13*/("""else if (tabId == "radar-tab") """),format.raw/*155.44*/("""{"""),format.raw/*155.45*/("""
                """),format.raw/*156.17*/("""window.chart = new Chart(ctx4).Radar(data2);
                return false;
            """),format.raw/*158.13*/("""}"""),format.raw/*158.14*/("""
            """),format.raw/*159.13*/("""else if (tabId == "pie-tab") """),format.raw/*159.42*/("""{"""),format.raw/*159.43*/("""
                """),format.raw/*160.17*/("""window.chart = new Chart(ctx).Pie(data);
                if($("#pie").find(".pie-legend")) """),format.raw/*161.51*/("""{"""),format.raw/*161.52*/("""
                    """),format.raw/*162.21*/("""return false;
                """),format.raw/*163.17*/("""}"""),format.raw/*163.18*/("""
            """),format.raw/*164.13*/("""}"""),format.raw/*164.14*/("""

            """),format.raw/*166.13*/("""var legendHolder = document.createElement('div');
            legendHolder.innerHTML = window.chart.generateLegend();

            // Include a html legend template after the module doughnut itself
            helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*170.91*/("""{"""),format.raw/*170.92*/("""
                """),format.raw/*171.17*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*171.71*/("""{"""),format.raw/*171.72*/("""
                    """),format.raw/*172.21*/("""var activeSegment = window.chart.segments[index];
                    activeSegment.save();
                    window.chart.showTooltip([activeSegment]);
                    activeSegment.restore();
                """),format.raw/*176.17*/("""}"""),format.raw/*176.18*/(""");
            """),format.raw/*177.13*/("""}"""),format.raw/*177.14*/(""");
            helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*178.79*/("""{"""),format.raw/*178.80*/("""
                """),format.raw/*179.17*/("""window.chart.draw();
            """),format.raw/*180.13*/("""}"""),format.raw/*180.14*/(""");

            window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);
        """),format.raw/*183.9*/("""}"""),format.raw/*183.10*/(""");

    """),format.raw/*185.5*/("""}"""),format.raw/*185.6*/(""");
</script>

""")))}))}
  }

  def render(numbers:List[String],classes:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(numbers,classes)

  def f:((List[String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (numbers,classes) => apply(numbers,classes)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue May 19 20:11:28 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/statistics.scala.html
                  HASH: 7e6c20c7e176ed8aa1c7ee0de400e17908d4d720
                  MATRIX: 747->1|929->47|957->100|984->102|1101->210|1141->212|1169->214|3601->2618|3630->2619|3667->2629|3762->2696|3791->2697|3832->2710|3902->2753|3931->2754|3960->2755|3993->2760|4022->2761|4063->2774|4137->2821|4166->2822|4203->2832|4276->2877|4305->2878|4346->2891|4408->2925|4437->2926|4482->2943|4626->3059|4655->3060|4691->3069|4720->3070|5727->4050|5776->4090|5816->4092|5857->4106|5907->4140|5947->4142|5992->4159|6030->4169|6059->4170|6088->4171|6123->4179|6158->4193|6202->4210|6228->4215|6287->4246|6314->4251|6354->4263|6390->4277|6421->4279|6451->4280|6514->4316|6549->4330|6614->4368|6649->4382|6697->4399|6738->4409|6775->4419|6817->4433|6846->4434|6887->4447|7019->4550|7049->4551|7174->4647|7204->4648|7284->4699|7314->4700|7352->4709|7382->4710|7426->4727|7455->4728|7493->4738|7638->4854|7668->4855|7710->4868|8027->5156|8057->5157|8103->5174|8186->5228|8216->5229|8266->5250|8511->5466|8541->5467|8585->5482|8615->5483|8725->5564|8755->5565|8801->5582|8863->5615|8893->5616|9031->5726|9061->5727|9099->5737|9140->5749|9170->5750|9212->5763|9302->5824|9332->5825|9382->5846|9726->6161|9756->6162|9798->6175|9836->6185|9866->6186|9943->6234|9973->6235|10015->6248|10178->6382|10208->6383|10254->6400|10398->6515|10428->6516|10478->6537|10612->6642|10642->6643|10684->6656|10714->6657|10756->6670|10816->6701|10846->6702|10892->6719|11006->6804|11036->6805|11078->6818|11138->6849|11168->6850|11214->6867|11330->6954|11360->6955|11402->6968|11460->6997|11490->6998|11536->7015|11656->7106|11686->7107|11736->7128|11795->7158|11825->7159|11867->7172|11897->7173|11940->7187|12257->7475|12287->7476|12333->7493|12416->7547|12446->7548|12496->7569|12741->7785|12771->7786|12815->7801|12845->7802|12955->7883|12985->7884|13031->7901|13093->7934|13123->7935|13261->8045|13291->8046|13327->8054|13356->8055
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|83->56|83->56|85->58|86->59|86->59|87->60|88->61|88->61|88->61|88->61|88->61|89->62|90->63|90->63|92->65|92->65|92->65|93->66|93->66|93->66|94->67|95->68|95->68|96->69|96->69|115->88|115->88|115->88|116->89|116->89|116->89|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|117->90|119->92|119->92|120->93|120->93|121->94|122->95|124->97|124->97|124->97|125->98|125->98|125->98|125->98|125->98|125->98|125->98|125->98|125->98|126->99|126->99|128->101|132->105|132->105|133->106|137->110|137->110|138->111|138->111|138->111|139->112|143->116|143->116|144->117|144->117|145->118|145->118|146->119|147->120|147->120|150->123|150->123|152->125|152->125|152->125|153->126|155->128|155->128|156->129|162->135|162->135|163->136|164->137|164->137|166->139|166->139|167->140|171->144|171->144|172->145|173->146|173->146|174->147|176->149|176->149|177->150|177->150|178->151|178->151|178->151|179->152|181->154|181->154|182->155|182->155|182->155|183->156|185->158|185->158|186->159|186->159|186->159|187->160|188->161|188->161|189->162|190->163|190->163|191->164|191->164|193->166|197->170|197->170|198->171|198->171|198->171|199->172|203->176|203->176|204->177|204->177|205->178|205->178|206->179|207->180|207->180|210->183|210->183|212->185|212->185
                  -- GENERATED --
              */
          