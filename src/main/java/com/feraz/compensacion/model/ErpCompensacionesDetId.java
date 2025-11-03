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
public class ErpCompensacionesDetId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
     
     @Column(name = "ID")
    private Integer id;
     
     @Column(name = "ID_DOCUMENTO")
    private Integer idDocumento;
     
     @Column(name = "ORIGEN")
    private String origen;
     
     public ErpCompensacionesDetId(){
     
     }
     
     public ErpCompensacionesDetId(String compania,Integer id, Integer idDocumento,String origen){
         
         this.compania =compania;
         this.id = id;
         this.idDocumento = idDocumento;
         this.origen = origen;
         
         
     
     
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

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
     
     
     
    
    
}
