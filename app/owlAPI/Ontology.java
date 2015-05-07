package owlAPI;

import java.io.File;
import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;

import static org.junit.Assert.assertNotNull;
import static org.semanticweb.owlapi.model.parameters.Imports.INCLUDED;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
//import org.semanticweb.owlapi.model.OWLObjectAssertionAxiom;

import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitorEx;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.search.Filters;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;

import org.json.*;

import de.derivo.sparqldlapi.Query;
import de.derivo.sparqldlapi.QueryEngine;
import de.derivo.sparqldlapi.QueryResult;
import de.derivo.sparqldlapi.exceptions.QueryEngineException;
import de.derivo.sparqldlapi.exceptions.QueryParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by serge on 14/12/2014.
 */
public class Ontology {

    ///==============================================================///

    static OWLOntology loadOntologyFromFile(OWLOntologyManager manager)
            throws OWLOntologyCreationException {

        File file = new File("ontology.owl");
//        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);

        return manager.loadOntologyFromOntologyDocument(file);
    }


    // ==================== Get All Classes ============================== //
    public static JSONObject rootClasses(String classItem) throws Exception {

        System.out.println("Class: " + classItem);

        ArrayList<String> jsonObj = new ArrayList<String>();
        TreeMap<String, String> hierarchic = new TreeMap<String, String>();

        String uri = "";
        if(classItem == null || classItem == "") {
            uri = "http://www.w3.org/2002/07/owl#Thing";
        } else {
            uri = "http://swrc.ontoware.org/ontology#"+classItem;
        }

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
        // Ask the reasoner to do all the necessary work now
//        reasoner.precomputeInferences();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLClass organizationClass = fac.getOWLClass(IRI
                .create(uri));

        NodeSet<OWLClass> subClses = reasoner.getSubClasses(organizationClass, true);

        Set<OWLClass> clses = subClses.getFlattened();

        for (OWLClass cls : clses) {

            // Get Results and split by Class name
            Pattern p = Pattern.compile("([A-Z])\\w+");
            Matcher m = p.matcher(cls.toString());

            while ( m.find() ) {
                // add classes to the array list
                String currentClass = cls.toString().substring(m.start(), m.end());

                if(!currentClass.equals("Nothing")) {
                    // get subClasses
                    String subClassesList = getSubClasses(currentClass, fac, reasoner).replace("\\", "");
                    hierarchic.put(currentClass, subClassesList);
                }

                jsonObj.add("\""+currentClass+"\"");
            }
        }

        JSONObject hierarchicList=new JSONObject(hierarchic);

        return hierarchicList;
    }

    public static String getSubClasses(String classItem, OWLDataFactory fac, OWLReasoner reasoner) throws Exception {

        ArrayList<String> jsonObj = new ArrayList<String>();
        TreeMap<String, String> hierarchic = new TreeMap<String, String>();

        String uri = "";
        if(classItem != null || classItem != "") {
            uri = "http://swrc.ontoware.org/ontology#"+classItem;
        } else {
            return "";
        }

        OWLClass organizationClass = fac.getOWLClass(IRI
                .create(uri));

        NodeSet<OWLClass> subClses = reasoner.getSubClasses(organizationClass, true);

        Set<OWLClass> clses = subClses.getFlattened();

        for (OWLClass cls : clses) {
            // Get Results and split by Class name
            Pattern p = Pattern.compile("([A-Z])\\w+");
            Matcher m = p.matcher(cls.toString());

            while ( m.find() ) {
                String currentClass = cls.toString().substring(m.start(), m.end());
                // add classes to the array list
                if(!currentClass.equals("Nothing")) {
                    // get subClasses
                    String subClassesList = getSubClasses(currentClass, fac, reasoner);
                    hierarchic.put(currentClass, subClassesList);
                }

                jsonObj.add("\""+currentClass+"\"");
            }

        }
        JSONObject hierarchicList=new JSONObject(hierarchic);

        return hierarchicList.toString();
    }
    // ============================================================== //

    // ====================== Get Class Individuals ================= //
    //public static String classesIndividuals(String classItem) throws Exception {
    public static ArrayList classesIndividuals(String classItem) throws Exception {

        ArrayList<String> jsonObj = new ArrayList<String>();
    //        String jsonObj = "{ \"classes\": ";
        JSONArray listCls = new JSONArray();
        JSONArray listInd = new JSONArray();

        String uri = "";
        if(classItem == null || classItem == "") {
            uri = "http://www.w3.org/2002/07/owl#Thing";
        } else {
            uri = "http://swrc.ontoware.org/ontology#"+classItem;
        }

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
        // Ask the reasoner to do all the necessary work now
//        reasoner.precomputeInferences();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLClass individs = fac.getOWLClass(IRI.create(uri));

        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                individs, true);

        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();

        OWLDataProperty hasName = fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#name"));

        System.out.println("Instances of class: ");
        int i = 0;
        for (OWLNamedIndividual ind : individuals) {
            i++;
            System.out.println(i+".    " + ind);
            if(i>200) {
                break;
            }

            //======================= Print name of the individual
            for (OWLLiteral l : reasoner.getDataPropertyValues(ind, hasName)) {
                if (l.getDatatype().isString()) {
                    System.out.println("Asserted value: " + l.getLiteral());
//                    jsonObj.add("\""+l.getLiteral()+"\"");
                    jsonObj.add("{\"id\":\""+ind.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", "")+"\", \"name\":\""+l.getLiteral()+"\"}");
//                    {"firstName":"John", "lastName":"Doe"},
                }
            }
    //            listInd.put(ind);
//            jsonObj.add("\""+ind.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", "")+"\"");
        }

        //        jsonObj += ", \"individuals\": " + (listInd.toString()) + " } ";

