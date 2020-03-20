package facades;

import dto.PersonDTO;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CityInfoFacade {
    
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public CityInfoFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityInfoFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getCityInfoCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long personCount = (long) em.createQuery("SELECT COUNT(c) FROM CityInfo c").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }
        
    }
    
    public List<PersonDTO> getPersonsByCityInfo(int zipcode) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select p from Person p where p.address.cityinfo.ZipCode =:zipcode", Person.class)
                    .setParameter("zipcode", zipcode);
            
           
            List<Person> persons = q.getResultList();
            List<PersonDTO> pDTOs = new ArrayList<>();
            for (Person person : persons) {
                pDTOs.add(new PersonDTO(person));
            }
            
            
            
          
            return pDTOs;
        } finally {
            em.close();
        }
    }
    
}
