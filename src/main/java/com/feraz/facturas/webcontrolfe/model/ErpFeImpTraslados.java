/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
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
@Table(name = "ERP_FE_IMP_TRASLADOS")
public class ErpFeImpTraslados implements java.io.Serializable {
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })    
    private ErpFeImpTrasladosId id;
    
     @Column (name = "IMPORTE")
    @JsonProperty("IMPORTE")
    private BigDecimal importe;
    
    @Column (name = "IMPUESTO")
    @JsonProperty("IMPUESTO")
    private String impuesto;
    
    @Column (name = "TASA")
    @JsonProperty("TASA")
    private BigDecimal tasa;
    
    @Column (name = "TIPO_FACTOR")
    @JsonProperty("TIPO_FACTOR")
    private String tipoFactor;
    
    
    @Column (name = "TASAOCUOTA")
    @JsonProperty("TASAOCUOTA")
    private BigDecimal tasaOCuota;
    
    
    public ErpFeImpTraslados(){
       }
    
        public ErpFeImpTraslados(ErpFeImpTrasladosId id){

            this.id = id;

        }

    public ErpFeImpTrasladosId getId() {
        return id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setId(ErpFeImpTrasladosId id) {
        this.id = id;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public String getTipoFactor() {
        return tipoFactor;
    }

    public void setTipoFactor(String tipoFactor) {
        this.tipoFactor = tipoFactor;
    }

    public BigDecimal getTasaOCuota() {
        return tasaOCuota;
    }

    public void setTasaOCuota(BigDecimal tasaOCuota) {
        this.tasaOCuota = tasaOCuota;
    }

   
    
}
