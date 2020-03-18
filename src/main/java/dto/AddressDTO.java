/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;

/**
 *
 * @author jenso
 */
class AddressDTO {
    private String street;
    private String additionalInfo;
    
    public AddressDTO(Address a){
        this.additionalInfo = a.getAdditionalInfo();
        this.street = a.getStreet();
        
    }
}
