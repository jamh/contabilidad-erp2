/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeImpRetencionesId implements java.io.Serializable{
    
     @JsonProperty("COMPANIA")
    @Column (name = "COMPANIA")
    private String compania;
   
    @JsonProperty("NUMERO")
    @Column (name = "NUMERO")
    private int numero;
    
      @JsonProperty("SEC")
    @Column (name = "SEC")
    private int sec;
    
    public ErpFeImpRetencionesId(){
    
    }
    
    public ErpFeImpRetencionesId(String compania, int numero, int sec){
        
        this.compania = compania;
        this.numero = numero;
        this.sec = sec;
    
    }

    public String getCompania() {
        return compania;
    }

    public int getNumero() {
        return numero;
    }

    public int getSec() {
        return sec;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
    
    
    
}
