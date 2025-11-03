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
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeConceptoXRetencionId implements java.io.Serializable{
    
     @JsonProperty("COMPANIA")
    @Column (name = "COMPANIA")
    private String compania;
     
      @JsonProperty("NUMERO")
    @Column (name = "NUMERO")
    private int numero;
      
      @JsonProperty("ID_CONCEPTO")
    @Column (name = "ID_CONCEPTO")
    private int idConcepto;
    
     @JsonProperty("SEC")
    @Column (name = "SEC")
    private int sec;
     
     
     public ErpFeConceptoXRetencionId(){
     
     }
     
     public ErpFeConceptoXRetencionId(String compania, int numero, int idConcepto, int sec){
     
          this.compania = compania;
          this.numero = numero;
          this.idConcepto = idConcepto;
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

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
     
    
}
