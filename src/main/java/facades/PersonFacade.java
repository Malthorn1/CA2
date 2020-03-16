package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
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
    
    public PersonDTO getPerson(int id){
        EntityManager em = emf.createEntityManager();
        try{
            Person p = em.find(Person.class, (long)id);
            return new PersonDTO(p);
        }finally{
            em.close();
        }
    }
    
    public void addPerson(Person person){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
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

}
