/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.CityInfo;

/**
 *
 * @author casper
 */
public class CityInfoDTO {
    
    private int ZipCode;
    private String city;
    private int cDTOID;
    

    public CityInfoDTO(int ZipCode, String city) {
        this.ZipCode = ZipCode;
        this.city = city;
    }

    public CityInfoDTO(int ZipCode, String city, int cDTOID) {
        this.ZipCode = ZipCode;
        this.city = city;
        this.cDTOID = cDTOID;
    }

    public int getcDTOID() {
        return cDTOID;
    }

    public void setcDTOID(int cDTOID) {
        this.cDTOID = cDTOID;
    }
    
    

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
}
