/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;

/**
 *
 * @author jenso
 */
class PhoneDTO {
    private int number;
    private String description;
    
    
    public PhoneDTO(Phone p){
        this.number = p.getNumber();
        this.description = p.getDescription();
        
    }
}
