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
    private String street; 
    private String additionalInfo; 
    private String city; 
    private int zipCode; 
    private List<Phone> phones;
    
    
    public PersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.id = person.getId();
        this.street = person.getAddress().getStreet();
        this.additionalInfo = person.getAddress().getAdditionalInfo(); 
        this.city = person.getAddress().getCityinfo().getCity();
        this.zipCode = person.getAddress().getCityinfo().getZipCode();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 
    

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    
    
    
    
    
    
    
    
}
