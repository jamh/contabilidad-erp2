/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_PERCEPCIONES")
public class ErpFePercepciones implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFePercepcionesId id;
    
     @Column (name = "TOTAL_EXENTO")
     private BigDecimal totalExento;
     
     @Column (name = "TOTAL_GRAVADO")
     private BigDecimal totalGravado;
     
     @Column (name = "TOTAL_SUELDOS")
     private BigDecimal totalSueldos;
     
     
     public ErpFePercepciones(){
     
     }
     
     public ErpFePercepciones(ErpFePercepcionesId id){
     
         this.id = id;
     }

    public ErpFePercepcionesId getId() {
        return id;
    }

    public void setId(ErpFePercepcionesId id) {
        this.id = id;
    }

    public BigDecimal getTotalExento() {
        return totalExento;
    }

    public void setTotalExento(BigDecimal totalExento) {
        this.totalExento = totalExento;
    }

    public BigDecimal getTotalGravado() {
        return totalGravado;
    }

    public void setTotalGravado(BigDecimal totalGravado) {
        this.totalGravado = totalGravado;
    }

    public BigDecimal getTotalSueldos() {
        return totalSueldos;
    }

    public void setTotalSueldos(BigDecimal totalSueldos) {
        this.totalSueldos = totalSueldos;
    }
     
     
     
     
     
     
    
}