//        System.out.println("Array List: "+jsonObj);

        return jsonObj;
    }

    public static ArrayList getIndividual(String individual) throws Exception {

        ArrayList<String> json = new ArrayList<String>();

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
//        reasoner.precomputeInferences();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLNamedIndividual individ = fac.getOWLNamedIndividual(IRI
                .create("http://www.aifb.uni-karlsruhe.de/" + individual));

        //======================= Get DATA PROPERTY of the individual
        List<String> listDataProperty = new ArrayList();
        listDataProperty.add("abstract");
        listDataProperty.add("address");
        listDataProperty.add("booktitle");
        listDataProperty.add("chapter");
        listDataProperty.add("date");

        listDataProperty.add("edition");
        listDataProperty.add("email");
        listDataProperty.add("eventTitle");
        listDataProperty.add("fax");

        listDataProperty.add("hasPrice");
        listDataProperty.add("homepage");
        listDataProperty.add("howpublished");
        listDataProperty.add("isbn");
        listDataProperty.add("journal");

        listDataProperty.add("keywords");
        listDataProperty.add("location");
        listDataProperty.add("month");
        listDataProperty.add("name");
        listDataProperty.add("note");

        listDataProperty.add("number");
        listDataProperty.add("pages");
        listDataProperty.add("phone");
