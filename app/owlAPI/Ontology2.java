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
public class Ontology2 {


    public static String getOntologyComment() throws Exception {

        String comment = "/";

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntology ont = loadOntologyFromFile(manager);

        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLAnnotation commentAnno = fac.getOWLAnnotation(fac.getRDFSComment(), fac.getOWLLiteral("A class which represents pizzas", "en"));

        System.out.println("Comment: " + commentAnno.toString());

        return commentAnno.toString();
    }
    

    

    // ========================================= Creating new instances in Ontology =============================== //

    public static void createNewClass(String cls) throws Exception {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

//        OWLOntology ontology = loadOntologyFromFile(manager);

//        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
//        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);

        // We can get a reference to a data factory from an OWLOntologyManager.
//        OWLDataFactory factory = manager.getOWLDataFactory();

        // Ontologies can have an IRI, which is used to identify the ontology.
        // You should think of the ontology IRI as the "name" of the ontology.
        // This IRI frequently resembles a Web address (i.e. http://...), but it
        // is important to realise that the ontology IRI might not necessarily
        // be resolvable. In other words, we can't necessarily get a document
        // from the URL corresponding to the ontology IRI, which represents the
        // ontology. In order to have a concrete representation of an ontology
        // (e.g. an RDF/XML file), we MAP the ontology IRI to a PHYSICAL URI. We
        // do this using an IRIMapper Let's create an ontology and name it
        // "http://www.co-ode.org/ontologies/testont.owl" We need to set up a
        // mapping which points to a concrete file where the ontology will be
        // stored. (It's good practice to do this even if we don't intend to
        // save the ontology).
        IRI ontologyIRI = IRI
                .create("http://www.stratan.eu/ontologies/testont.owl");
        // Create the document IRI for our ontology
        IRI documentIRI = IRI.create("file:MyOnt.owl");
        // Set up a mapping, which maps the ontology to the document IRI
        SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
        manager.getIRIMappers().add(mapper);
        // Now create the ontology - we use the ontology IRI (not the physical
        // URI)
        OWLOntology ontology = manager.createOntology(ontologyIRI);
        // Now we want to specify that A is a subclass of B. To do this, we add
        // a subclass axiom. A subclass axiom is simply an object that specifies
        // that one class is a subclass of another class. We need a data factory
        // to create various object from. Each manager has a reference to a data
        // factory that we can use.
        OWLDataFactory factory = manager.getOWLDataFactory();
        // Get hold of references to class A and class B. Note that the ontology
        // does not contain class A or classB, we simply get references to
        // objects from a data factory that represent class A and class B
        OWLClass clsA = factory.getOWLClass(IRI.create("http://swrc.ontoware.org/ontology#A"));
        OWLClass clsB = factory.getOWLClass(IRI.create("http://swrc.ontoware.org/ontology#B"));
        // Now create the axiom
        OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsA, clsB);
        // We now add the axiom to the ontology, so that the ontology states
        // that A is a subclass of B. To do this we create an AddAxiom change
        // object. At this stage neither classes A or B, or the axiom are
        // contained in the ontology. We have to add the axiom to the ontology.
        AddAxiom addAxiom = new AddAxiom(ontology, axiom);
        // We now use the manager to apply the change
//        manager.applyChange(addAxiom);
        // The ontology will now contain references to class A and class B -
        // that is, class A and class B are contained within the SIGNATURE of
        // the ontology let's print them out
        for (OWLClass cl : ontology.getClassesInSignature()) {
            // do anything that's necessary, e.g., print them out
            System.out.println("Referenced class: " + cl);
        }
        // We should also find that B is an ASSERTED superclass of A
//        Iterable<OWLClassExpression> superClasses = sup(
//                ontology.filterAxioms(Filters.subClassWithSub, clsA, INCLUDED),
//                OWLClassExpression.class);
        // Now save the ontology. The ontology will be saved to the location
        // where we loaded it from, in the default ontology format
        manager.saveOntology(ontology);
    }


    /**
     * An example which shows how to interact with a reasoner. In this example
     * Pellet is used as the reasoner. You must get hold of the pellet libraries
     * from pellet.owldl.com.
     *
     * @throws Exception
     *         exception
     */

    public static String shouldUseReasoner2() throws Exception {
        // Create our ontology manager in the usual way.
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("ontology.owl");
        OWLOntology ont = manager.loadOntologyFromOntologyDocument(file);
//        OWLOntology ont = loadPizza(manager);

        // We need to create an instance of OWLReasoner. An OWLReasoner provides
        // the basic query functionality that we need, for example the ability
        // obtain the subclasses of a class etc. To do this we use a reasoner
        // factory. Create a reasoner factory. In this case, we will use HermiT,
        // but we could also use FaCT++ (http://code.google.com/p/factplusplus/)
        // or Pellet(http://clarkparsia.com/pellet) Note that (as of 03 Feb
        // 2010) FaCT++ and Pellet OWL API 3.0.0 compatible libraries are
        // expected to be available in the near future). For now, we'll use
        // HermiT HermiT can be downloaded from http://hermit-reasoner.com Make
        // sure you get the HermiT library and add it to your class path. You
        // can then instantiate the HermiT reasoner factory: Comment out the
        // first line below and uncomment the second line below to instantiate
        // the HermiT reasoner factory. You'll also need to import the
        // org.semanticweb.HermiT.Reasoner package.
//        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        // We'll now create an instance of an OWLReasoner (the implementation
        // being provided by HermiT as we're using the HermiT reasoner factory).
        // The are two categories of reasoner, Buffering and NonBuffering. In
        // our case, we'll create the buffering reasoner, which is the default
        // kind of reasoner. We'll also attach a progress monitor to the
        // reasoner. To do this we set up a configuration that knows about a
        // progress monitor. Create a console progress monitor. This will print
        // the reasoner progress out to the console.
        // ConsoleProgressMonitor progressMonitor = new
        // ConsoleProgressMonitor();
        // Specify the progress monitor via a configuration. We could also
        // specify other setup parameters in the configuration, and different
        // reasoners may accept their own defined parameters this way.
        // OWLReasonerConfiguration config = new SimpleConfiguration(
        // progressMonitor);
        // Create a reasoner that will reason over our ontology and its imports
        // closure. Pass in the configuration.
        // OWLReasoner reasoner = reasonerFactory.createReasoner(ont, config);
        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
        // Ask the reasoner to do all the necessary work now

//        reasoner.precomputeInferences();


        // We can determine if the ontology is actually consistent (in this
        // case, it should be).

        //PASSED TRUE
//        boolean consistent = reasoner.isConsistent();
//        System.out.println("Consistent: " + consistent);

        // We can easily get a list of unsatisfiable classes. (A class is
        // unsatisfiable if it can't possibly have any instances). Note that the
        // getUnsatisfiableClasses method is really just a convenience method
        // for obtaining the classes that are equivalent to owl:Nothing. In our
        // case there should be just one unsatisfiable class - "mad_cow" We ask
        // the reasoner for the unsatisfiable classes, which returns the bottom
        // node in the class hierarchy (an unsatisfiable class is a subclass of
        // every class).

//        Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses();
        // This node contains owl:Nothing and all the classes that are
        // equivalent to owl:Nothing - i.e. the unsatisfiable classes. We just
        // want to print out the unsatisfiable classes excluding owl:Nothing,
        // and we can used a convenience method on the node to get these

        //PASSED TRUE
//        Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom();
//        if (!unsatisfiable.isEmpty()) {
//            System.out.println("The following classes are unsatisfiable: ");
//            for (OWLClass cls : unsatisfiable) {
//                System.out.println("    " + cls);
//            }
//        } else {
//            System.out.println("There are no unsatisfiable classes");
//        }

        // Now we want to query the reasoner for all descendants of vegetarian.
        // Vegetarians are defined in the ontology to be animals that don't eat
        // animals or parts of animals.
        OWLDataFactory fac = manager.getOWLDataFactory();
        // Get a reference to the vegetarian class so that we can as the
        // reasoner about it. The full IRI of this class happens to be:
        // <http://owl.man.ac.uk/2005/07/sssw/people#vegetarian>
        OWLClass organizationClass = fac.getOWLClass(IRI
                .create("http://swrc.ontoware.org/ontology#Graduate"));
//        OWLClass organizationClass = fac.getOWLClass(IRI
//                .create("http://www.w3.org/2002/07/owl#Thing"));
        // Now use the reasoner to obtain the subclasses of vegetarian. We can
        // ask for the direct subclasses of vegetarian or all of the (proper)
        // subclasses of vegetarian. In this case we just want the direct ones
        // (which we specify by the "true" flag).
        NodeSet<OWLClass> subClses = reasoner.getSubClasses(organizationClass, true);
        // The reasoner returns a NodeSet, which represents a set of Nodes. Each
        // node in the set represents a subclass of vegetarian pizza. A node of
        // classes contains classes, where each class in the node is equivalent.
        // For example, if we asked for the subclasses of some class A and got
        // back a NodeSet containing two nodes {B, C} and {D}, then A would have
        // two proper subclasses. One of these subclasses would be equivalent to
        // the class D, and the other would be the class that is equivalent to
        // class B and class C. In this case, we don't particularly care about
        // the equivalences, so we will flatten this set of sets and print the
        // result
        Set<OWLClass> clses = subClses.getFlattened();
        System.out.println("Subclasses of root: ");
        for (OWLClass cls : clses) {
            System.out.println("    " + cls);
        }

        // In this case, we should find that the classes, cow, sheep and giraffe
        // are vegetarian. Note that in this ontology only the class cow had
        // been stated to be a subclass of vegetarian. The fact that sheep and
        // giraffe are subclasses of vegetarian was implicit in the ontology
        // (through other things we had said) and this illustrates why it is
        // important to use a reasoner for querying an ontology. We can easily
        // retrieve the instances of a class. In this example we'll obtain the
        // instances of the class pet. This class has a full IRI of
        // <http://owl.man.ac.uk/2005/07/sssw/people#pet> We need to obtain a
        // reference to this class so that we can ask the reasoner about it.
        OWLClass person = fac.getOWLClass(IRI
                .create("http://swrc.ontoware.org/ontology#Graduate"));
        // Ask the reasoner for the instances of pet
        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                person, true);
        // The reasoner returns a NodeSet again. This time the NodeSet contains
        // individuals. Again, we just want the individuals, so get a flattened
        // set.
        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        System.out.println("Instances of person: ");
        for (OWLNamedIndividual ind : individuals) {
            System.out.println("    " + ind);
        }

        // Again, it's worth noting that not all of the individuals that are
        // returned were explicitly stated to be pets. Finally, we can ask for
        // the property values (property assertions in OWL speak) for a given
        // individual and property. Let's get the property values for the
        // individual Mick, the full IRI of which is
        // <http://owl.man.ac.uk/2005/07/sssw/people#Mick> Get a reference to
        // the individual Mick


        //======================= check individual's by property objects
        OWLNamedIndividual mick = fac.getOWLNamedIndividual(IRI
//                .create("http://swrc.ontoware.org/ontology#Organization"));
                .create("http://www.aifb.uni-karlsruhe.de/Personen/viewPersonOWL/id2105instance"));
