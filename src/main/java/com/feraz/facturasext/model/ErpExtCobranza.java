/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.model;

/**
 *
 * @author Ing. David Ortiz García
 */
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
@Table (name="ERP_EXT_COBRANZA" )
public class ErpExtCobranza implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "idCobranza", column = @Column(name = "ID_COBRANZA",nullable = false)),
        @AttributeOverride(name = "invoiceNo", column = @Column(name = "INVOICE_NO",nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC",nullable = false))
    
    })
    
    private ErpExtCobranzaId id;
     
     
    @Column (name = "FECHA_INVOICE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInvoice;
    
    @Column (name = "FECHA_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
    
    @Column (name = "MONEDA_INVOICE")
    private String monedaInvoice;
    
    @Column (name = "MONEDA_PAGO")
    private String monedaPago;
    
    @Column (name = "TIPO_CAMBIO_PAGO")
    private BigDecimal tipoCambioPago;
    
    @Column (name = "IMPORTE_INVOICE")
    private BigDecimal importeInvoice;
    
    @Column (name = "IMPORTE_PAGO_MON_ORIG")
    private BigDecimal importePagoMonOrig;
    
    @Column (name = "IMPORTE_PAGO")
    private BigDecimal importePago;
    
    @Column (name = "IMPORTE_RESTANTE")
    private BigDecimal importeRestante;
    
    @Column (name = "REFERENCIA")
    private String referencia;
    
    @Column (name = "BANCO_PAGO")
    private String bancoPago;
    
    @Column (name = "NUMERO_POLIZA")
    private String numeroPoliza;
    
    @Column (name = "TIPO_POLIZA")
    private String tipoPoliza;
    
    @Column (name = "FECHA_POLIZA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPoliza;
    
    public ErpExtCobranza(){
    
    }
    
    public ErpExtCobranza(ErpExtCobranzaId id){
    
        this.id = id;
        
    }

    public ErpExtCobranzaId getId() {
        return id;
    }

    public void setId(ErpExtCobranzaId id) {
        this.id = id;
    }

    public Date getFechaInvoice() {
        return fechaInvoice;
    }

    public void setFechaInvoice(Date fechaInvoice) {
        this.fechaInvoice = fechaInvoice;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMonedaInvoice() {
        return monedaInvoice;
    }

    public void setMonedaInvoice(String monedaInvoice) {
        this.monedaInvoice = monedaInvoice;
    }

    public String getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(String monedaPago) {
        this.monedaPago = monedaPago;
    }

    public BigDecimal getTipoCambioPago() {
        return tipoCambioPago;
    }

    public void setTipoCambioPago(BigDecimal tipoCambioPago) {
        this.tipoCambioPago = tipoCambioPago;
    }

    public BigDecimal getImporteInvoice() {
        return importeInvoice;
    }

    public void setImporteInvoice(BigDecimal importeInvoice) {
        this.importeInvoice = importeInvoice;
    }

    public BigDecimal getImportePagoMonOrig() {
        return importePagoMonOrig;
    }

    public void setImportePagoMonOrig(BigDecimal importePagoMonOrig) {
        this.importePagoMonOrig = importePagoMonOrig;
    }

    public BigDecimal getImportePago() {
        return importePago;
    }

    public void setImportePago(BigDecimal importePago) {
        this.importePago = importePago;
    }

    public BigDecimal getImporteRestante() {
        return importeRestante;
    }

    public void setImporteRestante(BigDecimal importeRestante) {
        this.importeRestante = importeRestante;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBancoPago() {
        return bancoPago;
    }

    public void setBancoPago(String bancoPago) {
        this.bancoPago = bancoPago;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public Date getFechaPoliza() {
        return fechaPoliza;
    }

    public void setFechaPoliza(Date fechaPoliza) {
        this.fechaPoliza = fechaPoliza;
    }
    
    
    
}
