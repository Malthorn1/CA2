package facades;

import dto.AddressDTO;
import dto.PersonDTO;
import entities.Address;
import entities.Person;
import exceptions.AddressNotFoundException;
import exceptions.PersonNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sun.jvm.hotspot.debugger.AddressException;

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
    
    
    public AddressDTO addAddress(String street, String additionalInfo, int aDTOID){
        AddressDTO aDTO = new AddressDTO(street, additionalInfo, aDTOID);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, (long)aDTOID);
            
            Address pAddress = new Address(aDTO.getStreet(), aDTO.getAdditionalInfo());
            p.setAddress(pAddress);
            
            em.persist(pAddress);
            em.getTransaction().commit();
            return new AddressDTO(pAddress.getStreet(), pAddress.getAdditionalInfo(), aDTOID);
        }finally{
            em.close();
        }
    }
    
    public AddressDTO editAddress(AddressDTO addressDTO) throws AddressNotFoundException{
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, (long)addressDTO.getpDTOID());
            if(p == null){
                throw new AddressNotFoundException("No person with provided ID");
            }
            
            
            
            p.getAddress().setAdditionalInfo(addressDTO.getAdditionalInfo());
            p.getAddress().setStreet(addressDTO.getStreet());
            
            em.getTransaction().commit();
            return new AddressDTO(addressDTO.getStreet(), addressDTO.getAdditionalInfo());
        }finally{
            em.close();
        }
        
        
    }

}