//                .create("http://www.aifb.uni-karlsruhe.de/Publikationen/viewExternerAutorOWL/id299instance"));
        // Let's get the pets of Mick Get hold of the has_pet property which has
        // a full IRI of <http://owl.man.ac.uk/2005/07/sssw/people#has_pet>
        OWLObjectProperty hasPet = fac.getOWLObjectProperty(IRI
//                .create("http://swrc.ontoware.org/ontology#year"));
                .create("http://swrc.ontoware.org/ontology#publication"));
        // Now ask the reasoner for the has_pet property values for Mick
        NodeSet<OWLNamedIndividual> petValuesNodeSet = reasoner
                        .getObjectPropertyValues(mick, hasPet);
        Set<OWLNamedIndividual> values = petValuesNodeSet.getFlattened();
        System.out.println("The publication property values for individual are: ");
        for (OWLNamedIndividual ind : values) {
            System.out.println("    " + ind);
        }

        //======================= Print name of the individual
        OWLDataProperty hasAge = fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#name"));

        for (OWLLiteral l : reasoner.getDataPropertyValues(mick, hasAge)) {
            if (l.getDatatype().isString()) {
                System.out.println("Asserted value: " + l.getLiteral());
            }
        }

        OWLObjectProperty hasProperty = fac.getOWLObjectProperty(IRI
//                .create("http://swrc.ontoware.org/ontology#author"));
                .create("http://swrc.ontoware.org/ontology#publication"));
