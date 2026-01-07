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
 *
 * @author Ing. David Ortiz
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_TRASLADO_DR")
public class ErpFeTrasladoDr implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO")),
        @AttributeOverride(name = "idPago", column = @Column(name = "ID_PAGO", nullable = false)),
        @AttributeOverride(name = "idDocRel", column = @Column(name = "ID_DOC_REL", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID"))
    
    })
    
    private ErpFeTrasladoDrId id;
    
    @Column(name = "BASE_DR")
    private BigDecimal baseDr;
    
    @Column(name = "IMPUESTO_DR")
    private String impuestoDr;
    
    @Column(name = "TIPO_FACTOR_DR")
    private String tipoFactorDr;
    
    @Column(name = "TASA_O_CUOTA_DR")
    private BigDecimal tasaCuotaDr;
    
    @Column(name = "IMPORTE_DR")
    private BigDecimal importeDr;
    
    public ErpFeTrasladoDr(){
        
    }
    
    public ErpFeTrasladoDr(ErpFeTrasladoDrId id){
        this.id = id;
    }

    public ErpFeTrasladoDrId getId() {
        return id;
    }

    public void setId(ErpFeTrasladoDrId id) {
        this.id = id;
    }

    

    public BigDecimal getBaseDr() {
        return baseDr;
    }

    public void setBaseDr(BigDecimal baseDr) {
        this.baseDr = baseDr;
    }

    public String getImpuestoDr() {
        return impuestoDr;
    }

    public void setImpuestoDr(String impuestoDr) {
        this.impuestoDr = impuestoDr;
    }

    public String getTipoFactorDr() {
        return tipoFactorDr;
    }

    public void setTipoFactorDr(String tipoFactorDr) {
        this.tipoFactorDr = tipoFactorDr;
    }

    public BigDecimal getTasaCuotaDr() {
        return tasaCuotaDr;
    }

    public void setTasaCuotaDr(BigDecimal tasaCuotaDr) {
        this.tasaCuotaDr = tasaCuotaDr;
    }

    public BigDecimal getImporteDr() {
        return importeDr;
    }

    public void setImporteDr(BigDecimal importeDr) {
        this.importeDr = importeDr;
    }
    
    
    
}
