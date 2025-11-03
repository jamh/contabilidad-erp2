/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_NOMINA_EMISOR")
public class ErpFeNominaEmisor implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeNominaEmisorId id;
    
     @Column (name = "REGISTRO_PATRONAL")
     private String registroPatronal;
     
     public ErpFeNominaEmisor(){
     
     }
     
     public ErpFeNominaEmisor(ErpFeNominaEmisorId id){
     
         this.id=id;
     
     }

    public ErpFeNominaEmisorId getId() {
        return id;
    }

    public void setId(ErpFeNominaEmisorId id) {
        this.id = id;
    }

    public String getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(String registroPatronal) {
        this.registroPatronal = registroPatronal;
    }
     
     
    
}
