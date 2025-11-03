/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

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
public class AtributosCheque implements java.io.Serializable{
    
     @JsonProperty("NUM")
    @Column(name = "NUM")
    private String numCheque;
     
      @JsonProperty("BANCO")
    @Column(name = "BANCO")
    private String bancoCheque;
      
      @JsonProperty("CTAORI")
    @Column(name = "CTAORI")
    private String ctaOriCheque;
      
     @JsonProperty("FECHA")
    @Column(name = "FECHA")
    private Date fechaCheque;
     
       @JsonProperty("MONTO")
    @Column(name = "MONTO")
    private BigDecimal montoCheque;
       
       @JsonProperty("BENEF")
    @Column(name = "BENEF")
    private String benefCheque;
   
    @JsonProperty("RFC")
    @Column(name = "RFC")
    private String rfcCheque;
    
    public AtributosCheque(){
    }
    
    public AtributosCheque(String numCheque,String bancoCheque, String ctaOriCheque,Date fechaCheque, BigDecimal montoCheque, String benefCheque, String rfcCheque){
        
        this.numCheque = numCheque;
        this.bancoCheque = bancoCheque;
        this.ctaOriCheque = ctaOriCheque;
        this.fechaCheque = fechaCheque;
        this.montoCheque = montoCheque;
        this.benefCheque = benefCheque;
        this.rfcCheque = rfcCheque;
        
    
    }

    public String getNumCheque() {
        return numCheque;
    }

    public String getBancoCheque() {
        return bancoCheque;
    }

    public String getCtaOriCheque() {
        return ctaOriCheque;
    }

    public Date getFechaCheque() {
        return fechaCheque;
    }

    public BigDecimal getMontoCheque() {
        return montoCheque;
    }

    public String getBenefCheque() {
        return benefCheque;
    }

    public String getRfcCheque() {
        return rfcCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public void setBancoCheque(String bancoCheque) {
        this.bancoCheque = bancoCheque;
    }

    public void setCtaOriCheque(String ctaOriCheque) {
        this.ctaOriCheque = ctaOriCheque;
    }

    public void setFechaCheque(Date fechaCheque) {
        this.fechaCheque = fechaCheque;
    }

    public void setMontoCheque(BigDecimal montoCheque) {
        this.montoCheque = montoCheque;
    }

    public void setBenefCheque(String benefCheque) {
        this.benefCheque = benefCheque;
    }

    public void setRfcCheque(String rfcCheque) {
        this.rfcCheque = rfcCheque;
    }
    
    
    
}