//        NodeSet<OWLObjectPropertyExpression> allProperties = reasoner.getEquivalentObjectProperties(hasPet);
        // get owl:topObjectProperty
//        NodeSet<OWLObjectPropertyExpression> allProperties = reasoner.getSuperObjectProperties(hasProperty, true);
        // get owl:bottomObjectProperty
//        NodeSet<OWLObjectPropertyExpression> allProperties = reasoner.getDisjointObjectProperties(hasProperty, true);

        NodeSet<OWLObjectPropertyExpression> allProperties = reasoner.getSuperObjectProperties(hasProperty, true);

        System.out.println("allProperties: " + allProperties);
        Set<OWLObjectPropertyExpression> allProp = allProperties.getFlattened();
//        Set<OWLObjectPropertyExpression> allProp = allProperties.getEntities();
        for (OWLObjectPropertyExpression val : allProp) {
            System.out.println("    " + val);
        }

//        Map<OWLObjectPropertyExpression, Set<OWLNamedIndividual>> all = reasoner.getDataPropertyValues(ont);
//        for (OWLLiteral l : reasoner.getDataPropertyValues(mick)) {
//            if (l.getDatatype().isString()) {
//                System.out.println("Asserted value: " + l.getLiteral());
//            }
//        }

//        for (OWLLiteral l : reasoner.getDataPropertyValues(mick)) {
//            if (l.getDatatype().isString()) {
//                System.out.println("Asserted value: " + l.getLiteral());
//            }
//        }

//        Set<OWLDataProperty> allEquivalentProperties = fac.getEquivalentDataProperties(IRI.create("http://swrc.ontoware.org/ontology#name"));
//        for (OWLDataProperty property : allEquivalentProperties) {
//            System.out.println("    " + property);
//        }

//        Set<OWLObjectAssertionAxiom> set = fac.getObjectPropertyAssertionAxioms(mick);

