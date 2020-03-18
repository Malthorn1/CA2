/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;

/**
 *
 * @author mikke
 */
public class AddressDTO {
   private String Street; 
   private String additionalInfo; 
   private int ZipCode; 
   private String City; 

    public AddressDTO(Address address) {
        this.Street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.ZipCode = address.getCityinfo().getZipCode();
        this.City = address.getCityinfo().getCity();
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
   
   
}
