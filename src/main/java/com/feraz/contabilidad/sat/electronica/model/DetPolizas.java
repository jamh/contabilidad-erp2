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
public class DetPolizas implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable= false, length = 25 ))
        
    })
    
    @JsonProperty("ID")
    private DetPolizasId id;
    
    @JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
      @JsonProperty("ATRIBUTOS")
    @Column(name = "ATRIBUTOS")
    private AtributosDetPolizas atributosDetPolizas;
    
    public DetPolizas(){
    }
    
    public DetPolizas(DetPolizasId id){
        
        this.id = id;
    
    }

    public void setId(DetPolizasId id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAtributosDetPolizas(AtributosDetPolizas atributosDetPolizas) {
        this.atributosDetPolizas = atributosDetPolizas;
    }

    public DetPolizasId getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public AtributosDetPolizas getAtributosDetPolizas() {
        return atributosDetPolizas;
    }
    
    
}
