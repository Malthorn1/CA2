package facades;

import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
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
public class CityInfoFacade {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    public CityInfoFacade() {}
    
    
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
    public long getZipCodeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(a) FROM Zipcode a").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
        
    }

    public List<PersonDTO> getPersonsByZipCode(int ZipCode) {
        EntityManager em = emf.createEntityManager(); 
        try {
           List<CityInfo> query =  em.createQuery("SELECT c FROM Person c where c.ZipCode like :zipcode", CityInfo.class ) 
                    .setParameter("zipcode", ZipCode)
                    .getResultList() ;
           
            
            

            List<PersonDTO> pDTO = new ArrayList<>();
//            for (Person person : persons) {
//                PersonDTO pdto = new PersonDTO(person);
//                pDTO.add(pdto);
           // }
            return pDTO;
            
            
            
        }finally {
            em.close();
        }
    }
    
    

}
