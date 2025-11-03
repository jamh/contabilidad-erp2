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
public class EstructurasId implements java.io.Serializable{
    
      @JsonProperty("ESTRUCTURA")
    @Column(name = "ESTRUCTURA")
    private String estructura;
  
     public EstructurasId(){
    
    }
    
    public EstructurasId(String estructura){
        
        this.estructura = estructura;
        
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }
    
    
    
}
