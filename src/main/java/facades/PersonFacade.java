package facades;

import dto.AddressDTO;
import dto.CityInfoDTO;
import dto.PersonDTO;
import dto.PersonsDTO;
import dto.PhoneDTO;
import dto.PhonesDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exceptions.PersonNotFoundException;
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
    
    public PersonDTO getPerson(int id) throws PersonNotFoundException{
        EntityManager em = emf.createEntityManager();
        try{
            Person p = em.find(Person.class, (long)id);
            if(p  == null){
                throw new PersonNotFoundException("No person found with that ID");
            }
            return new PersonDTO(
                    p.getId(),
                    p.getEmail(),
                    p.getFirstName(),
                    p.getLastName(),
                    new AddressDTO(p.getAddress().getStreet(), p.getAddress().getAdditionalInfo(),
                            new CityInfoDTO(p.getAddress().getCityinfo().getZipCode(), p.getAddress().getCityinfo().getCity())),
                    new PhonesDTO(p.getPhones()));
        }finally{
            em.close();
        }
    }
    
    public PersonDTO addPerson(String email, String firstName, String lastName){
        Person p = new Person(email, firstName, lastName);
        
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            PersonDTO pdto = new PersonDTO(p.getEmail(), p.getFirstName(), p.getLastName(), p.getId());
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
    
    public Person addPhone(Person person){
        EntityManager em = emf.createEntityManager();
        //person.setPhones(phones);
        
        try{
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return person;
    }
    
    public PersonDTO editPerson(PersonDTO p, int value) throws PersonNotFoundException{
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Person person = em.find(Person.class, (long)value);
            if(person == null){
                throw new PersonNotFoundException("No person found with specified ID");
                
            }
            
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            person.setEmail(p.getEmail());
            
            
            em.getTransaction().commit();

            return new PersonDTO(person);
        } finally {
            em.close();
        }
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
