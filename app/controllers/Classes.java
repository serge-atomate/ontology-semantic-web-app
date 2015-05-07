package controllers;

import owlAPI.Ontology;
import play.*;
import play.mvc.*;

import java.util.*;

import org.json.*;

import views.html.*;

/**
 * Created by serge on 28/12/2014.
 */

public class Classes extends Controller {

    public static Result index(String cls) throws Exception  {
        System.out.println("Cls: " + cls);
        if(!cls.equals("owl:Nothing")) {
//            List<String> results = new ArrayList<String>();
            JSONObject results = new JSONObject();
//        String results = "";
            results = Ontology.rootClasses(cls);

            String jsonObj = "{\"classes\" : ";
            jsonObj += results.toString();
            jsonObj += "}";
//        jsonObj += ", \"individuals\": " + (listInd.toString()) + " } ";

            return ok(jsonObj);
        } else {
            return ok("{\"classes\" : []}");
        }
//        return ok(views.html.classes.render(results));
    }

    public static Result classes() throws Exception {

        JSONObject results = new JSONObject();
        results = Ontology.rootClasses("");

        String html = "";
        html += buildHierarchically(results, 0);

        return ok(views.html.classes.render(html));
    }

    public static String buildHierarchically(JSONObject json, int level) throws JSONException{
        Iterator<String> keys = json.keys();
        level += 1;
        String html = "<ul class='level"+level+"' style=\"display:none;\">";
        while(keys.hasNext()){
            html += "<li class=\"dropdown-toggle lst\">";
            String key = keys.next();
            String val = null;
            html += "<a class='cls' href='"+key+"'>"+key+"</a>";
            try{
                JSONObject value = json.getJSONObject(key);
                val = value.toString();
            }catch(Exception e){
                val = json.getString(key);
            }

            if(val != null && !val.equals("\"{}\"")){
                JSONObject jsonObjVal = new JSONObject(val.replace("\"{\"", "{\"").replace("\"}\"", "\"}").replace("{\"{", "{{").replace("}}\"}}", "}}}}").replace("\"{\"", "{\"").replace("\"}\"", "\"}"));
                html += buildHierarchically(jsonObjVal, level);
            }
            html += "</li>";
        }
        html += "</ul>";
        return html;
    }

    public static Result getClasses() throws Exception {

        List<String> results = new ArrayList<String>();
        results = Ontology.getAllClasses();

        return ok(results.toString());
    }


    // public static Result createNewClass(String cls) throws Exception {
    //     System.out.println("Cls: " + cls);
    //     Ontology.createNewClass(cls);

    //     return ok(cls);
    // }

}
