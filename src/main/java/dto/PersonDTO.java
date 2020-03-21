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
    private AddressDTO addressDTO;
    private PhonesDTO phoneDTO;
    
    public PersonDTO(Person person, AddressDTO addressDTO, PhonesDTO phoneDTO) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.addressDTO = addressDTO;
        this.lastName = person.getLastName();
        this.phoneDTO = phoneDTO;
        this.id = person.getId();
        
    }
    
    public PersonDTO(Person person){
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    public PersonDTO(Long id, String email, String firstName, String lastName, AddressDTO addressDTO, PhonesDTO phonesDTO) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressDTO = addressDTO;
        this.phoneDTO = phonesDTO;
        this.id = id;
        
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

    public PhonesDTO getPhoneDTO() {
        return phoneDTO;
    }

    public void setPhoneDTO(PhonesDTO phoneDTO) {
        this.phoneDTO = phoneDTO;
    }

   

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

   
    
    
    
    
    
    
}