/*
//Prepare the expression for the query
        OWLDataProperty p = fac.getOWLDataProperty("http://swrc.ontoware.org/ontology#name");
        OWLClassExpression ex =
                fac.getOWLDataHasValue(p, fac.getOWLLiteral("Claudio Giuliano"));

//Print out the results, John is inside
        Set<OWLNamedIndividual> result = reasoner.getInstances(ex, true).getFlattened();
        for (OWLNamedIndividual owlNamedIndividual : result) {
            System.out.println(owlNamedIndividual);
        }
        /*
        // Notice that Mick has a pet Rex, which wasn't asserted in the
        // ontology. Finally, let's print out the class hierarchy. Get hold of
        // the top node in the class hierarchy (containing owl:Thing) Now print
        // the hierarchy out

        */
/*
        final OWLAnnotationProperty comment = fac.getRDFSComment();

//Create a walker
        OWLOntologyWalker walker =
                new OWLOntologyWalker(Collections.singleton(ont));

//Define what's going to visited
        OWLOntologyWalkerVisitorEx<Object> visitor =
                new OWLOntologyWalkerVisitorEx<Object>(walker) {

                    //In your case you visit the annotations made with rdfs:comment
                    //over the object properties assertions
                    @Override
                    public Object visit(OWLObjectPropertyAssertionAxiom axiom) {
                        //Print them
                        System.out.println("== "+axiom.getAnnotations(comment));
                        return null;
                    }
                };

//Walks over the structure - triggers the walk
        walker.walkStructure(visitor);
*/


//        Node<OWLClass> topNode = reasoner.getTopClassNode();
//        print(topNode, reasoner, 0);

    return "|";
}


    public static String loadOntology() throws Exception {

        // Get hold of an ontology manager
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // We can also load ontologies from files. Create a file object that points to the local copy
        File file = new File("ontology.owl");

        String uri = "";
        // Load the local copy

        try {
            OWLOntology my_o = manager.loadOntologyFromOntologyDocument(file);
            System.out.println("COUNT ontology: " + my_o.getLogicalAxiomCount());
            System.out.println("Loaded ontology: " + my_o);
            // We can always obtain the location where an ontology was loaded from
            IRI documentIRI = manager.getOntologyDocumentIRI(my_o);
            System.out.println(" from: " + documentIRI);

            uri = my_o.toString();

        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }

        return uri;
    }

    //get Classes which have Individuals
    public static String getClassesOntology() throws Exception {
//
        JSONArray list = new JSONArray();
        String object = "{ \"classes\": ";
//
//        // Get hold of an ontology manager
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        // We can also load ontologies from files. Create a file object that points to the local copy
        File file = new File("ontology.owl");
//
//        // Load the local copy
        try {
            OWLOntology my_o = manager.loadOntologyFromOntologyDocument(file);
//
//            assertNotNull(my_o);
//            // Named classes referenced by axioms in the ontology.
            for (OWLClass cls : my_o.getClassesInSignature()) {
                System.out.println(cls);
                list.put(cls);
            }

        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }

        object += list.toString();

        object += " } ";

        return object;
    }


