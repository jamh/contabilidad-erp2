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
public class AtributosBalanza implements java.io.Serializable {
    
    @JsonProperty("NIVEL")
    @Column(name = "NIVEL")
    private String nivelBalanza;
    
    @JsonProperty("RFC")
    @Column(name = "RFC")
    private String rfcBalanza;
    
    @JsonProperty("TOTALCTAS")
    @Column(name = "TOTALCTAS")
    private int totalCtasBalanza;
    
    @JsonProperty("MES")
    @Column(name = "MES")
    private String mesBalanza;
    
    @JsonProperty("ANO")
    @Column(name = "ANO")
    private int anoBalanza;
    
    public AtributosBalanza(){
    }
    
    public AtributosBalanza(String nivelBalanza, String rfcBalanza, int totalCtasBalanza, String mesBalanza, int anoBalanza){
        
        this.nivelBalanza = nivelBalanza;
        this.rfcBalanza = rfcBalanza;
        this.totalCtasBalanza = totalCtasBalanza;
        this.mesBalanza = mesBalanza;
        this.anoBalanza = anoBalanza;
        
    
    }

    public void setNivelBalanza(String nivelBalanza) {
        this.nivelBalanza = nivelBalanza;
    }

    public void setRfcBalanza(String rfcBalanza) {
        this.rfcBalanza = rfcBalanza;
    }

    public void setTotalCtasBalanza(int totalCtasBalanza) {
        this.totalCtasBalanza = totalCtasBalanza;
    }

    public void setMesBalanza(String mesBalanza) {
        this.mesBalanza = mesBalanza;
    }

    public void setAnoBalanza(int anoBalanza) {
        this.anoBalanza = anoBalanza;
    }

    public String getNivelBalanza() {
        return nivelBalanza;
    }

    public String getRfcBalanza() {
        return rfcBalanza;
    }

    public int getTotalCtasBalanza() {
        return totalCtasBalanza;
    }

    public String getMesBalanza() {
        return mesBalanza;
    }

    public int getAnoBalanza() {
        return anoBalanza;
    }
    
    
}
