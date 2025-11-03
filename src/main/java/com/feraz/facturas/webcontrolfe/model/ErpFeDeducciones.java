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
@Table(name = "ERP_FE_DEDUCCIONES")
public class ErpFeDeducciones implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeDeduccionesId id;
    
     @Column (name = "TOTAL_OTRAS_DEDUC")
     private BigDecimal totalOtrasDeduc;
     
     @Column (name = "TOTAL_IMPUESTOS_RETENIDOS")
     private BigDecimal totalImpuestosRetenidos;
     
     
     public ErpFeDeducciones(){
     
     
     }
     
     
     public ErpFeDeducciones(ErpFeDeduccionesId id){
     
            this.id = id;
     }

    public ErpFeDeduccionesId getId() {
        return id;
    }

    public void setId(ErpFeDeduccionesId id) {
        this.id = id;
    }

    public BigDecimal getTotalOtrasDeduc() {
        return totalOtrasDeduc;
    }

    public void setTotalOtrasDeduc(BigDecimal totalOtrasDeduc) {
        this.totalOtrasDeduc = totalOtrasDeduc;
    }

    public BigDecimal getTotalImpuestosRetenidos() {
        return totalImpuestosRetenidos;
    }

    public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
        this.totalImpuestosRetenidos = totalImpuestosRetenidos;
    }
    
    
    
     
}
