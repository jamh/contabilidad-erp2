/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.model;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpCompensacionesId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
     
     @Column(name = "ID")
    private Integer id;
     
     public ErpCompensacionesId(){
     
     }
     
     public ErpCompensacionesId(String compania,Integer id){
         
         
         this.compania = compania;
         this.id = id;
     
     
     }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     
     
    
}
