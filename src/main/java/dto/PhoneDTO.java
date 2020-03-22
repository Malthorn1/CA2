/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;

/**
 *
 * @author casper
 */
public class PhoneDTO {
    
    private String number;
    private String description;
    private int pDTOID;
    
    public PhoneDTO(Phone phone) {
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }
    
    public PhoneDTO(String number, String description) {
        this.number = number;
        this.description = description;
    }
    
        public PhoneDTO(String number, String description, int pDTOID) {
        this.number = number;
        this.description = description;
        this.pDTOID = pDTOID;
    }
    
    public PhoneDTO(){}

    public String getNumber() {
        return number;
    }

    public int getpDTOID() {
        return pDTOID;
    }

    public void setpDTOID(int pDTOID) {
        this.pDTOID = pDTOID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}