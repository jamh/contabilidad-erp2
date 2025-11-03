/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.diot.model;

/**
 *
 * @author Administrador
 */

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "CONCEPTOS_DIOT")
public class ConceptosDIOT implements java.io.Serializable{
    
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "concepto", column = @Column(name = "CONCEPTO", nullable = false, length = 5))
    })
     
    private ConceptosDIOTId id;
     
     @Column(name = "NOMBRE")
    private String nombre;
     
     public ConceptosDIOT(){
     
     }
     
     public ConceptosDIOT(ConceptosDIOTId id){
     
         this.id = id;
         
     }

    public ConceptosDIOTId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(ConceptosDIOTId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
     
    
     
}
