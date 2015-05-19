// @SOURCE:/Users/serge/Htdocs/ontology-semantic-web-app/conf/routes
// @HASH:a3cc8b8f9ba9404762df6cf3e02ee7fb4efe0a2e
// @DATE:Tue May 19 16:17:15 CEST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:9
private[this] lazy val controllers_Assets_at0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at0_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:12
private[this] lazy val controllers_Classes_classes1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Classes_classes1_invoker = createInvoker(
controllers.Classes.classes(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "classes", Nil,"GET", """Classes""", Routes.prefix + """"""))
        

// @LINE:13
private[this] lazy val controllers_Classes_classes2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("classes"))))
private[this] lazy val controllers_Classes_classes2_invoker = createInvoker(
controllers.Classes.classes(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "classes", Nil,"GET", """""", Routes.prefix + """classes"""))
        

// @LINE:14
private[this] lazy val controllers_Classes_index3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("classes/cls"))))
private[this] lazy val controllers_Classes_index3_invoker = createInvoker(
controllers.Classes.index(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "index", Seq(classOf[String]),"GET", """""", Routes.prefix + """classes/cls"""))
        

// @LINE:15
private[this] lazy val controllers_Classes_getClasses4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allclasses"))))
private[this] lazy val controllers_Classes_getClasses4_invoker = createInvoker(
controllers.Classes.getClasses(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "getClasses", Nil,"GET", """""", Routes.prefix + """allclasses"""))
        

// @LINE:18
private[this] lazy val controllers_Individuals_index5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("individ"))))
private[this] lazy val controllers_Individuals_index5_invoker = createInvoker(
controllers.Individuals.index(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "index", Seq(classOf[String]),"GET", """Individuals""", Routes.prefix + """individ"""))
        

// @LINE:19
private[this] lazy val controllers_Individuals_individuals6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("individuals"))))
private[this] lazy val controllers_Individuals_individuals6_invoker = createInvoker(
controllers.Individuals.individuals(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "individuals", Seq(classOf[String]),"GET", """""", Routes.prefix + """individuals"""))
        

// @LINE:20
private[this] lazy val controllers_Individuals_statisticsIndivid7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("stat"))))
private[this] lazy val controllers_Individuals_statisticsIndivid7_invoker = createInvoker(
controllers.Individuals.statisticsIndivid(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "statisticsIndivid", Seq(classOf[String]),"GET", """""", Routes.prefix + """stat"""))
        

// @LINE:23
private[this] lazy val controllers_Statistics_index8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("statistics"))))
private[this] lazy val controllers_Statistics_index8_invoker = createInvoker(
controllers.Statistics.index(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Statistics", "index", Seq(classOf[String]),"GET", """Statistics""", Routes.prefix + """statistics"""))
        

// @LINE:24
private[this] lazy val controllers_Statistics_queryPubByYear9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("statistics-publications"))))
private[this] lazy val controllers_Statistics_queryPubByYear9_invoker = createInvoker(
controllers.Statistics.queryPubByYear(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Statistics", "queryPubByYear", Nil,"GET", """""", Routes.prefix + """statistics-publications"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix,"""controllers.Classes.classes()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """classes""","""controllers.Classes.classes()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """classes/cls""","""controllers.Classes.index(q:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allclasses""","""controllers.Classes.getClasses()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """individ""","""controllers.Individuals.index(q:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """individuals""","""controllers.Individuals.individuals(q:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """stat""","""controllers.Individuals.statisticsIndivid(q:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """statistics""","""controllers.Statistics.index(q:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """statistics-publications""","""controllers.Statistics.queryPubByYear()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:9
case controllers_Assets_at0_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at0_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:12
case controllers_Classes_classes1_route(params) => {
   call { 
        controllers_Classes_classes1_invoker.call(controllers.Classes.classes())
   }
}
        

// @LINE:13
case controllers_Classes_classes2_route(params) => {
   call { 
        controllers_Classes_classes2_invoker.call(controllers.Classes.classes())
   }
}
        

// @LINE:14
case controllers_Classes_index3_route(params) => {
   call(params.fromQuery[String]("q", Some(""))) { (q) =>
        controllers_Classes_index3_invoker.call(controllers.Classes.index(q))
   }
}
        

// @LINE:15
case controllers_Classes_getClasses4_route(params) => {
   call { 
        controllers_Classes_getClasses4_invoker.call(controllers.Classes.getClasses())
   }
}
        

// @LINE:18
case controllers_Individuals_index5_route(params) => {
   call(params.fromQuery[String]("q", Some(""))) { (q) =>
        controllers_Individuals_index5_invoker.call(controllers.Individuals.index(q))
   }
}
        

// @LINE:19
case controllers_Individuals_individuals6_route(params) => {
   call(params.fromQuery[String]("q", Some(""))) { (q) =>
        controllers_Individuals_individuals6_invoker.call(controllers.Individuals.individuals(q))
   }
}
        

// @LINE:20
case controllers_Individuals_statisticsIndivid7_route(params) => {
   call(params.fromQuery[String]("q", Some(""))) { (q) =>
        controllers_Individuals_statisticsIndivid7_invoker.call(controllers.Individuals.statisticsIndivid(q))
   }
}
        

// @LINE:23
case controllers_Statistics_index8_route(params) => {
   call(params.fromQuery[String]("q", Some(""))) { (q) =>
        controllers_Statistics_index8_invoker.call(controllers.Statistics.index(q))
   }
}
        

// @LINE:24
case controllers_Statistics_queryPubByYear9_route(params) => {
   call { 
        controllers_Statistics_queryPubByYear9_invoker.call(controllers.Statistics.queryPubByYear())
   }
}
        
}

}
     