//    public static String walkerOntology() throws Exception {
//
//
//        // Get hold of an ontology manager
//        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        // We can also load ontologies from files. Create a file object that points to the local copy
//        File file = new File("ontology.owl");
//
//        // Load the local copy
//        try {
//            OWLOntology my_o = manager.loadOntologyFromOntologyDocument(file);
//
//            // Create the walker
//            OWLOntologyWalker walker =
//                    new OWLOntologyWalker(Collections.singleton(my_o));
//            // Now ask our walker to walk over the ontology
//            OWLOntologyWalkerVisitorEx<Object> visitor =
//                    new OWLOntologyWalkerVisitorEx<Object>(walker) {
//                        @Override
//                        public Object visit(OWLObjectSomeValuesFrom desc) {
//                            System.out.println(desc);
//                            System.out.println(" " + getCurrentAxiom());
//                            return null;
//                        }
//                    };
//            // Have the walker walk...
//            walker.walkStructure(visitor);
//
//        } catch (OWLOntologyCreationException e) {
//            e.printStackTrace();
//        }
//
//        return "|";
//    }

    /**
     * An example which shows how to interact with a reasoner. In this example
     * Pellet is used as the reasoner. You must get hold of the pellet libraries
     * from pellet.owldl.com.
     *
     * @throws Exception
     *         exception
     */
    public static String shouldUseReasoner() throws Exception {
        // Create our ontology manager in the usual way.
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("ontology.owl");
        OWLOntology ont = manager.loadOntologyFromOntologyDocument(file);
//        OWLOntology ont = loadPizza(manager);

        // We need to create an instance of OWLReasoner. An OWLReasoner provides
        // the basic query functionality that we need, for example the ability
        // obtain the subclasses of a class etc. To do this we use a reasoner
        // factory. Create a reasoner factory. In this case, we will use HermiT,
        // but we could also use FaCT++ (http://code.google.com/p/factplusplus/)
        // or Pellet(http://clarkparsia.com/pellet) Note that (as of 03 Feb
        // 2010) FaCT++ and Pellet OWL API 3.0.0 compatible libraries are
        // expected to be available in the near future). For now, we'll use
        // HermiT HermiT can be downloaded from http://hermit-reasoner.com Make
        // sure you get the HermiT library and add it to your class path. You
        // can then instantiate the HermiT reasoner factory: Comment out the
        // first line below and uncomment the second line below to instantiate
        // the HermiT reasoner factory. You'll also need to import the
        // org.semanticweb.HermiT.Reasoner package.
//        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        // We'll now create an instance of an OWLReasoner (the implementation
        // being provided by HermiT as we're using the HermiT reasoner factory).
        // The are two categories of reasoner, Buffering and NonBuffering. In
        // our case, we'll create the buffering reasoner, which is the default
        // kind of reasoner. We'll also attach a progress monitor to the
        // reasoner. To do this we set up a configuration that knows about a
        // progress monitor. Create a console progress monitor. This will print
        // the reasoner progress out to the console.
        // ConsoleProgressMonitor progressMonitor = new
        // ConsoleProgressMonitor();
        // Specify the progress monitor via a configuration. We could also
        // specify other setup parameters in the configuration, and different
        // reasoners may accept their own defined parameters this way.
        // OWLReasonerConfiguration config = new SimpleConfiguration(
        // progressMonitor);
        // Create a reasoner that will reason over our ontology and its imports
        // closure. Pass in the configuration.
        // OWLReasoner reasoner = reasonerFactory.createReasoner(ont, config);
        OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
        // Ask the reasoner to do all the necessary work now
        reasoner.precomputeInferences();
        // We can determine if the ontology is actually consistent (in this
        // case, it should be).

        //PASSED TRUE
//        boolean consistent = reasoner.isConsistent();
//        System.out.println("Consistent: " + consistent);

        // We can easily get a list of unsatisfiable classes. (A class is
        // unsatisfiable if it can't possibly have any instances). Note that the
        // getUnsatisfiableClasses method is really just a convenience method
        // for obtaining the classes that are equivalent to owl:Nothing. In our
        // case there should be just one unsatisfiable class - "mad_cow" We ask
        // the reasoner for the unsatisfiable classes, which returns the bottom
        // node in the class hierarchy (an unsatisfiable class is a subclass of
        // every class).

//        Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses();
        // This node contains owl:Nothing and all the classes that are
        // equivalent to owl:Nothing - i.e. the unsatisfiable classes. We just
        // want to print out the unsatisfiable classes excluding owl:Nothing,
        // and we can used a convenience method on the node to get these

        //PASSED TRUE
//        Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom();
//        if (!unsatisfiable.isEmpty()) {
//            System.out.println("The following classes are unsatisfiable: ");
//            for (OWLClass cls : unsatisfiable) {
//                System.out.println("    " + cls);
//            }
//        } else {
//            System.out.println("There are no unsatisfiable classes");
//        }

        // Now we want to query the reasoner for all descendants of vegetarian.
        // Vegetarians are defined in the ontology to be animals that don't eat
        // animals or parts of animals.
        OWLDataFactory fac = manager.getOWLDataFactory();
        // Get a reference to the vegetarian class so that we can as the
        // reasoner about it. The full IRI of this class happens to be:
        // <http://owl.man.ac.uk/2005/07/sssw/people#vegetarian>
        OWLClass organizationClass = fac.getOWLClass(IRI
                .create("http://swrc.ontoware.org/ontology#Graduate"));
//        OWLClass organizationClass = fac.getOWLClass(IRI
//                .create("http://www.w3.org/2002/07/owl#Thing"));
        // Now use the reasoner to obtain the subclasses of vegetarian. We can
        // ask for the direct subclasses of vegetarian or all of the (proper)
        // subclasses of vegetarian. In this case we just want the direct ones
        // (which we specify by the "true" flag).
        NodeSet<OWLClass> subClses = reasoner.getSubClasses(organizationClass, true);
        // The reasoner returns a NodeSet, which represents a set of Nodes. Each
        // node in the set represents a subclass of vegetarian pizza. A node of
        // classes contains classes, where each class in the node is equivalent.
        // For example, if we asked for the subclasses of some class A and got
        // back a NodeSet containing two nodes {B, C} and {D}, then A would have
        // two proper subclasses. One of these subclasses would be equivalent to
        // the class D, and the other would be the class that is equivalent to
        // class B and class C. In this case, we don't particularly care about
        // the equivalences, so we will flatten this set of sets and print the
        // result
        Set<OWLClass> clses = subClses.getFlattened();
        System.out.println("Subclasses of root: ");
        for (OWLClass cls : clses) {
            System.out.println("    " + cls);
        }

        // In this case, we should find that the classes, cow, sheep and giraffe
        // are vegetarian. Note that in this ontology only the class cow had
        // been stated to be a subclass of vegetarian. The fact that sheep and
        // giraffe are subclasses of vegetarian was implicit in the ontology
        // (through other things we had said) and this illustrates why it is
        // important to use a reasoner for querying an ontology. We can easily
        // retrieve the instances of a class. In this example we'll obtain the
        // instances of the class pet. This class has a full IRI of
        // <http://owl.man.ac.uk/2005/07/sssw/people#pet> We need to obtain a
        // reference to this class so that we can ask the reasoner about it.
        OWLClass person = fac.getOWLClass(IRI
                .create("http://swrc.ontoware.org/ontology#Graduate"));
        // Ask the reasoner for the instances of pet
        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                person, true);
        // The reasoner returns a NodeSet again. This time the NodeSet contains
        // individuals. Again, we just want the individuals, so get a flattened
        // set.
        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        System.out.println("Instances of person: ");
        for (OWLNamedIndividual ind : individuals) {
            System.out.println("    " + ind);
        }

        // Again, it's worth noting that not all of the individuals that are
        // returned were explicitly stated to be pets. Finally, we can ask for
        // the property values (property assertions in OWL speak) for a given
        // individual and property. Let's get the property values for the
        // individual Mick, the full IRI of which is
        // <http://owl.man.ac.uk/2005/07/sssw/people#Mick> Get a reference to
        // the individual Mick


        //======================= check individual's by property objects
        OWLNamedIndividual mick = fac.getOWLNamedIndividual(IRI
//                .create("http://swrc.ontoware.org/ontology#Organization"));
                .create("http://www.aifb.uni-karlsruhe.de/Personen/viewPersonOWL/id2105instance"));
