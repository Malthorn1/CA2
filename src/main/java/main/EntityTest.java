/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Address;
import entities.Person;
import facades.AddressFacade;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        pf.addAddress(aske, askeaddress);
        pf.addPerson(aske);
        
    }
    
}
