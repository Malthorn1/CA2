package facades;

import entities.Address;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    public AddressFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getAddressCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(a) FROM Address a").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
        
    }
    
    
    public void addAddress(Address address){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
        public Person addCityInfo(Person person, Address address){
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
