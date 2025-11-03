/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.model;

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
@Entity
@Table(name = "ESTRUCTURAS")

public class Estructuras implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "estructura", column = @Column(name = "ESTRUCTURA", nullable = false, length = 10))

    })
    
     @JsonProperty("ID2")
    private EstructurasId id;
    
    @JsonProperty("NOMBRE")
    @Column(name = "NOMBRE")
    private String nombre;
    
    @JsonProperty("NIVELES")
    @Column(name = "NIVELES")
    private int niveles;
    
    @JsonProperty("SEPARADOR")
    @Column(name = "SEPARADOR")
    private String separador;
    
    @JsonProperty("FORMATO")
    @Column(name = "FORMATO")
    private String formato;
    
    @JsonProperty("ESTATUS")
    @Column(name = "ESTATUS")
    private String estatus;
    
      public Estructuras(){
    
    }
    
    public Estructuras(EstructurasId id){
        
        this.id = id;
        
    }

    public EstructurasId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNiveles() {
        return niveles;
    }

    public String getSeparador() {
        return separador;
    }

    public String getFormato() {
        return formato;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setId(EstructurasId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
