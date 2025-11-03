/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author LENOVO
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
@Table (name="ERP_DET_PRORRATEOXNEGOCIO" )
public class ErpDetProrrateoXNegocio implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO",nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC",nullable = false)),
        @AttributeOverride(name = "ctoCto", column = @Column(name = "CTO_CTO",nullable = false)),
        @AttributeOverride(name = "tipo", column = @Column(name = "TIPO",nullable = false)),
        @AttributeOverride(name = "tipoNegocio", column = @Column(name = "TIPO_NEGOCIO",nullable = false)),
            @AttributeOverride(name = "idFamilia", column = @Column(name = "ID_FAMILIA",nullable = false))
    
    
    
    })
    
    private ErpDetProrrateoXNegocioId id;
    
    
    @Column (name = "PORCENTAJE")
    private String porcentaje;
    
    @Column (name = "IMPORTE_PRORRATEADO")
    private BigDecimal importeProrrateo;
    
    @Column (name = "TOTAL_CONCEPTO")
    private BigDecimal totalConcepto;
    
    @Column (name = "CUENTA_CONTABLE")
    private String cuentaContable;
    

    
    @Column (name = "CUENTA_COMPLEMENTARIA")
    private String cuentaComplementaria;
    
    @Column (name = "ID_PROVEEDOR")
    private String idProveedor;
    
    @Column (name = "TIPO_CTA")
    private String tipoCta;
    
    
    
    
    
    
    public ErpDetProrrateoXNegocio(){
    }
    
    
    public ErpDetProrrateoXNegocio(ErpDetProrrateoXNegocioId id){
    
            this.id = id;
    }

    public ErpDetProrrateoXNegocioId getId() {
        return id;
    }

    public void setId(ErpDetProrrateoXNegocioId id) {
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

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }



    public String getCuentaComplementaria() {
        return cuentaComplementaria;
    }

    public void setCuentaComplementaria(String cuentaComplementaria) {
        this.cuentaComplementaria = cuentaComplementaria;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTipoCta() {
        return tipoCta;
    }

    public void setTipoCta(String tipoCta) {
        this.tipoCta = tipoCta;
    }
    
    
    
    
}
