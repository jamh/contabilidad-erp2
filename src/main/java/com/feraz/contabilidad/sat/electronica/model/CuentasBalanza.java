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
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;

@JsonIgnoreProperties(ignoreUnknown = true)
//@Entity
//@Table(name = "DET_POLIZAS")
public class CuentasBalanza implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable= false, length = 25 ))
        
    })
    
    @JsonProperty("ID")
    private CuentasBalanzaId id;
    
    @JsonProperty("NUMCTA")
    @Column(name = "NUMCTA")
    private String numCtaBalanza;
    
    @JsonProperty("SALDOINI")
    @Column(name = "SALDOINI")
    private BigDecimal saldoIniBalanza;
    
    @JsonProperty("DEBE")
    @Column(name = "DEBE")
    private BigDecimal debeBalanza;
    
    @JsonProperty("HABER")
    @Column(name = "HABER")
    private BigDecimal haberBalanza;
    
    @JsonProperty("SALDOFIN")
    @Column(name = "SALDOFIN")
    private BigDecimal saldoFin;
    
    
    
     public CuentasBalanza(){
    }
    
    public CuentasBalanza(CuentasBalanzaId id){
        
        this.id = id;
    
    }

    public CuentasBalanzaId getId() {
        return id;
    }

    public String getNumCtaBalanza() {
        return numCtaBalanza;
    }

    public BigDecimal getSaldoIniBalanza() {
        return saldoIniBalanza;
    }

    public BigDecimal getDebeBalanza() {
        return debeBalanza;
    }

    public BigDecimal getHaberBalanza() {
        return haberBalanza;
    }

    public BigDecimal getSaldoFin() {
        return saldoFin;
    }

    public void setId(CuentasBalanzaId id) {
        this.id = id;
    }

    public void setNumCtaBalanza(String numCtaBalanza) {
        this.numCtaBalanza = numCtaBalanza;
    }

    public void setSaldoIniBalanza(BigDecimal saldoIniBalanza) {
        this.saldoIniBalanza = saldoIniBalanza;
    }

    public void setDebeBalanza(BigDecimal debeBalanza) {
        this.debeBalanza = debeBalanza;
    }

    public void setHaberBalanza(BigDecimal haberBalanza) {
        this.haberBalanza = haberBalanza;
    }

    public void setSaldoFin(BigDecimal saldoFin) {
        this.saldoFin = saldoFin;
    }
    
    
    
}
