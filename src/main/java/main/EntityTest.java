/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
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
        
        CityInfo askeby = new CityInfo(2450, "Sydhavn");
        
        
        Person aske = pf.addPerson("aske@thorsen.dk", "Aske", "Thorsen");
        Address askeaddress = new Address("Thorsengade", "nede på hjørnet");
        askeaddress.setCityinfo(askeby);
        
        Phone askehjem = new Phone("42131388", "Hjem");
        Phone askearbejde = new Phone("123585885", "Arbejde");
        aske.addPhones(askehjem);
        aske.addPhones(askearbejde);
        
        Hobby askehobby = new Hobby("Ridning", "Det er sjovt");
        Hobby askehobby2 = new Hobby("Rollespil", "Det er i den mørke skov");
        
        
        
        pf.addPhone(aske);
        
        
        pf.addAddress(aske, askeaddress);
        
    }
    
}
