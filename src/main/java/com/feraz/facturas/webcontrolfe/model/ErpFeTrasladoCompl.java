/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Armando
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_TRASLADO_COMPL")
public class ErpFeTrasladoCompl implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO")),
        @AttributeOverride(name = "idPago", column = @Column(name = "ID_PAGO", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID"))
    
    })
    
    private ErpFeTrasladoComplId id;
    
    @Column(name = "IMPUESTO")
    private String impuesto;
    
    @Column(name = "TIPO_FACTOR")
    private String tipoFactor;
    
    @Column(name = "TASA_O_CUOTA")
    private BigDecimal tasaCuota;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    public ErpFeTrasladoCompl(){
        
    }
    
    public ErpFeTrasladoCompl(ErpFeTrasladoComplId id){
        this.id = id;
    }

    public ErpFeTrasladoComplId getId() {
        return id;
    }

    public void setId(ErpFeTrasladoComplId id) {
        this.id = id;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getTipoFactor() {
        return tipoFactor;
    }

    public void setTipoFactor(String tipoFactor) {
        this.tipoFactor = tipoFactor;
    }

    public BigDecimal getTasaCuota() {
        return tasaCuota;
    }

    public void setTasaCuota(BigDecimal tasaCuota) {
        this.tasaCuota = tasaCuota;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    
    
    
}