//        listDataProperty.add("photo");
        listDataProperty.add("price");

        listDataProperty.add("series");
        listDataProperty.add("source");
        listDataProperty.add("title");
        listDataProperty.add("type");
        listDataProperty.add("volume");

        listDataProperty.add("year");

        String dataStr = "";

        for (String property : listDataProperty) {
            String value = "\""+property+"\":\"";
            for (OWLLiteral l : reasoner.getDataPropertyValues(individ, fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#"+property)))) {
                if (l.getDatatype().isString()) {
//                    System.out.println("Asserted value: " + l.getLiteral());
                    value += StringEscapeUtils.unescapeJava(l.getLiteral().replace("\n", " ").replace("\r", " "));
                }
            }
            dataStr += value+"\"¡";
        }
        json.add("{ \"data\": [ {"+dataStr.substring(0, dataStr.length()-1)+"} ]");
//        json.add("{\"id\":\"" + individual + "\", \"data\": [ {"+dataStr.substring(0, dataStr.length()-1)+"} ]");

        //======================= Get OBJECT PROPERTY of the individual

        List<String> listOBJProperty = new ArrayList();
        listOBJProperty.add("affiliation");
        listOBJProperty.add("atEvent");
        listOBJProperty.add("author");
        listOBJProperty.add("carriedOutBy");
        listOBJProperty.add("carriesOut");

        listOBJProperty.add("cite");
        listOBJProperty.add("citeBy");
        listOBJProperty.add("cooperateWith");
        listOBJProperty.add("dealtWithIn");
        listOBJProperty.add("describesProject");

        listOBJProperty.add("developedBy");
        listOBJProperty.add("develops");
        listOBJProperty.add("editor");
        listOBJProperty.add("employs");
        listOBJProperty.add("financedBy");

        listOBJProperty.add("finances");
        listOBJProperty.add("givenBy");
        listOBJProperty.add("hasPartEvent");
        listOBJProperty.add("hasParts");
        listOBJProperty.add("head");

        listOBJProperty.add("headOf");
        listOBJProperty.add("headOfGroup");
        listOBJProperty.add("institution");
        listOBJProperty.add("isAbout");
        listOBJProperty.add("isWorkedOnBy");

        listOBJProperty.add("member");
        listOBJProperty.add("memberOfPC");
        listOBJProperty.add("organization");
        listOBJProperty.add("organizerOrChairOf");
        listOBJProperty.add("participant");

        listOBJProperty.add("product");
        listOBJProperty.add("projectInfo");
        listOBJProperty.add("publication");
        listOBJProperty.add("publisher");
        listOBJProperty.add("publishes");

        listOBJProperty.add("Root");
        listOBJProperty.add("RootRelation");
        listOBJProperty.add("school");
        listOBJProperty.add("student");
        listOBJProperty.add("studiesAt");

        listOBJProperty.add("supervises");
        listOBJProperty.add("supervisor");
        listOBJProperty.add("technicalReport");
        listOBJProperty.add("worksAtProject");

        String objStr = "";

        for (String propertyObj : listOBJProperty) {
            String value = "\""+propertyObj+"\":\"";
            int i=0;
            for (OWLNamedIndividual l : reasoner.getObjectPropertyValues(individ, fac.getOWLObjectProperty(IRI
                    .create("http://swrc.ontoware.org/ontology#" + propertyObj))).getFlattened()) {
                i++;
                // get title/name for objects
//                if(propertyObj.equals("publication")) {
                    ArrayList<String> nameResults = new ArrayList<String>();
                    nameResults = getNameTitle(l.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", ""), fac, reasoner);
//                    System.out.println(l.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", ""));
//                    System.out.println(nameResults.get(1));
//                }

                String yearInfo = "";
                if(nameResults.size()>1) {
                  yearInfo = " ("+nameResults.get(1)+")";
                }
                if(i>=1) {
                    //add sepparator if more elements
                    value += nameResults.get(0)+yearInfo+"="+l+"¿";
                } else {
                    value += nameResults.get(0)+yearInfo+"="+l;
                }
            }
            //delete the last separator if more elements added
            if(value.substring(value.length() - 1).equals(";")){
                value = value.substring(0, value.length()-1);
            }
            objStr += StringEscapeUtils.unescapeJava(value) + "\"¡";
        }
        json.add("\"objects\": [ {"+objStr.substring(0, objStr.length()-1)+"} ] }");
        System.out.println(json);

        return json;
    }

    // ===============================   get Title/Name of the individ   =====================================
    public static ArrayList getNameTitle(String individual, OWLDataFactory fac, OWLReasoner reasoner) throws Exception {

        ArrayList<String> name = new ArrayList<String>();

        OWLNamedIndividual individ = fac.getOWLNamedIndividual(IRI
                .create("http://www.aifb.uni-karlsruhe.de/" + individual));

        //======================= Get DATA PROPERTY of the individual
        List<String> listDataProperty = new ArrayList();
        listDataProperty.add("name");
        listDataProperty.add("title");
        listDataProperty.add("year");

        for (String property : listDataProperty) {
            for (OWLLiteral l : reasoner.getDataPropertyValues(individ, fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#"+property)))) {
                if (l.getDatatype().isString() && !l.getLiteral().equals("")) {
                    System.out.println("Asserted value: |" + l.getLiteral()+"|");
                    name.add(StringEscapeUtils.unescapeJava(l.getLiteral().replace("\n", " ").replace("\r", " ")));
                }
            }
        }

        return name;
    }


    // ====================================   Query Statistics   ===========================================
    public static HashMap queryPublicationsbyYear() throws Exception {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);

        OWLDataFactory fac = manager.getOWLDataFactory();

        ArrayList<String> pub = new ArrayList<String>();

        HashMap<String, Integer> years = new HashMap<String, Integer>();

        try {
            QueryEngine queryEng = QueryEngine.create(manager, reasoner);

            String q = "SELECT ?subject\n" +
                    "WHERE { SubClassOf(?subject, <http://swrc.ontoware.org/ontology#Publication>) }";

            Query query = Query.create(q);
            // Execute the query and generate the result set
            QueryResult result = queryEng.execute(query);

            if(!result.ask()) {
                System.out.println("Query has no solution.\n");
            }
            else {
                String s = result.toString();

                Pattern p = Pattern.compile("([A-Z])\\w+");
                Matcher m = p.matcher(s);

                ArrayList<String>  v = new ArrayList<String>();
                // Get Classes names,
                while ( m.find() ) {
                    String word = s.substring(m.start(), m.end());
                    // skip Nothing class, no individs for it
                    if(word.equals("Nothing")) {
                        continue;
                    }
                    v.add(word);
                }

                // go for each Class get individs
                for(int i = 0; i < v.size() ; i++) {

                    // till here, have all classes, now get individs and count them for each
                    String uri = "http://swrc.ontoware.org/ontology#"+v.get(i);

                    OWLClass individs = fac.getOWLClass(IRI.create(uri));

                    NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                            individs, true);

                    Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();

                    OWLDataProperty hasYear = fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#year"));

                    int j = 0;
                    // get for each individ Year property
                    for (OWLNamedIndividual ind : individuals) {
                        j++;

                        for (OWLLiteral l : reasoner.getDataPropertyValues(ind, hasYear)) {
                            if (l.getDatatype().isString()) {
                                // check if year is not in the list already
                                if(!years.containsValue(l.getLiteral())) {
                                    //add year to the list, together with counting
                                    if(years.get(l.getLiteral()) == null) {
                                        years.put(l.getLiteral(), 1);
                                    } else {
                                        years.put(l.getLiteral(), years.get(l.getLiteral())+1);
                                    }

                                }
                            }
                        }
                    }
                    // Add Totals individs per Class
                    pub.add(j + "=" + v.get(i));
                }

            }

        }
        catch(UnsupportedOperationException exception) {
            System.out.println("Unsupported reasoner operation.");
        }
        finally {
            if (reasoner != null) {
                reasoner.dispose();
            }
        }

        return years;
    }


    public static ArrayList queryIndividsPerClass(String cls) throws Exception {

        if(cls.equals("")) {
            return new ArrayList<String>();
        } else {

            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

            OWLOntology ont = loadOntologyFromFile(manager);

            OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

            OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
            // Ask the reasoner to do all the necessary work now
            //        reasoner.precomputeInferences();

            OWLDataFactory fac = manager.getOWLDataFactory();

            ArrayList<String> pub = new ArrayList<String>();

            try {
                QueryEngine queryEng = QueryEngine.create(manager, reasoner);

                String q = "SELECT ?subject\n" +
                        "WHERE { SubClassOf(?subject, <http://swrc.ontoware.org/ontology#" + cls + ">) }";

                Query query = Query.create(q);
                // Execute the query and generate the result set
                QueryResult result = queryEng.execute(query);

                if (!result.ask()) {
                    System.out.println("Query has no solution.\n");
                } else {
                    String s = result.toString();
                    // Get Results and split by Class name
                    Pattern p = Pattern.compile("([A-Z])\\w+");
                    Matcher m = p.matcher(s);

                    ArrayList<String> v = new ArrayList<String>();
                    // Get Classes names,
                    while (m.find()) {
                        String word = s.substring(m.start(), m.end());
                        // skip Nothing class, no individs for it
                        if (word.equals("Nothing")) {
                            continue;
                        }
                        // add classes to the array list
                        v.add(word);
                    }

                    // till here, have all classes, now get individs and count them for each
                    for (int i = 0; i < v.size(); i++) {

                        String uri = "http://swrc.ontoware.org/ontology#" + v.get(i);

                        OWLClass individs = fac.getOWLClass(IRI.create(uri));

                        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                                individs, true);

                        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();

                        int j = 0;
                        // count for each class individs
                        for (OWLNamedIndividual ind : individuals) {
                            j++;
                        }

                        // Add Totals individs per Class
                        pub.add(j + "=" + v.get(i));
                    }

                }

            } catch (UnsupportedOperationException exception) {
                System.out.println("Unsupported reasoner operation.");
            } finally {
                if (reasoner != null) {
                    reasoner.dispose();
                }
            }

            return pub;
        }
    }

    // ======================== Statistics for Individ get Publications by Year ========================
    public static HashMap queryPublicationsbyYearIndivid(String individual) throws Exception {
        System.out.println("Individ: " + individual);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
//        reasoner.precomputeInferences();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLNamedIndividual individ = fac.getOWLNamedIndividual(IRI
                .create("http://www.aifb.uni-karlsruhe.de/" + individual));

        HashMap<String, Integer> years = new HashMap<String, Integer>();

        int i=0;

        List<String> listDataProperty = new ArrayList();
        listDataProperty.add("publication");
        listDataProperty.add("publishes");
        listDataProperty.add("projectInfo");

        for (String property : listDataProperty) {
            for (OWLNamedIndividual l : reasoner.getObjectPropertyValues(individ, fac.getOWLObjectProperty(IRI
                    .create("http://swrc.ontoware.org/ontology#"+property))).getFlattened()) {
                i++;

                //            System.out.println(l.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", ""));
                String year = getYearPublication(l.toString().replace("<http://www.aifb.uni-karlsruhe.de/", "").replace(">", ""), fac, reasoner);
                //            System.out.println("Year: "+year);

                // check if year is not in the list already
                if (!years.containsValue(year)) {
                    //add year to the list, together with counting
                    if (years.get(year) == null) {
                        years.put(year, 1);
                    } else {
                        years.put(year, years.get(year) + 1);
                    }

                }

            }
        }
        System.out.println("Total: "+i);

        return years;
    }


    // ===============================   get Title/Name of the individ   =====================================
    public static String getYearPublication(String id, OWLDataFactory fac, OWLReasoner reasoner) throws Exception {

        String year = "";

        OWLNamedIndividual individ = fac.getOWLNamedIndividual(IRI
                .create("http://www.aifb.uni-karlsruhe.de/" + id));

        for (OWLLiteral l : reasoner.getDataPropertyValues(individ, fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#year")))) {
            if (l.getDatatype().isString() && !l.getLiteral().equals("")) {
                year = l.getLiteral();
            }
        }

        return year;
    }


    public static ArrayList getAllClasses() throws Exception {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);

        OWLDataFactory fac = manager.getOWLDataFactory();

        ArrayList<String> classes = new ArrayList<String>();

        try {
            QueryEngine queryEng = QueryEngine.create(manager, reasoner);

            String q = "SELECT ?subject\n" +
                    "WHERE { StrictSubClassOf(?subject, <http://www.w3.org/2002/07/owl#Thing>) }";

            Query query = Query.create(q);
            // Execute the query and generate the result set
            QueryResult result = queryEng.execute(query);

            if(!result.ask()) {
                System.out.println("Query has no solution.\n");
            }
            else {
                System.out.println(result);
                String s = result.toString();
                // Get Results and split by Class name
                Pattern p = Pattern.compile("([A-Z])\\w+");
                Matcher m = p.matcher(s);

                ArrayList<String>  v = new ArrayList<String>();
                // Get Classes names,
                while ( m.find() ) {
                    String cls = s.substring(m.start(), m.end());
                    // skip Nothing class
                    if(cls.equals("Nothing")) {
                        continue;
                    }
                    // add classes to the array list
                    classes.add(cls);
                }
            }
        }
        catch(UnsupportedOperationException exception) {
            System.out.println("Unsupported reasoner operation.");
        }
        finally {
            if (reasoner != null) {
                reasoner.dispose();
            }
        }

        return classes;
    }


}
