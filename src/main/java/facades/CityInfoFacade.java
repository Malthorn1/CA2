package facades;

import dto.AddressDTO;
import dto.CityInfoDTO;
import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import exceptions.CityInfoNotFoundException;
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

    public List<PersonDTO> getPersonsByCityInfo(int zipcode) throws CityInfoNotFoundException{
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select p from Person p where p.address.cityinfo.ZipCode =:zipcode", Person.class)
                    .setParameter("zipcode", zipcode);
            

            List<Person> persons = q.getResultList();
            if(persons.isEmpty()){
                throw new CityInfoNotFoundException("No person found  with provided ZipCode");
                
            }
            List<PersonDTO> pDTOs = new ArrayList<>();
            for (Person person : persons) {
                pDTOs.add(new PersonDTO(person));
            }

            return pDTOs;
        } finally {
            em.close();
        }
    }
    
    public CityInfoDTO AddCityInfo(int zipcode, String city, int id){
         EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, (long)id);
            CityInfo cityInfo = new CityInfo(zipcode, city);
            Address a = em.find(Address.class, (long)id);
            p.setAddress(a);
        p.getAddress().setCityinfo(cityInfo);
          em.persist(p); 
        em.getTransaction().commit();
        
        return  new CityInfoDTO(cityInfo.getZipCode(), cityInfo.getCity(), id);
        
        }finally{
            em.close();
        }
    }
    public CityInfoDTO editCityInfo(CityInfoDTO cityInfoDTO, int id) throws CityInfoNotFoundException{
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, (long)id);
            if(p == null){
                throw new CityInfoNotFoundException("Could not find any person to edit CityInfo on with specified ID");
            }
            
            CityInfo cityInfo  = new CityInfo(cityInfoDTO.getZipCode(), cityInfoDTO.getCity());
            
            p.getAddress().setCityinfo(cityInfo);
            
            
            em.getTransaction().commit();
            return new CityInfoDTO(p.getAddress().getCityinfo().getZipCode(), p.getAddress().getCityinfo().getCity());
        }finally{
            em.close();
        }
        
        
    }

}
