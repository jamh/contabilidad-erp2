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
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "COMPANIAS")

public class Compania implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
       
    })
    
     @JsonProperty("ID2")
    private CompaniaId id;
    
    @JsonProperty("ESTRUCTURA")
    @Column(name = "ESTRUCTURA")
    private String estructura;
    
    public Compania(){
    
    }
    
    public Compania(CompaniaId id){
        
        this.id = id;
        
    }

    public CompaniaId getId() {
        return id;
    }

    public void setId(CompaniaId id) {
        this.id = id;
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }
    
    
    
    
    
}
