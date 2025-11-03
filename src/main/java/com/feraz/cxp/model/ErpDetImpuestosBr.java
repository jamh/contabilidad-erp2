/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;
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
 * @author David O
 */
@Entity
@Table(name = "ERP_DET_IMPUESTOS_BR")
public class ErpDetImpuestosBr implements Serializable{
    
     @EmbeddedId
    @AttributeOverrides({      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "impuesto", column = @Column(name = "IMPUESTO", nullable = false, length = 10))
      
    })
      private  ErpDetImpuestosBrId id;
     
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "MUNICIPIO")
    private String municipio;
    
    @Column(name = "TIPO_IMPUESTO")
    private String tipoImpuesto;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    
    public ErpDetImpuestosBr(){
    
    }
    
    public ErpDetImpuestosBr(ErpDetImpuestosBrId id){
    
            this.id = id;
    }

    public ErpDetImpuestosBrId getId() {
        return id;
    }

    public void setId(ErpDetImpuestosBrId id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }
    
    
    
}
