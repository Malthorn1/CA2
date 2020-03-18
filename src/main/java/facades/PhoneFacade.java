package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PhoneFacade {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PhoneFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
        public long getPhoneCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long phoneCount = (long)em.createQuery("SELECT COUNT(p) FROM Phone p").getSingleResult();
            return phoneCount;
        }finally{  
            em.close();
        }
        
        }
    
        public PersonDTO getPersonByPhoneNumber(int number){
        EntityManager em = emf.createEntityManager();
        try{
            List<Phone> query =  em.createQuery("SELECT c FROM Phone c where c.number like :number", Phone.class ) 
                    .setParameter("number", number)
                    .getResultList() ;
            Person p = em.find(Person.class, (long) query.get(0).getId()) ;
            PersonDTO p2 = new PersonDTO(p) ; 
            return p2; 
        }finally{
            em.close();
        }
    }
    
    public List<PersonDTO> getAllPersons(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> q = em.createQuery("SELECT p from Person p", Person.class);
            List<Person> persons = q.getResultList();
            List<PersonDTO> pDTO = new ArrayList<>();
            for (Person person : persons) {
                PersonDTO pdto = new PersonDTO(person);
                pDTO.add(pdto);
            }
            return pDTO;
        }finally{
            em.close();
        }
    }
    
    //    //TODO Remove/Change this before use
//    public long getPersonCount(){
//        EntityManager em = emf.createEntityManager();
//        try{
//            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
//            return personCount;
//        }finally{  
//            em.close();
//        }
//        
//    }
//    
//    
//    
//    public void addPerson(Person person){
//        EntityManager em = emf.createEntityManager();
//        try{
//            em.getTransaction().begin();
//            em.persist(person);
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//    }
//    
//    public Person addAddress(Person person, Address address){
//        EntityManager em = emf.createEntityManager();
//        person.setAddress(address);
//        
//        try{
//            em.getTransaction().begin();
//            em.merge(person);
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//        return person;
//    }
//    
//    public Person addPhone(Person person, Phone phone){
//        EntityManager em = emf.createEntityManager();
//        person.addPhones(phone);
//        
//        try{
//            em.getTransaction().begin();
//            em.merge(person);
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//        return person;
//    }
//    
//    public Person addHobby(Person person, Hobby hobby){
//        EntityManager em = emf.createEntityManager();
//        person.addHobby(hobby);
//        
//        try{
//            em.getTransaction().begin();
//            em.merge(person);
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//        return person;
//    }

}
