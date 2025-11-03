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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_CONCEPTOXRETENCION")
public class ErpFeConceptoXRetencion implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "idConcepto", column = @Column(name = "ID_CONCEPTO", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })    
    private ErpFeConceptoXRetencionId id;
    
    @Column (name = "FOLIO")
    @JsonProperty("FOLIO")
    private String folio;
    
    @Column (name = "BASE")
    @JsonProperty("BASE")
    private BigDecimal base;
    
    @Column (name = "IMPUESTO")
    @JsonProperty("IMPUESTO")
    private String impuesto;
    
    @Column (name = "TIPO_FACTOR")
    @JsonProperty("TIPO_FACTOR")
    private String tipoFactor;
    
    @Column (name = "TASA_O_CUOTA")
    @JsonProperty("TASA_O_CUOTA")
    private BigDecimal tasaOcuota;
    
    @Column (name = "IMPORTE")
    @JsonProperty("IMPORTE")
    private BigDecimal importe;
    
    
    
    public ErpFeConceptoXRetencion(){
    
    
    }
    
    
    public ErpFeConceptoXRetencion(ErpFeConceptoXRetencionId id){
        
        
        this.id = id;
    
    
    }

    public ErpFeConceptoXRetencionId getId() {
        return id;
    }

    public void setId(ErpFeConceptoXRetencionId id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
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

    public BigDecimal getTasaOcuota() {
        return tasaOcuota;
    }

    public void setTasaOcuota(BigDecimal tasaOcuota) {
        this.tasaOcuota = tasaOcuota;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    
    
    
    
    
}
