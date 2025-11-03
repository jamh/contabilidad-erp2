/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.model;

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
@Table (name="ERP_ORDENXFACTURA" )
public class ErpOrdenXFactura implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "idOrden", column = @Column(name = "ID_ORDEN",nullable = false)),
        @AttributeOverride(name = "numeroFe", column = @Column(name = "NUMERO_FE",nullable = false)),
        @AttributeOverride(name = "idOrdenDet", column = @Column(name = "ID_ORDEN_DET",nullable = false))
    
    })
    
    private ErpOrdenXFacturaId id;
     
     
    @Column (name = "ID")
    private String idOF;
    
    @Column (name = "CANTIDAD_LLEGO")
    private BigDecimal cantidadLlego;
    
    @Column (name = "ORIGEN")
    private String origen;
    
    @Column (name = "SUBTOTAL")
    private BigDecimal subtotal;
    
    @Column (name = "IVA")
    private BigDecimal iva;
    
    @Column (name = "TOTAL")
    private BigDecimal total;
    
    @Column (name = "ESTATUS_CXP")
    private String estatusCXP;
    
    
    public ErpOrdenXFactura (){
    
    }
    
    
    public ErpOrdenXFactura (ErpOrdenXFacturaId id){
    
        this.id = id;
    }

    public ErpOrdenXFacturaId getId() {
        return id;
    }

    public void setId(ErpOrdenXFacturaId id) {
        this.id = id;
    }

    public String getIdOF() {
        return idOF;
    }

    public void setIdOF(String idOF) {
        this.idOF = idOF;
    }

    public BigDecimal getCantidadLlego() {
        return cantidadLlego;
    }

    public void setCantidadLlego(BigDecimal cantidadLlego) {
        this.cantidadLlego = cantidadLlego;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstatusCXP() {
        return estatusCXP;
    }

    public void setEstatusCXP(String estatusCXP) {
        this.estatusCXP = estatusCXP;
    }
    
    
    
    
}
