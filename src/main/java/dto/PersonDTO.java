/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | TemplatesNewClass
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.Person;
import entities.Phone;
import java.util.List;

/**
 *
 * @author casper
 */
public class PersonDTO {
    
    private String email;
    private String firstName;
    private String lastName;
    private long id;
    private Address address;
    private List<Phone> phones;
    public PersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.id = person.getId();
      //  this.address = person.getAddress();
        this.phones = person.getPhones();
    }
    
    public PersonDTO(){}

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

    public Long getId() {
        return id;
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

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    
    
    
    
    
    
    
}
