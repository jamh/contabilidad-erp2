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
//@Entity
//@Table(name = "DET_POLIZAS")
public class Polizas implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable= false, length = 25 ))
        
    })
    
    @JsonProperty("ID")
    private PolizasId id;
    
    @JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JsonProperty("ATRIBUTOS")
    @Column(name = "ATRIBUTOS")
    private AtributosPolizas atributosPolizas;
    
    public Polizas(){
    }
    
    public Polizas(PolizasId id){
        
        this.id = id;
    
    }

    public void setId(PolizasId id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAtributosPolizas(AtributosPolizas atributosPolizas) {
        this.atributosPolizas = atributosPolizas;
    }

    public PolizasId getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public AtributosPolizas getAtributosPolizas() {
        return atributosPolizas;
    }
    
    
    
}
