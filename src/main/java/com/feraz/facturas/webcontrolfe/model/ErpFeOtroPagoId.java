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
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeOtroPagoId implements java.io.Serializable{
    
     @JsonProperty("COMPANIA")
    @Column (name = "COMPANIA")
    private String compania;
   
    @JsonProperty("NUMERO")
    @Column (name = "NUMERO")
    private int numero;
    
    @JsonProperty("SEC")
    @Column (name = "SEC")
    private int sec;
    
    public ErpFeOtroPagoId(){
    
    }
    
    public ErpFeOtroPagoId(String compania,int numero,int sec){
        
        this.compania = compania;
        this.numero = numero;
        this.sec = sec;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
    
    
    
    
}
