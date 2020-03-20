/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import entities.Address;



/**
 *
 * @author casper
 */
public class AddressDTO {
    
    private String street;
    private String additionalInfo;
    private CityInfoDTO cityInfoDTO;
    

    public AddressDTO(String street, String additionalInfo, CityInfoDTO cityInfoDTO) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfoDTO = cityInfoDTO;
    }
    
//    public AddressDTO(String street, String additionalInfo){
//        
//        this.street = street;
//        this.additionalInfo = additionalInfo
//        
//        
//    }
    
    
    public AddressDTO(){}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    
    
    
}