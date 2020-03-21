/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casper
 */
public class PersonsDTO {

    List<PersonDTO> all = new ArrayList<>();

    public PersonsDTO(List<Person> personEntities) {
        for (Person personEntity : personEntities) {
            all.add(new PersonDTO(personEntity.getId(),
                    personEntity.getEmail(),
                    personEntity.getFirstName(),
                    personEntity.getLastName(),               
                    new AddressDTO(
                            personEntity.getAddress().getStreet(),
                            personEntity.getAddress().getAdditionalInfo(),
                            new CityInfoDTO(personEntity.getAddress().getCityinfo().getZipCode(),
                                            personEntity.getAddress().getCityinfo().getCity())),
                    new PhonesDTO(personEntity.getPhones())));
        }
    }

    public List<PersonDTO> getAll() {
        return all;
    }

}
