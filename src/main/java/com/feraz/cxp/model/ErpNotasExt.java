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
@Table (name="ERP_NOTAS_EXT" )
public class ErpNotasExt implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "idNota", column = @Column(name = "ID_NOTA",nullable = false)),
        @AttributeOverride(name = "idFactura", column = @Column(name = "ID_FACTURA",nullable = false))
    
    })
    
    private ErpNotasExtId id;
    
    
    @Column (name = "ORIGEN")
    private String origen;
    
    @Column (name = "IMP_APLICA_NOTA")
    private BigDecimal impAplicaNota;
    
    @Column (name = "IMP_APLICA_FACT")
    private BigDecimal impAplicaFact;
    
    
    @Column (name = "IMP_ANT_FACT")
    private BigDecimal impAntFact;
    
    public ErpNotasExt (){
    
    }
    
    public ErpNotasExt(ErpNotasExtId id){
    
        this.id = id;
        
    }

    public ErpNotasExtId getId() {
        return id;
    }

    public void setId(ErpNotasExtId id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public BigDecimal getImpAplicaNota() {
        return impAplicaNota;
    }

    public void setImpAplicaNota(BigDecimal impAplicaNota) {
        this.impAplicaNota = impAplicaNota;
    }

    public BigDecimal getImpAplicaFact() {
        return impAplicaFact;
    }

    public void setImpAplicaFact(BigDecimal impAplicaFact) {
        this.impAplicaFact = impAplicaFact;
    }

    public BigDecimal getImpAntFact() {
        return impAntFact;
    }

    public void setImpAntFact(BigDecimal impAntFact) {
        this.impAntFact = impAntFact;
    }
    
    
    
}