//                .create("http://www.aifb.uni-karlsruhe.de/Publikationen/viewExternerAutorOWL/id299instance"));
        // Let's get the pets of Mick Get hold of the has_pet property which has
        // a full IRI of <http://owl.man.ac.uk/2005/07/sssw/people#has_pet>
        OWLObjectProperty hasPet = fac.getOWLObjectProperty(IRI
//                .create("http://swrc.ontoware.org/ontology#year"));
                .create("http://swrc.ontoware.org/ontology#publication"));
        // Now ask the reasoner for the has_pet property values for Mick
        NodeSet<OWLNamedIndividual> petValuesNodeSet = reasoner
                .getObjectPropertyValues(mick, hasPet);
        Set<OWLNamedIndividual> values = petValuesNodeSet.getFlattened();
        System.out.println("The publication property values for individual are: ");
        for (OWLNamedIndividual ind : values) {
            System.out.println("    " + ind);
        }

        //======================= Print name of the individual
        OWLDataProperty hasAge = fac.getOWLDataProperty(IRI.create("http://swrc.ontoware.org/ontology#name"));

        for (OWLLiteral l : reasoner.getDataPropertyValues(mick, hasAge)) {
            if (l.getDatatype().isString()) {
                System.out.println("Asserted value: " + l.getLiteral());
            }
        }

/*
//Prepare the expression for the query
        OWLDataProperty p = fac.getOWLDataProperty("http://swrc.ontoware.org/ontology#name");
        OWLClassExpression ex =
                fac.getOWLDataHasValue(p, fac.getOWLLiteral("John"));

//Print out the results, John is inside
        Set<OWLNamedIndividual> result = reasoner.getInstances(ex, true).getFlattened();
        for (OWLNamedIndividual owlNamedIndividual : result) {
            System.out.println(owlNamedIndividual);
        }
        /*
        // Notice that Mick has a pet Rex, which wasn't asserted in the
        // ontology. Finally, let's print out the class hierarchy. Get hold of
        // the top node in the class hierarchy (containing owl:Thing) Now print
        // the hierarchy out

        */

//        Node<OWLClass> topNode = reasoner.getTopClassNode();
//        print(topNode, reasoner, 0);

        return "|";
    }

    /**
     * This example shows how to examine the restrictions on a class.
     *
     * @throws Exception
     *         exception
     */

