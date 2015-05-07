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

import views.html.*;

public class Statistics extends Controller {

    public static Result index(String cls) throws Exception {

        List<String> results = new ArrayList<String>();
        results = Ontology.queryIndividsPerClass(cls);

        ArrayList<String> numbers = new ArrayList<String>();
        ArrayList<String> classes = new ArrayList<String>();

        int i = 0;
        for(int k = 0; k < results.size() ; k++) {
//            System.out.println(results.get(k));
            List<String> dataItems = new ArrayList<String>(Arrays.asList(results.get(k).split("=")));
            for (String dataItem : dataItems) {
                if(i%2==0) {
                    numbers.add(dataItem);
                } else {
                    classes.add(dataItem);
                }
                i++;
            }
        }

        return ok(views.html.statistics.render(numbers, classes));
    }

    public static Result queryPubByYear() throws Exception {

        HashMap<String, Integer> results = new HashMap<String, Integer>();
        results = Ontology.queryPublicationsbyYear();
        // sort results by year asceding
        Map<String, Integer> sortedResults = new TreeMap<String, Integer>(results);
        System.out.println(sortedResults);

        return ok(views.html.publications.render(sortedResults));
    }

}
