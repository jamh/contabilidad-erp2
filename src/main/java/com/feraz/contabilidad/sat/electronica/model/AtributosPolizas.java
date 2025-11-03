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
public class AtributosPolizas implements java.io.Serializable {
    
    
     @JsonProperty("VERSION")
    @Column(name = "VERSION")
    private String versionPolizas;
     
     @JsonProperty("RFC")
     @Column(name = "RFC")
     private String rfcPolizas;
     
     @JsonProperty("MES")
     @Column(name = "MES")
     private String mesPolizas;
     
     @JsonProperty("ANO")
     @Column(name = "ANO")
     private int anoPolizas;
    
    public AtributosPolizas(){
    }
    
    public AtributosPolizas(String versionPolizas, String rfcPolizas, String mesPolizas, int anoPolizas){
        
        this.versionPolizas = versionPolizas;
        this.rfcPolizas = rfcPolizas;
        this.mesPolizas = mesPolizas;
        this.anoPolizas = anoPolizas;
       
    
    }

    public void setVersionPolizas(String versionPolizas) {
        this.versionPolizas = versionPolizas;
    }

    public void setRfcPolizas(String rfcPolizas) {
        this.rfcPolizas = rfcPolizas;
    }

    public void setMesPolizas(String mesPolizas) {
        this.mesPolizas = mesPolizas;
    }

    public void setAnoPolizas(int anoPolizas) {
        this.anoPolizas = anoPolizas;
    }

    public String getVersionPolizas() {
        return versionPolizas;
    }

    public String getRfcPolizas() {
        return rfcPolizas;
    }

    public String getMesPolizas() {
        return mesPolizas;
    }

    public int getAnoPolizas() {
        return anoPolizas;
    }

        
    
}