//    public static String shouldLookAtRestrictions() throws Exception {
//        // Create our manager
//        // Load the pizza ontology
//        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//        File file = new File("ontology.owl");
//        OWLOntology ont = man.loadOntologyFromOntologyDocument(file);
//        // We want to examine the restrictions on margherita pizza. To do this,
//        // we need to obtain a reference to the margherita pizza class. In this
//        // case, we know the URI for margherita pizza (it happens to be the
//        // ontology URI - the base URI plus #Margherita - note that this isn't
//        // always the case. A class may have a URI that bears no resemblance to
//        // the ontology URI which contains axioms about the class).
//        IRI margheritaPizzaIRI = IRI.create(ont.getOntologyID()
//                .getOntologyIRI().get()
//                + "#Graduate");
//        OWLClass margheritaPizza = man.getOWLDataFactory().getOWLClass(
//                margheritaPizzaIRI);
//
//        // Now we want to collect the properties which are used in existential
//        // restrictions on the class. To do this, we will create a utility class
//        // - RestrictionVisitor, which acts as a filter for existential
//        // restrictions. This uses the Visitor Pattern (google Visitor Design
//        // Pattern for more information on this design pattern, or see
//        // http://en.wikipedia.org/wiki/Visitor_pattern)
//        RestrictionVisitor restrictionVisitor = new RestrictionVisitor(singleton(ont));
//        // In this case, restrictions are used as (anonymous) superclasses, so
//        // to get the restrictions on margherita pizza we need to obtain the
//        // subclass axioms for margherita pizza.
//        for (OWLSubClassOfAxiom ax : ont
//                .getSubClassAxiomsForSubClass(margheritaPizza)) {
//            OWLClassExpression superCls = ax.getSuperClass();
//            // Ask our superclass to accept a visit from the RestrictionVisitor
//            // - if it is an existential restiction then our restriction visitor
//            // will answer it - if not our visitor will ignore it
//            superCls.accept(restrictionVisitor);
//        }
//        // Our RestrictionVisitor has now collected all of the properties that
//        // have been restricted in existential restrictions - print them out.
//        // System.out.println("Restricted properties for " + margheritaPizza
//        // + ": " + restrictionVisitor.getRestrictedProperties().size());
//        for (OWLObjectPropertyExpression prop : restrictionVisitor.getRestrictedProperties()) {
//            System.out.println("    " + prop);
//        }
//
//        return "|";
//    }
//
//    /**
//     * Visits existential restrictions and collects the properties which are
//     * restricted.
//     */
//    private static class RestrictionVisitor extends
//            OWLClassExpressionVisitorAdapter {
//
//
//        private final Set<OWLClass> processedClasses;
//        private final Set<OWLOntology> onts;
//
//        RestrictionVisitor(Set<OWLOntology> onts) {
//            processedClasses = new HashSet<OWLClass>();
//            this.onts = onts;
//        }
//
//        @Override
//        public void visit(OWLClass ce) {
//            if (!processedClasses.contains(ce)) {
//                // If we are processing inherited restrictions then we
//                // recursively visit named supers. Note that we need to keep
//                // track of the classes that we have processed so that we don't
//                // get caught out by cycles in the taxonomy
//                processedClasses.add(ce);
//                for (OWLOntology ont : onts) {
//                    for (OWLSubClassOfAxiom ax : ont
//                            .getSubClassAxiomsForSubClass(ce)) {
//                        ax.getSuperClass().accept(this);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void visit(OWLObjectSomeValuesFrom ce) {
//            // This method gets called when a class expression is an existential
//            // (someValuesFrom) restriction and it asks us to visit it
//        }
//    }


//    private static void print(Node<OWLClass> parent, OWLReasoner reasoner, int depth) {
//        // We don't want to print out the bottom node (containing owl:Nothing
//        // and unsatisfiable classes) because this would appear as a leaf node
//        // everywhere
//        if (parent.isBottomNode()) {
//            return;
//        }
//        // Print an indent to denote parent-child relationships
//        printIndent(depth);
//        // Now print the node (containing the child classes)
//        printNode(parent);
//        for (Node<OWLClass> child : reasoner.getSubClasses(
//                parent.getRepresentativeElement(), true)) {
//            assert child != null;
//            // Recurse to do the children. Note that we don't have to worry
//            // about cycles as there are non in the inferred class hierarchy
//            // graph - a cycle gets collapsed into a single node since each
//            // class in the cycle is equivalent.
//            print(child, reasoner, depth + 1);
//        }
//    }
//
//
//    private static void printIndent(int depth) {
//        for (int i = 0; i < depth; i++) {
//            System.out.print("    ");
//        }
//    }
//
//
//    private static void printNode(Node<OWLClass> node) {
//        DefaultPrefixManager pm = new DefaultPrefixManager(null, null,
//                "http://swrc.ontoware.org/ontology#");
//        // Print out a node as a list of class names in curly brackets
//        for (Iterator<OWLClass> it = node.getEntities().iterator(); it
//                .hasNext();) {
//            OWLClass cls = it.next();
//            // User a prefix manager to provide a slightly nicer shorter name
//            String shortForm = pm.getShortForm(cls);
//            assertNotNull(shortForm);
//        }
//    }


