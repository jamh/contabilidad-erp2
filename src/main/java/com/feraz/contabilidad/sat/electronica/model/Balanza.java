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
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;

@JsonIgnoreProperties(ignoreUnknown = true)
//@Entity
//@Table(name = "DET_POLIZAS")
public class Balanza implements java.io.Serializable {
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable= false, length = 25 ))
        
    })
    
    @JsonProperty("ID")
    private BalanzaId id;
    
    @JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JsonProperty("ATRIBUTOS")
    @Column(name = "ATRIBUTOS")
    private AtributosBalanza atributosBalanza;
    
    public Balanza(){
    }
    
    public Balanza(BalanzaId id){
        
        this.id = id;
    
    }

    public BalanzaId getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public AtributosBalanza getAtributosBalanza() {
        return atributosBalanza;
    }

    public void setId(BalanzaId id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAtributosBalanza(AtributosBalanza atributosBalanza) {
        this.atributosBalanza = atributosBalanza;
    }
    
    
}
