package entities;


import dto.AddressDTO;
import dto.PhonesDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;






@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    @OneToMany(mappedBy="person", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Phone> phones = new ArrayList<>();
    //@ManyToMany(cascade = CascadeType.PERSIST)
    //private Set<Hobby> hobbies;
    
    
    

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
     public Person(String email, String firstName, String lastName, Long id) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    
    
    
    public Person() {
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhones(Phone phone) {
        this.phones.add(phone);
        phone.setPerson(this);
    }
    public void setPhones(List<Phone> phones){
        this.phones = phones;
        for (Phone phone : phones) {
            phone.setPerson(this);
        }
    }

//    public Set<Hobby> getHobbies() {
//        return hobbies;
//    }
//
//    
//    public void addHobby(Hobby hobby){
//        this.hobbies.add(hobby);
//        
//    }
    
    
    

   
}
