package facades;

import dto.PersonDTO;
import entities.Person;
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
            Query q = em.createNativeQuery("select firstName \n"
                    + "from PERSON  \n"
                    + "inner join ADDRESS on PERSON.ADDRESS_ID = ADDRESS.ID\n"
                    + "inner join CITYINFO on ADDRESS.CITYINFO_ID = CITYINFO.ID\n"
                    + "where CITYINFO.ZIPCODE = '"+zipcode+"';", Person.class);
            List<PersonDTO> persons = q.getResultList();
            return persons;
        } finally {
            em.close();
        }
    }
    
}
