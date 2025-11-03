/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.model;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class CompaniaId implements java.io.Serializable{
    
     @JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;
     
      public CompaniaId(){
    
    }
    
    public CompaniaId(String compania){
        
        this.compania = compania;
        
       
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }
    
    
    
}
