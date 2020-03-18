//package facades;
//
//import dto.PersonDTO;
//import entities.Address;
//import utils.EMF_Creator;
//import entities.Person;
//import entities.Phone;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static sun.jvm.hotspot.code.CompressedStream.L;
//import utils.EMF_Creator.DbSelector;
//import utils.EMF_Creator.Strategy;
//
////Uncomment the line below, to temporarily disable this test
////@Disabled
//public class PhoneFacadeTest {
//
//    private static EntityManagerFactory emf;
//    private static PhoneFacade facade;
//
//    public PhoneFacadeTest() {
//    }
//
//    //@BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/ca2_test",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
//        facade = PhoneFacade.getFacadeExample(emf);
//    }
//
//    /*   **** HINT **** 
//        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
//        ONE COMMON place accessible from anywhere.
//        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
//        See below for how to use these files. This is our RECOMENDED strategy
//     */
//    @BeforeAll
//    public static void setUpClassV2() {
//       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
//       facade = PhoneFacade.getFacadeExample(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    //TODO -- Make sure to change the script below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Phone.deleteAllRows").executeUpdate(); 
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            Person p1 = new Person("batman@arto.dk", "batman", "batmansen");
//            
//            Person p2 = new Person("superman@arto.dk", "superman", "supermansen");
//            p1.setAddress(new Address("Valbylanggade", "den er lang"));
//            p2.setAddress(new Address("valdemarsgade", "LOL"));
//            
//            Phone askephone = new Phone(42131388, "Hjem");
//            p1.addPhones(askephone);
//            
//            em.persist(p1);
//            em.persist(p2);
//            
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//    }
//
//    // TODO: Delete or change this method 
//    @Test
//    public void testAFacadeMethod() {
//        assertEquals(1, facade.getPhoneCount(), "Expects one rows in the database");
//    }
//    
//    
//    @Test
//    public void testGetAllPersons(){
//        assertEquals(2, facade.getAllPersons().size());
//    }
////    
////  //  @Test
////    public void getPersonByPhoneNumber () {
////        int phonenumber  = 42131388; 
////        PersonDTO p = facade.getPersonByPhoneNumber(phonenumber); 
////       
////        
////        assertEquals(p.getFirstName(), "batman");
////    }
////    
////    
////    
////    @Test
////    public void testGetPerson(){
////        List<PersonDTO> p = facade.getAllPersons();
////        String fName = p.get(0).getFirstName();
////        String lName = p.get(0).getLastName();
////        String email = p.get(0).getEmail();
////        Long fakeid = p.get(0).getId();
////        int id = (int)fakeid;
////        
////        assertTrue(facade.getPerson(p.get(0).getId()).getFirstName().contains(fName));
////        assertTrue(facade.getPerson(1).getLastName().contains(lName));
////        assertTrue(facade.getPerson(1).getEmail().contains(email));
////    }
//
//}
