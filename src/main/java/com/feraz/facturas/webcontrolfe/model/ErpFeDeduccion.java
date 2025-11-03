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
@Table(name = "ERP_FE_DEDUCCION")
public class ErpFeDeduccion implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })    
    private ErpFeDeduccionId id;
    
     @Column (name = "IMPORTE")
     private BigDecimal importe;
     
     @Column (name = "CONCEPTO")
     private String concepto;
     
     @Column (name = "CLAVE")
     private String clave;
     
     @Column (name = "TIPO_DEDUCCION")
     private String tipoDeduccion;
     
     
     public ErpFeDeduccion(){
     
     }
     
     public ErpFeDeduccion(ErpFeDeduccionId id){
         
         
         this.id = id;
     
     
     }

    public ErpFeDeduccionId getId() {
        return id;
    }

    public void setId(ErpFeDeduccionId id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
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

    public String getTipoDeduccion() {
        return tipoDeduccion;
    }

    public void setTipoDeduccion(String tipoDeduccion) {
        this.tipoDeduccion = tipoDeduccion;
    }
     
     
    
}
