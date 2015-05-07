// @SOURCE:/Users/serge/Htdocs/Project-OWL/conf/routes
// @HASH:7a07c87fba173777d0c797fddfd094c5b3eb70ac
// @DATE:Mon Apr 13 14:26:16 CEST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers {

// @LINE:23
// @LINE:22
class ReverseStatistics {


// @LINE:23
def queryPubByYear(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "statistics-publications")
}
                        

// @LINE:22
def index(q:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "statistics" + queryString(List(if(q == "") None else Some(implicitly[QueryStringBindable[String]].unbind("q", q)))))
}
                        

}
                          

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:14
// @LINE:13
// @LINE:12
class ReverseClasses {


// @LINE:12
def classes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "classes")
}
                        

// @LINE:14
def getClasses(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "allclasses")
}
                        

// @LINE:13
def index(q:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "classes/cls" + queryString(List(if(q == "") None else Some(implicitly[QueryStringBindable[String]].unbind("q", q)))))
}
                        

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseIndividuals {


// @LINE:19
def statisticsIndivid(q:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "stat" + queryString(List(if(q == "") None else Some(implicitly[QueryStringBindable[String]].unbind("q", q)))))
}
                        

// @LINE:17
def index(q:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "individ" + queryString(List(if(q == "") None else Some(implicitly[QueryStringBindable[String]].unbind("q", q)))))
}
                        

// @LINE:18
def individuals(q:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "individuals" + queryString(List(if(q == "") None else Some(implicitly[QueryStringBindable[String]].unbind("q", q)))))
}
                        

}
                          
}
                  


// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:23
// @LINE:22
class ReverseStatistics {


// @LINE:23
def queryPubByYear : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Statistics.queryPubByYear",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "statistics-publications"})
      }
   """
)
                        

// @LINE:22
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Statistics.index",
   """
      function(q) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "statistics" + _qS([(q == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("q", q))])})
      }
   """
)
                        

}
              

// @LINE:9
class ReverseAssets {


// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:14
// @LINE:13
// @LINE:12
class ReverseClasses {


// @LINE:12
def classes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Classes.classes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "classes"})
      }
   """
)
                        

// @LINE:14
def getClasses : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Classes.getClasses",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allclasses"})
      }
   """
)
                        

// @LINE:13
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Classes.index",
   """
      function(q) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "classes/cls" + _qS([(q == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("q", q))])})
      }
   """
)
                        

}
              

// @LINE:6
class ReverseApplication {


// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseIndividuals {


// @LINE:19
def statisticsIndivid : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Individuals.statisticsIndivid",
   """
      function(q) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "stat" + _qS([(q == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("q", q))])})
      }
   """
)
                        

// @LINE:17
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Individuals.index",
   """
      function(q) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "individ" + _qS([(q == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("q", q))])})
      }
   """
)
                        

// @LINE:18
def individuals : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Individuals.individuals",
   """
      function(q) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "individuals" + _qS([(q == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("q", q))])})
      }
   """
)
                        

}
              
}
        


// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:23
// @LINE:22
class ReverseStatistics {


// @LINE:23
def queryPubByYear(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Statistics.queryPubByYear(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Statistics", "queryPubByYear", Seq(), "GET", """""", _prefix + """statistics-publications""")
)
                      

// @LINE:22
def index(q:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Statistics.index(q), HandlerDef(this.getClass.getClassLoader, "", "controllers.Statistics", "index", Seq(classOf[String]), "GET", """Statistics""", _prefix + """statistics""")
)
                      

}
                          

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:14
// @LINE:13
// @LINE:12
class ReverseClasses {


// @LINE:12
def classes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Classes.classes(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "classes", Seq(), "GET", """Classes""", _prefix + """classes""")
)
                      

// @LINE:14
def getClasses(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Classes.getClasses(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "getClasses", Seq(), "GET", """""", _prefix + """allclasses""")
)
                      

// @LINE:13
def index(q:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Classes.index(q), HandlerDef(this.getClass.getClassLoader, "", "controllers.Classes", "index", Seq(classOf[String]), "GET", """""", _prefix + """classes/cls""")
)
                      

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseIndividuals {


// @LINE:19
def statisticsIndivid(q:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Individuals.statisticsIndivid(q), HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "statisticsIndivid", Seq(classOf[String]), "GET", """""", _prefix + """stat""")
)
                      

// @LINE:17
def index(q:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Individuals.index(q), HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "index", Seq(classOf[String]), "GET", """Individuals""", _prefix + """individ""")
)
                      

// @LINE:18
def individuals(q:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Individuals.individuals(q), HandlerDef(this.getClass.getClassLoader, "", "controllers.Individuals", "individuals", Seq(classOf[String]), "GET", """""", _prefix + """individuals""")
)
                      

}
                          
}
        
    