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
@Table(name = "ERP_FE_RETENCION_COMPL")
public class ErpFeRetencionCompl implements java.io.Serializable {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "idPago", column = @Column(name = "ID_PAGO", nullable = false)),        
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })
    private ErpFeRetencionComplId id;
    
    @Column(name = "IMPUESTO")
    private String impuesto;
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    public ErpFeRetencionCompl(){
        
    }
    
    public ErpFeRetencionCompl(ErpFeRetencionComplId id){
        this.id = id;
    }

    public ErpFeRetencionComplId getId() {
        return id;
    }

    public void setId(ErpFeRetencionComplId id) {
        this.id = id;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    
    
}
