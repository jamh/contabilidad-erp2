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
@Table(name = "ERP_FE_PERCEPCION")
public class ErpFePercepcion implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })    
    private ErpFePercepcionId id;
    
     @Column (name = "IMPORTE_EXENTO")
     private BigDecimal importeExento;
     
     @Column (name = "IMPORTE_GRAVADO")
     private BigDecimal importeGravado;
     
     @Column (name = "CONCEPTO")
     private String concepto;
     
     @Column (name = "CLAVE")
     private String clave;
     
     @Column (name = "TIPO_PERCEPCION")
     private String tipoPercepcion;
     
     
     public ErpFePercepcion(){
     
     }
     
     public ErpFePercepcion(ErpFePercepcionId id){
         
         this.id = id;
     
     }

    public ErpFePercepcionId getId() {
        return id;
    }

    public void setId(ErpFePercepcionId id) {
        this.id = id;
    }

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }

    public BigDecimal getImporteGravado() {
        return importeGravado;
    }

    public void setImporteGravado(BigDecimal importeGravado) {
        this.importeGravado = importeGravado;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoPercepcion() {
        return tipoPercepcion;
    }

    public void setTipoPercepcion(String tipoPercepcion) {
        this.tipoPercepcion = tipoPercepcion;
    }
     
     
     
    
}
