/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.model;

/**
 *
 * @author 55555
 */

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "AU_CEDULAS")
public class AuCedulas implements java.io.Serializable{
    
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "cedula", column = @Column(name = "CEDULA", nullable = false, length = 10))

    })


    private AuCedulasId id;
    
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    public AuCedulas(){
    
    }
    
    public AuCedulas (AuCedulasId id){
        
        this.id = id;
    
    }

    public AuCedulasId getId() {
        return id;
    }

    public void setId(AuCedulasId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
