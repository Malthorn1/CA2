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
        CityInfo Jensby = new CityInfo(2800, "Lyngby");        
        
        Person aske = new Person("aske@thorsen.dk", "Aske", "Thorsen");
        Person jens = new Person("jens@derp.dk", "Jens", "ohhlends"); 
        
        pf.addPerson(aske.getEmail(), aske.getFirstName(), aske.getLastName()) ; 
        pf.addPerson(jens.getEmail(), jens.getFirstName(), jens.getLastName()); 
        
        Address askeaddress = new Address("Thorsengade", "nede på hjørnet");
        Address Jensaddress = new Address("Nybrovej", "Stuen A");
        askeaddress.setCityinfo(askeby);
        Jensaddress.setCityinfo(Jensby);
        
        Phone askehjem = new Phone("42131388", "Hjem");
        Phone askearbejde = new Phone("123585885", "Arbejde");
        Phone Jenshjem = new Phone("88888", "HHome");
        Phone Jensarbejde = new Phone("9999999", "WORK");
        
        jens.addPhones(Jenshjem);
        jens.addPhones(Jensarbejde);
        aske.addPhones(askehjem);
        aske.addPhones(askearbejde);
        
        Hobby askehobby = new Hobby("Ridning", "Det er sjovt");
        Hobby askehobby2 = new Hobby("Rollespil", "Det er i den mørke skov");
        
        Hobby jenshobby = new Hobby("ComputerSpil", "fun");
        Hobby jenshobby2 = new Hobby("DDDD", "kkkk");
        
        
        
        
        
        pf.addAddress(aske, askeaddress);
        pf.addAddress(jens, Jensaddress); 
        
    }
    
}
