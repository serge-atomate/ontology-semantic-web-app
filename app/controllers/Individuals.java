package controllers;

import owlAPI.Ontology;
import play.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.text.WordUtils;

//import org.json.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import views.html.*;

/**
 * Created by serge on 07/01/2015.
 */

public class Individuals extends Controller {


    public static Result index(String individual) throws Exception  {

        if(!individual.equals("")) {
            ArrayList<String> results = new ArrayList<String>();

            results = Ontology.getIndividual(individual);

            List<String> dataItems = new ArrayList<String>(Arrays.asList(results.get(0).replace("{ \"data\": [ {", "").replace("} ]", "").replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", "").split("¡")));
            List<String> objectItems = new ArrayList<String>(Arrays.asList(results.get(1).replace("\"objects\": [ {", "").replace("} ] }", "").replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", "").split("¡")));

            List<String> datas = new ArrayList<String>();
            List<String> objects = new ArrayList<String>();

            String name = "";

            for (String dataItem : dataItems) {

                String[] dataParts = dataItem.split(":", 2);

                if(dataParts.length!=1 && !dataParts[1].equals("\"\"") && !dataParts[1].equals("\"  \"")) {

                    // add name as separate var
                    if(dataParts[0].replace("\"", "").equals("name") || dataParts[0].replace("\"", "").equals("title")) {
                        name = dataParts[1].replace("\"", "");
                        continue;
                    }

                    String outputHtml = "";

                    if(dataParts[1].contains("OWL/id")) {
                        // internal links
                        outputHtml += "<a class=\"cls\" href=\"/individ?q=" + dataParts[1].replace("\"", "") + "\">" + dataParts[1].replace("\"", "") + "</a>";
                    } else if(dataParts[1].contains("http://")) {
                        // external links
                        outputHtml += "<a class=\"cls\" target=\"_blank\" href=\"" + dataParts[1].replace("\"", "") + "\">" + dataParts[1].replace("\"", "") + "</a>";
                    } else {
                        // just text
                        outputHtml += dataParts[1].replace("\"", "");
                    }
                    outputHtml += "";

                    String label = WordUtils.capitalize(dataParts[0].replace("\"", ""));

                    datas.add("<li class=\"lst\"><span class='" + label + "'>" + label + ":</span>"+outputHtml+"</li>");
                }
            }

            for (String objectItem : objectItems) {
                String[] objParts = objectItem.split(":", 2);

                String outputHtml = "";

                if (objParts.length > 1 && !objParts[1].equals("\"\"")) {

                    String[] options = objParts[1].replace("\"", "").split("¿");

                    int p = 0;
                    outputHtml = "<ul class='items'><div class='page0'>";
                    for (String opt : options) {
                        p++;
                        // split again to take name and id separately
                        String[] parts = opt.split("=");
                        if(parts.length > 1) {
                            outputHtml += "<li class=\"lst\"><a class=\"cls\" href=\"/individ?q=" + parts[1] + "\">" +parts[0] + "</a></li>";
                        } else {
                            outputHtml += "<li class=\"lst\"><a class=\"cls\" href=\"#\">" + parts[0] + "</a></li>";
                        }
                        // split results in pages by 20
                        if(p%20==0) {
                            outputHtml += "</div><div style='display:none;' class='page"+(p/20)+"'>";
                        }
                    }
                    outputHtml += "</div></ul>";

                    String label = WordUtils.capitalize(objParts[0].replace("\"", ""));

                    // add pagination html if more than 20 results
                    if(p>20) {
                        String pagination = "<nav class='"+label+"'><ul class=\"pagination\">";

                        for (int i = 0; i <= (p/20); i++) {
                            pagination += "<li";
                            if(i==0) {
                                pagination += " class=\"active\"";
                            }
                            pagination += "><a href=\"page"+i+"\">"+(i+1)+"</a></li>";
                        }
                        pagination += "  </ul></nav>";
                        outputHtml += pagination;
                    }

                    // totalRecords = "<h5>Total records found: "+p+"</h5>";

                    objects.add("<li id='"+label+"' class=\"lst "+label+"\"><span class='"+label+"'>" + 
                                label + ":</span><h6>Total records found: "+p+"</h6>"+outputHtml+"</li>");

                } else {
                    // do nothing
                }
            }

            Map<String, Integer> chartData = new TreeMap<String, Integer>();
            // get Charts Data
            HashMap<String, Integer> chart = new HashMap<String, Integer>();
            chart = Ontology.queryPublicationsbyYearIndivid(individual);
            chartData = new TreeMap<String, Integer>(chart);

            return ok(views.html.individ.render(datas, objects, name, chartData));

        } else {
            return ok("{[]}");
        }

    }

    public static Result statisticsIndivid(String individual) throws Exception  {

        if(!individual.equals("")) {
            HashMap<String, Integer> results = new HashMap<String, Integer>();

            results = Ontology.queryPublicationsbyYearIndivid(individual);

            Map<String, Integer> sortedResults = new TreeMap<String, Integer>(results);

            return ok(sortedResults.toString());

        } else {
            return ok("{}");
        }

    }


    public static Result individuals(String cls) throws Exception  {
        // System.out.println("Cls: " + cls);

        if(!cls.equals("owl:Nothing")) {
            List<String> results = new ArrayList<String>();
            
            results = Ontology.classesIndividuals(cls);

            String jsonObj = "{\"individuals\" : ";
            jsonObj += results.toString();
            jsonObj += "}";

            return ok(jsonObj);
        } else {
            return ok("{\"classes\" : []}");
        }

    }
}