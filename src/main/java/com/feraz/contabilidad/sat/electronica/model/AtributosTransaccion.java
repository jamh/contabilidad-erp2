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
public class AtributosTransaccion implements java.io.Serializable{
    
        @JsonProperty("NUMCTA")
     @Column(name = "NUMCTA")
     private String numCtaTransaccion;
        
     @JsonProperty("CONCEPTO")
     @Column(name = "CONCEPTO")
     private String conceptoTransaccion;
     
     @JsonProperty("DEBE")
     @Column(name = "DEBE")
     private BigDecimal debeTransaccion;
     
     @JsonProperty("HABER")
     @Column(name = "HABER")
     private BigDecimal haberTransaccion;
     
     @JsonProperty("MONEDA")
     @Column(name = "MONEDA")
     private String monedaTransaccion;
     
     @JsonProperty("TIPCAMB")
     @Column(name = "TIPCAMB")
     private BigDecimal tipCambTransaccion;
     
     
    
    public AtributosTransaccion(){
    }
    
    public AtributosTransaccion(String numCtaTransaccion, String conceptoTransaccion, BigDecimal debeTransaccion, BigDecimal haberTransaccion, String monedaTransaccion, BigDecimal tipCambTransaccion){
        
        this.numCtaTransaccion = numCtaTransaccion;
        this.conceptoTransaccion = conceptoTransaccion;
        this.debeTransaccion = debeTransaccion;
        this.haberTransaccion = haberTransaccion;
        this.monedaTransaccion = monedaTransaccion;
        this.tipCambTransaccion = tipCambTransaccion;
       
    
    }

    public String getNumCtaTransaccion() {
        return numCtaTransaccion;
    }

    public String getConceptoTransaccion() {
        return conceptoTransaccion;
    }

    public BigDecimal getDebeTransaccion() {
        return debeTransaccion;
    }

    public BigDecimal getHaberTransaccion() {
        return haberTransaccion;
    }

    public String getMonedaTransaccion() {
        return monedaTransaccion;
    }

    public BigDecimal getTipCambTransaccion() {
        return tipCambTransaccion;
    }

    public void setNumCtaTransaccion(String numCtaTransaccion) {
        this.numCtaTransaccion = numCtaTransaccion;
    }

    public void setConceptoTransaccion(String conceptoTransaccion) {
        this.conceptoTransaccion = conceptoTransaccion;
    }

    public void setDebeTransaccion(BigDecimal debeTransaccion) {
        this.debeTransaccion = debeTransaccion;
    }

    public void setHaberTransaccion(BigDecimal haberTransaccion) {
        this.haberTransaccion = haberTransaccion;
    }

    public void setMonedaTransaccion(String monedaTransaccion) {
        this.monedaTransaccion = monedaTransaccion;
    }

    public void setTipCambTransaccion(BigDecimal tipCambTransaccion) {
        this.tipCambTransaccion = tipCambTransaccion;
    }
    
    
    
}
