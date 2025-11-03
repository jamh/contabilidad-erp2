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
@Table(name = "ERP_FE_PAGOS")
public class ErpFePagos implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })    
    private ErpFePagosId id;
    
     @Column (name = "VERSION")
     private String version;
     
     @Column (name = "CTA_BENEFICIARIO")
     private String ctaBeneficiario;
     
     @Column (name = "MONTO")
     private BigDecimal monto;
     
     @Column (name = "MONEDA_P")
     private String monedaP;
     
     @Column (name = "FORMA_DE_PAGO_P")
     private String formaPagoP;
     
     @Column (name = "FECHA_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date fechaPago;
     
     @Column (name = "TIPO_CAMBIO_P")
     private BigDecimal tipoCambioP;
     
     @Column (name = "NUM_OPERACION")
     private String numOperacion;
     
     @Column (name = "RFC_EMISOR_CTA_ORD")
     private String rfcEmisorCtaOrd;
     
     @Column (name = "NOM_BANCO_ORD_EXT")
     private String nomBancoOrdExt;
     
     @Column (name = "CTA_ORDENANTE")
     private String ctaOrdenante;
     
     @Column (name = "RFC_EMISOR_CTA_BEN")
     private String rfcEmisorCtaBen;
     
     @Column (name = "TIPO_CAD_PAGO")
     private String tipoCadPago;
     
     @Column (name = "CERT_PAGO")
     private String certPago;
     
     @Column (name = "CAD_PAGO")
     private String cadPago;
     
     @Column (name = "SELLO_PAGO")
     private String selloPago;
     
     
     public ErpFePagos(){
     
     
     }
     
     public ErpFePagos(ErpFePagosId id){
     
         this.id = id;
     
     }

    public ErpFePagosId getId() {
        return id;
    }

    public void setId(ErpFePagosId id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCtaBeneficiario() {
        return ctaBeneficiario;
    }

    public void setCtaBeneficiario(String ctaBeneficiario) {
        this.ctaBeneficiario = ctaBeneficiario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMonedaP() {
        return monedaP;
    }

    public void setMonedaP(String monedaP) {
        this.monedaP = monedaP;
    }

    public String getFormaPagoP() {
        return formaPagoP;
    }

    public void setFormaPagoP(String formaPagoP) {
        this.formaPagoP = formaPagoP;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getTipoCambioP() {
        return tipoCambioP;
    }

    public void setTipoCambioP(BigDecimal tipoCambioP) {
        this.tipoCambioP = tipoCambioP;
    }

    public String getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public String getRfcEmisorCtaOrd() {
        return rfcEmisorCtaOrd;
    }

    public void setRfcEmisorCtaOrd(String rfcEmisorCtaOrd) {
        this.rfcEmisorCtaOrd = rfcEmisorCtaOrd;
    }

    public String getNomBancoOrdExt() {
        return nomBancoOrdExt;
    }

    public void setNomBancoOrdExt(String nomBancoOrdExt) {
        this.nomBancoOrdExt = nomBancoOrdExt;
    }

    public String getCtaOrdenante() {
        return ctaOrdenante;
    }

    public void setCtaOrdenante(String ctaOrdenante) {
        this.ctaOrdenante = ctaOrdenante;
    }

    public String getRfcEmisorCtaBen() {
        return rfcEmisorCtaBen;
    }

    public void setRfcEmisorCtaBen(String rfcEmisorCtaBen) {
        this.rfcEmisorCtaBen = rfcEmisorCtaBen;
    }

    public String getTipoCadPago() {
        return tipoCadPago;
    }

    public void setTipoCadPago(String tipoCadPago) {
        this.tipoCadPago = tipoCadPago;
    }

    public String getCertPago() {
        return certPago;
    }

    public void setCertPago(String certPago) {
        this.certPago = certPago;
    }

    public String getCadPago() {
        return cadPago;
    }

    public void setCadPago(String cadPago) {
        this.cadPago = cadPago;
    }

    public String getSelloPago() {
        return selloPago;
    }

    public void setSelloPago(String selloPago) {
        this.selloPago = selloPago;
    }
     
     
     
     
     
    
}
