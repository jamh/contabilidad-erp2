/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.model;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author JAMH
 */

@Entity
@Table(name = "ERP_COMPENSACIONES")
public class ErpCompensaciones implements java.io.Serializable{
    
       @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))

    })

    //@JsonProperty("ID")
    private ErpCompensacionesId id;
        
     @Column(name = "DESCRIPCION")
     private String descripcion;
     
     @Column(name = "MONTO_INGRESO")
     private BigDecimal montoIngreso;
     
     @Column(name = "ESTATUS")
     private String estatus;
    
     
     public ErpCompensaciones(){
     
     }
     
     public ErpCompensaciones(ErpCompensacionesId id){
         
         
         this.id = id;
         
     
     
     }

    public ErpCompensacionesId getId() {
        return id;
    }

    public void setId(ErpCompensacionesId id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(BigDecimal montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
     
     
}
