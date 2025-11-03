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
@Table(name = "ERP_FE_DOC_RELACIONADO")
public class ErpFeDocRelacionado implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "idPago", column = @Column(name = "ID_PAGO", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })    
    private ErpFeDocRelacionadoId id;
    
     @Column (name = "IMP_SALDO_INSOLUTO")
     private BigDecimal impSaldoInsoluto;
     
     @Column (name = "IMP_PAGADO")
     private BigDecimal impPagado;
     
     @Column (name = "IMP_SALDO_ANT")
     private BigDecimal impSaldoAnt;
     
     @Column (name = "NUM_PARCIALIDAD")
     private Integer numParcialidad;
     
     @Column (name = "METODO_DE_PAGO_DR")
     private String metodoPagoDr;
     
     @Column (name = "MONEDA_DR")
     private String monedaDr;
     
     @Column (name = "FOLIO")
     private String folio;
     
     @Column (name = "ID_DOCUMENTO")
     private String idDocumento;
     
     @Column (name = "SERIE")
     private String serie;
     
     @Column (name = "TIPO_CAMBIO_DR")
     private BigDecimal tipoCambioDr;
     
     
     public ErpFeDocRelacionado(){
     
     }
     
     public ErpFeDocRelacionado(ErpFeDocRelacionadoId id){
         
         
         this.id = id;
     
     
     }

    public ErpFeDocRelacionadoId getId() {
        return id;
    }

    public void setId(ErpFeDocRelacionadoId id) {
        this.id = id;
    }

    public BigDecimal getImpSaldoInsoluto() {
        return impSaldoInsoluto;
    }

    public void setImpSaldoInsoluto(BigDecimal impSaldoInsoluto) {
        this.impSaldoInsoluto = impSaldoInsoluto;
    }

    public BigDecimal getImpPagado() {
        return impPagado;
    }

    public void setImpPagado(BigDecimal impPagado) {
        this.impPagado = impPagado;
    }

    public BigDecimal getImpSaldoAnt() {
        return impSaldoAnt;
    }

    public void setImpSaldoAnt(BigDecimal impSaldoAnt) {
        this.impSaldoAnt = impSaldoAnt;
    }

    public Integer getNumParcialidad() {
        return numParcialidad;
    }

    public void setNumParcialidad(Integer numParcialidad) {
        this.numParcialidad = numParcialidad;
    }

    public String getMetodoPagoDr() {
        return metodoPagoDr;
    }

    public void setMetodoPagoDr(String metodoPagoDr) {
        this.metodoPagoDr = metodoPagoDr;
    }

    public String getMonedaDr() {
        return monedaDr;
    }

    public void setMonedaDr(String monedaDr) {
        this.monedaDr = monedaDr;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public BigDecimal getTipoCambioDr() {
        return tipoCambioDr;
    }

    public void setTipoCambioDr(BigDecimal tipoCambioDr) {
        this.tipoCambioDr = tipoCambioDr;
    }
     
     
    
}
