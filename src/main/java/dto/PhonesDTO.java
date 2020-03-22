/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casper
 */
public class PhonesDTO {
    
    List<PhoneDTO> phones = new ArrayList<>();
    private int pDTOID;
    
    public PhonesDTO(List<Phone> phoneEntities){
        for (Phone phoneEntity : phoneEntities) {
            phones.add(new PhoneDTO(phoneEntity.getNumber(), phoneEntity.getDescription()));
        }
        this.pDTOID = pDTOID;
    }

    public List<PhoneDTO> getAll() {
        return phones;
    }

    public int getpDTOID() {
        return pDTOID;
    }

    public void setpDTOID(int pDTOID) {
        this.pDTOID = pDTOID;
    }
    
    
    
}
