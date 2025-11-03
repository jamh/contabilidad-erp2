/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author vavi
 */
@Entity
@Table(name = "ERP_FE_TRAS_LOCALES")
public class ErpFeTrasLocales implements java.io.Serializable{
    
         @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
       
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })    
    private ErpFeTrasLocalesId id;
    
     @Column (name = "IMP_LOC_TRASLADADO")
    private String impLocTraslado;
    
     @Column (name = "IMPORTE")
    private BigDecimal importe;
     
     @Column (name = "TASA_TRASLADO")
    private String tasaTraslado;
     
     
     public ErpFeTrasLocales(){
     
     
     }
     
     
     public ErpFeTrasLocales(ErpFeTrasLocalesId id){
     
          this.id = id;
     }

    public ErpFeTrasLocalesId getId() {
        return id;
    }

    public void setId(ErpFeTrasLocalesId id) {
        this.id = id;
    }

    public String getImpLocTraslado() {
        return impLocTraslado;
    }

    public void setImpLocTraslado(String impLocTraslado) {
        this.impLocTraslado = impLocTraslado;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getTasaTraslado() {
        return tasaTraslado;
    }

    public void setTasaTraslado(String tasaTraslado) {
        this.tasaTraslado = tasaTraslado;
    }
    
     
     
}
