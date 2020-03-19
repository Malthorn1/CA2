package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
        
    }
    
    public PersonsDTO getAllPersons(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Person> query = em.createQuery("SELECT p from Person p", Person.class);
            return new PersonsDTO(query.getResultList());
        }finally{
            em.close();
        }
    }
    
    public PersonDTO getPerson(int id){
        EntityManager em = emf.createEntityManager();
        try{
            Person p = em.find(Person.class, (long)id);
            return new PersonDTO(p);
        }finally{
            em.close();
        }
    }
    
    public PersonDTO addPerson(String firstName, String lastName, String email){
        Person person = new Person(email, firstName, lastName);
        
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            PersonDTO pdto = new PersonDTO(person);
            return pdto;
        }finally{
            em.close();
        }
    }
    
    public Person addAddress(Person person, Address address){
        EntityManager em = emf.createEntityManager();
        person.setAddress(address);
        
        try{
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return person;
    }
    
    public Person addPhone(Person person, Phone phone){
        EntityManager em = emf.createEntityManager();
        person.addPhones(phone);
        
        try{
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return person;
    }
    
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
