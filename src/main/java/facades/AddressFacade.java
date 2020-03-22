package facades;

import dto.AddressDTO;
import dto.PersonDTO;
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
    
    
    public PersonDTO addAddress(String street, String additionalInfo, long aDTOID){
        AddressDTO aDTO = new AddressDTO(street, additionalInfo, aDTOID);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, aDTOID);
            Address pAddress = new Address(aDTO.getStreet(), aDTO.getAdditionalInfo());
            p.setAddress(pAddress);
            
            em.persist(pAddress);
            em.getTransaction().commit();
            return new PersonDTO(p);
        }finally{
            em.close();
        }
    }
    
    public PersonDTO editAddress(AddressDTO addressDTO){
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, addressDTO.getpDTOID());
            Address pAddress = new Address(addressDTO.getStreet(), addressDTO.getAdditionalInfo());
            p.setAddress(pAddress);
            
            em.getTransaction().commit();
            return new PersonDTO(p);
        }finally{
            em.close();
        }
        
        
    }

}
