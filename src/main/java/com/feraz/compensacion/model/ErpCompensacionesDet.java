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
@Table(name = "ERP_COMPENSACIONES_DET")
public class ErpCompensacionesDet implements java.io.Serializable{
    
        @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
        @AttributeOverride(name = "idDocumento", column = @Column(name = "ID_DOCUMENTO", nullable = false)),
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 10))

    })

    //@JsonProperty("ID")
    private ErpCompensacionesDetId id;
        
     @Column(name = "DESCRIPCION_CONPENSACION")
     private String descripcionCompensacion;
     
     @Column(name = "IMPORTE_DOCUMENTO")
     private BigDecimal importeDocumento;
     
     @Column(name = "IMPORTE_PAGO")
     private BigDecimal importePago;
     
     @Column(name = "IMPORTE_COMPENSADO")
     private BigDecimal importeCompensado;
     
     public ErpCompensacionesDet(){
     
     }
     
     
     public ErpCompensacionesDet(ErpCompensacionesDetId id){
         
         this.id = id;
     
     }

    public ErpCompensacionesDetId getId() {
        return id;
    }

    public void setId(ErpCompensacionesDetId id) {
        this.id = id;
    }

    public String getDescripcionCompensacion() {
        return descripcionCompensacion;
    }

    public void setDescripcionCompensacion(String descripcionCompensacion) {
        this.descripcionCompensacion = descripcionCompensacion;
    }

    public BigDecimal getImporteDocumento() {
        return importeDocumento;
    }

    public void setImporteDocumento(BigDecimal importeDocumento) {
        this.importeDocumento = importeDocumento;
    }

    public BigDecimal getImportePago() {
        return importePago;
    }

    public void setImportePago(BigDecimal importePago) {
        this.importePago = importePago;
    }

    public BigDecimal getImporteCompensado() {
        return importeCompensado;
    }

    public void setImporteCompensado(BigDecimal importeCompensado) {
        this.importeCompensado = importeCompensado;
    }
     
     
    
}
