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
@Table(name = "ERP_FE_IMPUESTO_COMPL")

public class ErpFeImpuestoCompl implements java.io.Serializable {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "idPago", column = @Column(name = "ID_PAGO", nullable = false))
    })
    private ErpFeImpuestoComplId id;
    
    @Column (name = "TOT_IMP_RETENIDOS")
    private BigDecimal totImpRetenidos;
    
    @Column (name = "TOT_IMP_TRASLADOS")
    private BigDecimal totImpTraslados;
    
    public ErpFeImpuestoCompl(){
        
    }
    
    public ErpFeImpuestoCompl(ErpFeImpuestoComplId id){
        this.id = id;
        
    }

    public ErpFeImpuestoComplId getId() {
        return id;
    }

    public void setId(ErpFeImpuestoComplId id) {
        this.id = id;
    }

    public BigDecimal getTotImpRetenidos() {
        return totImpRetenidos;
    }

    public void setTotImpRetenidos(BigDecimal totImpRetenidos) {
        this.totImpRetenidos = totImpRetenidos;
    }

    public BigDecimal getTotImpTraslados() {
        return totImpTraslados;
    }

    public void setTotImpTraslados(BigDecimal totImpTraslados) {
        this.totImpTraslados = totImpTraslados;
    }
    
    
}
