/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.AddressFacade;
import facades.PersonFacade;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author casper
 */
public class EntityTest {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
        PersonFacade pf = PersonFacade.getFacadeExample(emf);
        
        AddressFacade af = new AddressFacade();
        
        Person aske = new Person("aske@punani.dk", "Aske", "Thorsen");
        Address askeaddress = new Address("Punanigdade", "nede på hjørnet");
        Phone askephone = new Phone(42131388, "Hjem");
        Hobby askehobby = new Hobby("Ridning", "Det er sjovt");
        Hobby askehobby2 = new Hobby("Rollespil", "Det er i den mørke skov");
        pf.addPhone(aske, askephone);
//        pf.addHobby(aske, askehobby);
//        pf.addHobby(aske, askehobby2);
        pf.addAddress(aske, askeaddress);
        pf.addPerson(aske);
        
    }
    
}
