package facades;

import dto.AddressDTO;
import dto.CityInfoDTO;
import dto.PersonDTO;
import dto.PhonesDTO;
import entities.Person;
import entities.Phone;
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

    public PersonDTO getPersonByPhoneNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Phone> query = em.createQuery("SELECT c FROM Phone c where c.number like :number", Phone.class)
                    .setParameter("number", number)
                    .getResultList();
            Person p = em.find(Person.class, (long) query.get(0).getId());

            return new PersonDTO(
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

}
