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
public class AtributosDetPolizas implements java.io.Serializable{
    
     @JsonProperty("TIPO")
     @Column(name = "TIPO")
     private int tipoPolizas;
     
     @JsonProperty("NUM")
     @Column(name = "NUM")
     private String numPoliza;
     
     @JsonProperty("FECHA")
     @Column(name = "FECHA")
     private Date fechaPoliza;
     
     @JsonProperty("CONCEPTO")
     @Column(name = "CONCEPTO")
     private String conceptoPoliza;
     
    
    public AtributosDetPolizas(){
    }
    
    public AtributosDetPolizas(int tipoPolizas, String numPoliza, Date fechaPoliza, String conceptoPoliza){
        
        this.tipoPolizas = tipoPolizas;
        this.numPoliza = numPoliza;
        this.fechaPoliza = fechaPoliza;
        this.conceptoPoliza = conceptoPoliza;
       
    }

    public void setTipoPolizas(int tipoPolizas) {
        this.tipoPolizas = tipoPolizas;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    public void setFechaPoliza(Date fechaPoliza) {
        this.fechaPoliza = fechaPoliza;
    }

    public void setConceptoPoliza(String conceptoPoliza) {
        this.conceptoPoliza = conceptoPoliza;
    }

    public int getTipoPolizas() {
        return tipoPolizas;
    }

    public String getNumPoliza() {
        return numPoliza;
    }

    public Date getFechaPoliza() {
        return fechaPoliza;
    }

    public String getConceptoPoliza() {
        return conceptoPoliza;
    }
    
    
    
}
