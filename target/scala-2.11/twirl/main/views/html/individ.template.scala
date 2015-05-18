
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
object individ extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[List[String],List[String],String,Map[String, Integer],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(dataItm: List[String], objItm: List[String], name: String, chartData: Map[String, Integer]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.94*/("""

"""),format.raw/*5.1*/("""
"""),_display_(/*6.2*/main(name+" | Institute of AIFB of the Karlsruhe Univesity | Ontology-Driven Semantic Web Application")/*6.105*/ {_display_(Seq[Any](format.raw/*6.107*/("""

"""),format.raw/*8.1*/("""<div class="individ">
    """),_display_(/*9.6*/if(!name.isEmpty)/*9.23*/ {_display_(Seq[Any](format.raw/*9.25*/("""
        """),format.raw/*10.9*/("""<h3>"""),_display_(/*10.14*/name),format.raw/*10.18*/("""</h3>
    """)))}),format.raw/*11.6*/("""

    """),_display_(/*13.6*/if(!dataItm.isEmpty)/*13.26*/ {_display_(Seq[Any](format.raw/*13.28*/("""
        """),format.raw/*14.9*/("""<div class="row dataProperties">
            <div class="col-md-12">
                <ul class="list-unstyled">
                        <!--Data Properties-->
                        """),_display_(/*18.26*/for(value <- dataItm) yield /*18.47*/ {_display_(Seq[Any](format.raw/*18.49*/("""
                            """),_display_(/*19.30*/Html(value)),format.raw/*19.41*/("""
                            """),format.raw/*20.29*/("""<!--<li class="lst"><a class="cls" href='/individ?q="""),_display_(/*20.82*/value),format.raw/*20.87*/("""'>"""),_display_(/*20.90*/value),format.raw/*20.95*/("""</a></li>-->
                        """)))}),format.raw/*21.26*/("""
                """),format.raw/*22.17*/("""</ul>
            </div>
        </div>
    """)))}),format.raw/*25.6*/("""

    """),_display_(/*27.6*/if(!objItm.isEmpty)/*27.25*/ {_display_(Seq[Any](format.raw/*27.27*/("""
        """),format.raw/*28.9*/("""<div class="row objectsProperties">
            <div class="col-md-12">
                <ul class="list-unstyled">
                        <!--Object Properties-->
                        """),_display_(/*32.26*/for(value <- objItm) yield /*32.46*/ {_display_(Seq[Any](format.raw/*32.48*/("""
                            """),_display_(/*33.30*/Html(value)),format.raw/*33.41*/("""
                        """)))}),format.raw/*34.26*/("""
                """),format.raw/*35.17*/("""</ul>
            </div>
        </div>
    """)))}),format.raw/*38.6*/("""

    """),_display_(/*40.6*/if(!chartData.isEmpty)/*40.28*/ {_display_(Seq[Any](format.raw/*40.30*/("""
        """),format.raw/*41.9*/("""<div class="row">
            <div class="col-md-12">

                <h4>Charts: "Number of Publications by year"</h4>

                <ul id="chartType" class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#pie" id="pie-tab" role="tab" data-toggle="tab" aria-controls="pie" aria-expanded="true">Pie Chart</a></li>
                    <li role="presentation"><a href="#bar" role="tab" id="bar-tab" data-toggle="tab" aria-controls="bar">Bar Chart</a></li>
                    <li role="presentation"><a href="#polar" role="tab" id="polar-tab" data-toggle="tab" aria-controls="polar">Polar Area Chart</a></li>
                    <li role="presentation"><a href="#radar" role="tab" id="radar-tab" data-toggle="tab" aria-controls="radar">Radar Chart</a></li>
                </ul>

                <div id="myTabContent" class="tab-content">
                    <div role="tabpanel" class="tab-pane in active" id="pie" aria-labelledby="pie-tab">
                        <div id="canvas-holder"><canvas id="chart-area" width="300" height="300"/></div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="bar" aria-labelledby="bar-tab">
                        <div id="canvas-holder"><canvas id="chart-area2" width="400" height="300"/></div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="polar" aria-labelledby="polar-tab">
                        <div id="canvas-holder"><canvas id="chart-area3" width="300" height="300"/></div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="radar" aria-labelledby="radar-tab">
                        <div id="canvas-holder"><canvas id="chart-area4" width="300" height="300"/></div>
                    </div>
                </div>

            </div>
        </div>
    """)))}),format.raw/*70.6*/("""

"""),format.raw/*72.1*/("""</div>


<script language="JavaScript">
    $(document).ready(function() """),format.raw/*76.34*/("""{"""),format.raw/*76.35*/("""
        """),format.raw/*77.9*/("""$(".pagination a").click(function(e) """),format.raw/*77.46*/("""{"""),format.raw/*77.47*/("""
            """),format.raw/*78.13*/("""e.preventDefault();

            $(this).parent( "li" ).parent( "ul" ).find('li').removeClass("active");
            $(this).parent( "li" ).addClass('active');

            var href = $(this).attr('href');
            $(this).parent( "li" ).parent( "ul" ).parent("nav").parent("li.lst").find("div").hide();
            $(this).parent( "li" ).parent( "ul" ).parent("nav").parent("li.lst").find("div."+href).show();

            var classAccess = $(this).parent( "li" ).parent( "ul" ).parent("nav").attr("class");
            var url = document.URL;

            if(url.indexOf('#') != -1) """),format.raw/*90.40*/("""{"""),format.raw/*90.41*/("""
                """),format.raw/*91.17*/("""window.location.href = url.replace(url.substr(url.lastIndexOf('#') + 1), ""+classAccess);
            """),format.raw/*92.13*/("""}"""),format.raw/*92.14*/(""" """),format.raw/*92.15*/("""else """),format.raw/*92.20*/("""{"""),format.raw/*92.21*/("""
                """),format.raw/*93.17*/("""window.location.href = url+"#"+classAccess;
            """),format.raw/*94.13*/("""}"""),format.raw/*94.14*/("""
        """),format.raw/*95.9*/("""}"""),format.raw/*95.10*/(""");




        // Initiate Charts
        var data = [];
        var labelsArray = [];
        var dataArray = [];

        var colors = [
            "#e53935", "#43A047", "#1E88E5", "#FDD835", "#F4511E", "#6D4C41", "#3949AB", "#D81B60", "#8E24AA", "#00ACC1", "#7CB342", "#FFB300", "#546E7A", "#1E88E5", "#C0CA33", "#757575", "#ff1744", "#2979FF", "#FFC400", "#FF3D00"
        ];

        var highlightColors = [
            "#ef9a9a", "#81C784", "#64B5F6", "#FFF176", "#FF8A65", "#A1887F", "#7986CB", "#F06292", "#BA68C8", "#4DD0E1", "#AED581", "#FFD54F", "#90A4AE", "#64B5F6", "#DCE775", "#BDBDBD", "#ff8a80", "#82B1FF", "#FFE57F", "#FF9E80"
        ];

        var index = 1;
        """),_display_(/*114.10*/if(!chartData.isEmpty)/*114.32*/ {_display_(Seq[Any](format.raw/*114.34*/("""
            """),_display_(/*115.14*/for((key,value) <- chartData) yield /*115.43*/ {_display_(Seq[Any](format.raw/*115.45*/("""
                """),format.raw/*116.17*/("""index = Math.floor(Math.random() * 20) + 1;
                data.push("""),format.raw/*117.27*/("""{"""),format.raw/*117.28*/(""" """),format.raw/*117.29*/("""value: """),_display_(/*117.37*/value),format.raw/*117.42*/(""", color: colors[index], highlight: highlightColors[index], label: '"""),_display_(/*117.110*/key),format.raw/*117.113*/("""' """),format.raw/*117.115*/("""}"""),format.raw/*117.116*/(""");
                //console.log(index);
                dataArray.push("""),_display_(/*119.33*/value),format.raw/*119.38*/(""");
                labelsArray.push(""""),_display_(/*120.36*/key),format.raw/*120.39*/("""");
            """)))}),format.raw/*121.14*/("""
        """)))}),format.raw/*122.10*/("""

        """),format.raw/*124.9*/("""var ctx = document.getElementById("chart-area").getContext("2d");
        var ctx2 = document.getElementById("chart-area2").getContext("2d");
        var ctx3 = document.getElementById("chart-area3").getContext("2d");
        var ctx4 = document.getElementById("chart-area4").getContext("2d");

        var options = """),format.raw/*129.23*/("""{"""),format.raw/*129.24*/("""
            """),format.raw/*130.13*/("""legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++)"""),format.raw/*130.116*/("""{"""),format.raw/*130.117*/("""%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label)"""),format.raw/*130.213*/("""{"""),format.raw/*130.214*/("""%><%=segments[i].label%> (<%=segments[i].value%>)<%"""),format.raw/*130.265*/("""}"""),format.raw/*130.266*/("""%></li><%"""),format.raw/*130.275*/("""}"""),format.raw/*130.276*/("""%></ul>"
        """),format.raw/*131.9*/("""}"""),format.raw/*131.10*/("""

        """),format.raw/*133.9*/("""var helpers = Chart.helpers;
        window.chart = new Chart(ctx).Pie(data, options);

        var legendHolder = document.createElement('div');
        legendHolder.innerHTML = window.chart.generateLegend();

        // Include a html legend template after the module doughnut itself
        helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*140.87*/("""{"""),format.raw/*140.88*/("""
            """),format.raw/*141.13*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*141.67*/("""{"""),format.raw/*141.68*/("""
                """),format.raw/*142.17*/("""var activeSegment = window.chart.segments[index];
                activeSegment.save();
                window.chart.showTooltip([activeSegment]);
                activeSegment.restore();
            """),format.raw/*146.13*/("""}"""),format.raw/*146.14*/(""");
        """),format.raw/*147.9*/("""}"""),format.raw/*147.10*/(""");
        helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*148.75*/("""{"""),format.raw/*148.76*/("""
            """),format.raw/*149.13*/("""window.chart.draw();
        """),format.raw/*150.9*/("""}"""),format.raw/*150.10*/(""");

        window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);

        var data2 = """),format.raw/*154.21*/("""{"""),format.raw/*154.22*/("""
            """),format.raw/*155.13*/("""labels: labelsArray,
            datasets: [
               /* """),format.raw/*157.19*/("""{"""),format.raw/*157.20*/("""
                    """),format.raw/*158.21*/("""label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                """),format.raw/*164.17*/("""}"""),format.raw/*164.18*/(""", */
                """),format.raw/*165.17*/("""{"""),format.raw/*165.18*/("""
                    """),format.raw/*166.21*/("""label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: dataArray
                """),format.raw/*172.17*/("""}"""),format.raw/*172.18*/("""
            """),format.raw/*173.13*/("""]
        """),format.raw/*174.9*/("""}"""),format.raw/*174.10*/(""";

        $('#chartType a').click(function (e) """),format.raw/*176.46*/("""{"""),format.raw/*176.47*/("""
            """),format.raw/*177.13*/("""e.preventDefault();
            var tabId = $(this).attr('id');
            $(this).tab('show');

            if (tabId == "bar-tab") """),format.raw/*181.37*/("""{"""),format.raw/*181.38*/("""
                """),format.raw/*182.17*/("""window.chart = new Chart(ctx2).Bar(data2, options);
                return false;
            """),format.raw/*184.13*/("""}"""),format.raw/*184.14*/("""
            """),format.raw/*185.13*/("""else if (tabId == "polar-tab") """),format.raw/*185.44*/("""{"""),format.raw/*185.45*/("""
                """),format.raw/*186.17*/("""window.chart = new Chart(ctx3).PolarArea(data, options);
                if($("#polar").find(".polararea-legend")) """),format.raw/*187.59*/("""{"""),format.raw/*187.60*/("""
                    """),format.raw/*188.21*/("""console.log($("#polar").find(".polararea-legend"));
                    return false;
                """),format.raw/*190.17*/("""}"""),format.raw/*190.18*/("""
            """),format.raw/*191.13*/("""}"""),format.raw/*191.14*/("""
            """),format.raw/*192.13*/("""else if (tabId == "radar-tab") """),format.raw/*192.44*/("""{"""),format.raw/*192.45*/("""
                """),format.raw/*193.17*/("""window.chart = new Chart(ctx4).Radar(data2, options);
                return false;
            """),format.raw/*195.13*/("""}"""),format.raw/*195.14*/("""
            """),format.raw/*196.13*/("""else if (tabId == "pie-tab") """),format.raw/*196.42*/("""{"""),format.raw/*196.43*/("""
                """),format.raw/*197.17*/("""window.chart = new Chart(ctx).Pie(data, options);
                if($("#pie.tab-pane").find(".pie-legend")) """),format.raw/*198.60*/("""{"""),format.raw/*198.61*/("""
                    """),format.raw/*199.21*/("""return false;
                """),format.raw/*200.17*/("""}"""),format.raw/*200.18*/("""
            """),format.raw/*201.13*/("""}"""),format.raw/*201.14*/("""

            """),format.raw/*203.13*/("""var legendHolder = document.createElement('div');
            legendHolder.innerHTML = window.chart.generateLegend();

            // Include a html legend template after the module doughnut itself
            helpers.each(legendHolder.firstChild.childNodes, function (legendNode, index) """),format.raw/*207.91*/("""{"""),format.raw/*207.92*/("""
                """),format.raw/*208.17*/("""helpers.addEvent(legendNode, 'mouseover', function () """),format.raw/*208.71*/("""{"""),format.raw/*208.72*/("""
                    """),format.raw/*209.21*/("""var activeSegment = window.chart.segments[index];
                    activeSegment.save();
                    window.chart.showTooltip([activeSegment]);
                    activeSegment.restore();
                """),format.raw/*213.17*/("""}"""),format.raw/*213.18*/(""");
            """),format.raw/*214.13*/("""}"""),format.raw/*214.14*/(""");
            helpers.addEvent(legendHolder.firstChild, 'mouseout', function () """),format.raw/*215.79*/("""{"""),format.raw/*215.80*/("""
                """),format.raw/*216.17*/("""window.chart.draw();
            """),format.raw/*217.13*/("""}"""),format.raw/*217.14*/(""");

            window.chart.chart.canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);

        """),format.raw/*221.9*/("""}"""),format.raw/*221.10*/(""");


    """),format.raw/*224.5*/("""}"""),format.raw/*224.6*/(""");
</script>

""")))}))}
  }

  def render(dataItm:List[String],objItm:List[String],name:String,chartData:Map[String, Integer]): play.twirl.api.HtmlFormat.Appendable = apply(dataItm,objItm,name,chartData)

  def f:((List[String],List[String],String,Map[String, Integer]) => play.twirl.api.HtmlFormat.Appendable) = (dataItm,objItm,name,chartData) => apply(dataItm,objItm,name,chartData)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon May 18 16:41:50 CEST 2015
                  SOURCE: /Users/serge/Htdocs/ontology-semantic-web-app/app/views/individ.scala.html
                  HASH: 632b8d6155aec8b09a2fa0f97b940c14dff7c242
                  MATRIX: 772->1|1000->93|1028->146|1055->148|1167->251|1207->253|1235->255|1287->282|1312->299|1351->301|1387->310|1419->315|1444->319|1485->330|1518->337|1547->357|1587->359|1623->368|1834->552|1871->573|1911->575|1968->605|2000->616|2057->645|2137->698|2163->703|2193->706|2219->711|2288->749|2333->766|2408->811|2441->818|2469->837|2509->839|2545->848|2761->1037|2797->1057|2837->1059|2894->1089|2926->1100|2983->1126|3028->1143|3103->1188|3136->1195|3167->1217|3207->1219|3243->1228|5155->3110|5184->3112|5285->3185|5314->3186|5350->3195|5415->3232|5444->3233|5485->3246|6101->3834|6130->3835|6175->3852|6305->3954|6334->3955|6363->3956|6396->3961|6425->3962|6470->3979|6554->4035|6583->4036|6619->4045|6648->4046|7365->4735|7397->4757|7438->4759|7480->4773|7526->4802|7567->4804|7613->4821|7712->4891|7742->4892|7772->4893|7808->4901|7835->4906|7932->4974|7958->4977|7990->4979|8021->4980|8122->5053|8149->5058|8215->5096|8240->5099|8289->5116|8331->5126|8369->5136|8715->5453|8745->5454|8787->5467|8920->5570|8951->5571|9077->5667|9108->5668|9189->5719|9220->5720|9259->5729|9290->5730|9335->5747|9365->5748|9403->5758|9804->6130|9834->6131|9876->6144|9959->6198|9989->6199|10035->6216|10264->6416|10294->6417|10333->6428|10363->6429|10469->6506|10499->6507|10541->6520|10598->6549|10628->6550|10776->6669|10806->6670|10848->6683|10940->6746|10970->6747|11020->6768|11382->7101|11412->7102|11462->7123|11492->7124|11542->7145|11886->7460|11916->7461|11958->7474|11996->7484|12026->7485|12103->7533|12133->7534|12175->7547|12338->7681|12368->7682|12414->7699|12537->7793|12567->7794|12609->7807|12669->7838|12699->7839|12745->7856|12889->7971|12919->7972|12969->7993|13100->8095|13130->8096|13172->8109|13202->8110|13244->8123|13304->8154|13334->8155|13380->8172|13505->8268|13535->8269|13577->8282|13635->8311|13665->8312|13711->8329|13849->8438|13879->8439|13929->8460|13988->8490|14018->8491|14060->8504|14090->8505|14133->8519|14450->8807|14480->8808|14526->8825|14609->8879|14639->8880|14689->8901|14934->9117|14964->9118|15008->9133|15038->9134|15148->9215|15178->9216|15224->9233|15286->9266|15316->9267|15455->9378|15485->9379|15522->9388|15551->9389
                  LINES: 26->1|30->1|32->5|33->6|33->6|33->6|35->8|36->9|36->9|36->9|37->10|37->10|37->10|38->11|40->13|40->13|40->13|41->14|45->18|45->18|45->18|46->19|46->19|47->20|47->20|47->20|47->20|47->20|48->21|49->22|52->25|54->27|54->27|54->27|55->28|59->32|59->32|59->32|60->33|60->33|61->34|62->35|65->38|67->40|67->40|67->40|68->41|97->70|99->72|103->76|103->76|104->77|104->77|104->77|105->78|117->90|117->90|118->91|119->92|119->92|119->92|119->92|119->92|120->93|121->94|121->94|122->95|122->95|141->114|141->114|141->114|142->115|142->115|142->115|143->116|144->117|144->117|144->117|144->117|144->117|144->117|144->117|144->117|144->117|146->119|146->119|147->120|147->120|148->121|149->122|151->124|156->129|156->129|157->130|157->130|157->130|157->130|157->130|157->130|157->130|157->130|157->130|158->131|158->131|160->133|167->140|167->140|168->141|168->141|168->141|169->142|173->146|173->146|174->147|174->147|175->148|175->148|176->149|177->150|177->150|181->154|181->154|182->155|184->157|184->157|185->158|191->164|191->164|192->165|192->165|193->166|199->172|199->172|200->173|201->174|201->174|203->176|203->176|204->177|208->181|208->181|209->182|211->184|211->184|212->185|212->185|212->185|213->186|214->187|214->187|215->188|217->190|217->190|218->191|218->191|219->192|219->192|219->192|220->193|222->195|222->195|223->196|223->196|223->196|224->197|225->198|225->198|226->199|227->200|227->200|228->201|228->201|230->203|234->207|234->207|235->208|235->208|235->208|236->209|240->213|240->213|241->214|241->214|242->215|242->215|243->216|244->217|244->217|248->221|248->221|251->224|251->224
                  -- GENERATED --
              */
          