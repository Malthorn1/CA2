package facades;

import dto.AddressDTO;
import dto.CityInfoDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import dto.PhonesDTO;
import entities.Person;
import entities.Phone;
import exceptions.PhoneNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PhoneFacade {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public PhoneFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PhoneFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getPhoneCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long personCount = (long) em.createQuery("SELECT COUNT(p) FROM Phone p").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }

    }

    public PersonDTO getPersonByPhoneNumber(String number) throws PhoneNotFoundException{
        EntityManager em = emf.createEntityManager();
        try {
            List<Phone> query = em.createQuery("SELECT c FROM Phone c where c.number like :number", Phone.class)
                    .setParameter("number", number)
                    .getResultList();
            if(query.isEmpty()){
                throw new PhoneNotFoundException("No entries found on that phone number");
            }
            Person p = em.find(Person.class, (long) query.get(0).getId());
            

            return new PersonDTO(
                    p.getId(),
                    p.getEmail(),
                    p.getFirstName(),
                    p.getLastName(),
                    new AddressDTO(p.getAddress().getStreet(), p.getAddress().getAdditionalInfo(),
                            new CityInfoDTO(p.getAddress().getCityinfo().getZipCode(), p.getAddress().getCityinfo().getCity())),
                    new PhonesDTO(p.getPhones()));
        } finally {
            em.close();
        }
    }

    public PhonesDTO editPhones(List<PhoneDTO> pDTOs, int id) throws PhoneNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, (long) id);
            if(p == null){
                throw new PhoneNotFoundException("No person with provided ID");
            }
            List<Phone> phones = p.getPhones();
            
            for (int i = 0; i < phones.size(); i++) {
                p.getPhones().get(i).setNumber(pDTOs.get(i).getNumber());
                p.getPhones().get(i).setDescription(pDTOs.get(i).getDescription());
            }
            
            
            em.getTransaction().commit();

            return new PhonesDTO(phones);

        } finally {
            em.close();
        }
    }

}