//    public static String shouldQueryWithReasoner() throws Exception {
//        // We will load the ontology and query it using a reasoner
//        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//        OWLOntology ont = loadOntologyFromFile(man);
//        // For this particular ontology, we know that all class, properties
//        // names etc. have URIs that is made up of the ontology IRI plus # plus
//        // the local name
////        String prefix = ont.getOntologyID().getOntologyIRI().get() + "#";
//        String prefix = "http://swrc.ontoware.org/ontology#";
//
//        // Create a reasoner. We will use Pellet in this case. Make sure that
//        // the latest version of the Pellet libraries are on the runtime class
//        // path
////        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
//        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
////        // Uncomment the line below
////        OWLReasonerFactory reasonerFactory = new PelletReasonerFactory();
//        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ont);
//        // Ask the reasoner to do all the necessary work now
//        reasoner.precomputeInferences();
////        // Now we can query the reasoner, suppose we want to determine the
////        // properties that instances of Marghertia pizza must have
////        OWLClass person = man.getOWLDataFactory().getOWLClass(
////                IRI.create(prefix + "Person"));
////        printProperties(man, ont, reasoner, person);
////        // We can also ask if the instances of a class must have a property
//        OWLClass graduate = man.getOWLDataFactory().getOWLClass(
//                IRI.create(prefix + "Graduate"));
//        OWLObjectProperty hasOrigin = man
//                .getOWLDataFactory()
//                .getOWLObjectProperty(IRI.create(prefix + "publication"));
//        if (hasProperty(man, reasoner, graduate, hasOrigin)) {
//            System.out.println("Instances of " + graduate + " have a publication");
//        }
//
//        return "|";
//    }
//
//
//    private static boolean hasProperty(OWLOntologyManager man,
//                                       OWLReasoner reasoner, OWLClass cls,
//                                       OWLObjectPropertyExpression prop) {
//        // To test whether the instances of a class must have a property we
//        // create a some values from restriction and then ask for the
//        // satisfiability of the class interesected with the complement of this
//        // some values from restriction. If the intersection is satisfiable then
//        // the instances of the class don't have to have the property,
//        // otherwise, they do.
//        OWLDataFactory dataFactory = man.getOWLDataFactory();
//        OWLClassExpression restriction = dataFactory
//                .getOWLObjectSomeValuesFrom(prop, dataFactory.getOWLThing());
//        // Now we see if the intersection of the class and the complement of
//        // this restriction is satisfiable
//        OWLClassExpression complement = dataFactory
//                .getOWLObjectComplementOf(restriction);
//        OWLClassExpression intersection = dataFactory
//                .getOWLObjectIntersectionOf(cls, complement);
//        return !reasoner.isSatisfiable(intersection);
//    }




    ///==============================================================///


    static OWLOntology loadOntologyFromFile(OWLOntologyManager manager)
            throws OWLOntologyCreationException {

        File file = new File("ontology.owl");
//        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);

        return manager.loadOntologyFromOntologyDocument(file);
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!
//public static String rootClasses(String classItem) throws Exception {
    public static ArrayList rootClasses(String classItem) throws Exception {

        System.out.println("Class: " + classItem);

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
        reasoner.precomputeInferences();

//        Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLClass organizationClass = fac.getOWLClass(IRI
                .create(uri));

        NodeSet<OWLClass> subClses = reasoner.getSubClasses(organizationClass, true);

        Set<OWLClass> clses = subClses.getFlattened();
//        System.out.println("Subclasses of Organization: ");
        for (OWLClass cls : clses) {
//            System.out.println("    " + cls);
//
//            Pattern pattern = Pattern.compile("(#[A-Za-z*])\\w+");
//            Matcher matcher = pattern.matcher(cls.toString());
//            if (matcher.find())
//            {
//                System.out.println(matcher.group(1));
//                classes.add(cls.toString());
//            }

            jsonObj.add("\""+cls.toString().replace("<http://swrc.ontoware.org/ontology#", "").replace(">", "")+"\"");

//            listCls.put(cls.toString().replace("<http://swrc.ontoware.org/ontology#", "").replace(">", ""));

        }

//        jsonObj += listCls.toString();

        OWLClass individs = fac.getOWLClass(IRI.create(uri));

        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                individs, true);

        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        System.out.println("Instances of class: ");
        for (OWLNamedIndividual ind : individuals) {
            System.out.println("    " + ind);
//            listInd.put(ind);
//            jsonObj.add(ind);
        }

//        jsonObj += ", \"individuals\": " + (listInd.toString()) + " } ";

        System.out.println("Array List: "+jsonObj);

        return jsonObj;
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //public static String classesIndividuals(String classItem) throws Exception {
    public static ArrayList classesIndividuals(String classItem) throws Exception {

        System.out.println("Class: " + classItem);

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
        reasoner.precomputeInferences();

        OWLDataFactory fac = manager.getOWLDataFactory();

        OWLClass individs = fac.getOWLClass(IRI.create(uri));

        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
                individs, true);

        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        System.out.println("Instances of class: ");
        for (OWLNamedIndividual ind : individuals) {
            System.out.println("    " + ind);
    //            listInd.put(ind);
            jsonObj.add("\""+ind.toString()+"\"");
        }

    //        jsonObj += ", \"individuals\": " + (listInd.toString()) + " } ";

        System.out.println("Array List: "+jsonObj);

        return jsonObj;
    }
}
