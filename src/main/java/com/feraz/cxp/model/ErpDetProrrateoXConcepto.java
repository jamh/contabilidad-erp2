/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author vavi
 */

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

@Entity
@Table (name="ERP_DET_PRORRATEOXCONCEPTO" )
public class ErpDetProrrateoXConcepto implements java.io.Serializable{
    
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO",nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC",nullable = false)),
        @AttributeOverride(name = "ctoCto", column = @Column(name = "CTO_CTO",nullable = false))
    
    })
    
    private ErpDetProrrateoXConceptoId id;
    
    
    @Column (name = "PORCENTAJE")
    private String porcentaje;
    
    @Column (name = "IMPORTE_PRORRATEADO")
    private BigDecimal importeProrrateo;
    
    @Column (name = "TOTAL_CONCEPTO")
    private BigDecimal totalConcepto;
    
    @Column (name = "TIPO_GASTO")
    private Integer tipoGasto;
    
    public ErpDetProrrateoXConcepto(){
    }
    
    public ErpDetProrrateoXConcepto(ErpDetProrrateoXConceptoId id){
        
        this.id = id;
    
    }

    public ErpDetProrrateoXConceptoId getId() {
        return id;
    }

    public void setId(ErpDetProrrateoXConceptoId id) {
        this.id = id;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getImporteProrrateo() {
        return importeProrrateo;
    }

    public void setImporteProrrateo(BigDecimal importeProrrateo) {
        this.importeProrrateo = importeProrrateo;
    }

    public BigDecimal getTotalConcepto() {
        return totalConcepto;
    }

    public void setTotalConcepto(BigDecimal totalConcepto) {
        this.totalConcepto = totalConcepto;
    }

    public Integer getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(Integer tipoGasto) {
        this.tipoGasto = tipoGasto;
    }
    
    
    
    
    
    
}
