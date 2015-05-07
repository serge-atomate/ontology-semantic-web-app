package controllers;

import owlAPI.Ontology;
import play.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import views.html.*;

public class Application extends Controller {

    public static Result index() throws Exception {

       	// String comment = Ontology.getOntologyComment();

        return ok(views.html.homepage.render("Instance data for institute aifb of the university of karslruhe"));
    }

}
