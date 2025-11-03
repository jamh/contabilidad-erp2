/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.model;

/**
 *
 * @author Administrador
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "ERP_SAT_TRANSACCION")
public class ErpSatTransaccion implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })
     
        
    private ErpSatTransaccionId id;
    
    
    @Column(name = "NUMERO_POL")
    private String numeroPol;
    
   
    @Column(name = "TIPO_POL")
    private String tipoPol;
    
   
    @Column(name = "FECHA_POL")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPol;
    
   
    @Column(name = "SEC_POL")
    private int secPol;
    
    @Column(name = "ORIGEN_PAGO")
    private String origenPago;
    
    @Column(name = "T_DOC_PAGO")
    private String tDocPago;
    
    @Column(name = "FOLIO_PAGO")
    private int folioPago;
    
    @Column(name = "SEC_PAGO")
    private int secPago;
    
    @Column(name = "NUM_CTA")
    private String numCta;
    
    @Column(name = "DES_CTA")
    private String desCta;
    
    @Column(name = "CONCEPTO")
    private String concepto;
    
    @Column(name = "DEBE")
    private BigDecimal debe;
    
    @Column(name = "HABER")
    private BigDecimal haber;
    
    @Column(name = "TIPO")
    private String tipo;
    
    @Column(name = "UUID_CFDI")
    private String uuidCfdi;
    
    @Column(name = "RFC")
    private String rfc;
    
    @Column(name = "CFD_CBB_SERIE")
    private String cfdCbbSerie;
    
    @Column(name = "CFD_CBB_NUMFOL")
    private String cfdCbbNnumFol;
    
    @Column(name = "NUM_FACT_EXT")
    private String numFactExt;
    
    @Column(name = "TAX_ID")
    private String taxId;
    
    @Column(name = "NUM")
    private String num;
    
    @Column(name = "BAN_EMIS_NAL")
    private String banEmisNal;
    
    @Column(name = "BAN_EMIS_EXT")
    private String banEmisExt;
    
    @Column(name = "FECHA")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "BENEF")
    private String benef;
    
    @Column(name = "CTA_ORI")
    private String ctaOri;
    
    @Column(name = "BANCO_ORI_NAL")
    private String bancoOriNal;
    
    @Column(name = "BANCO_ORI_EXT")
    private String bancoOriExt;
    
    @Column(name = "CTA_DEST")
    private String ctaDest;
    
    @Column(name = "BANCO_DEST_NAL")
    private String bancoDestNal;
    
    @Column(name = "BANCO_DEST_EXT")
    private String bancoDestExt;
    
    @Column(name = "MET_PAGO_POL")
    private String metPagoPol;
    
    @Column(name = "MONTO_TOTAL")
    private BigDecimal montoTotal;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "TIP_CAMB")
    private BigDecimal tipCamb;
    
    @Column(name = "MET_PAGO_AUX")
    private String metPagoAux;
    
     public ErpSatTransaccion(){
    
    }
    
    public ErpSatTransaccion(ErpSatTransaccionId id){
        
        this.id = id;
        
    }

    public ErpSatTransaccionId getId() {
        return id;
    }

    public void setId(ErpSatTransaccionId id) {
        this.id = id;
    }

    public String getNumeroPol() {
        return numeroPol;
    }

    public void setNumeroPol(String numeroPol) {
        this.numeroPol = numeroPol;
    }

    public String getTipoPol() {
        return tipoPol;
    }

    public void setTipoPol(String tipoPol) {
        this.tipoPol = tipoPol;
    }

    public Date getFechaPol() {
        return fechaPol;
    }

    public void setFechaPol(Date fechaPol) {
        this.fechaPol = fechaPol;
    }

    public int getSecPol() {
        return secPol;
    }

    public void setSecPol(int secPol) {
        this.secPol = secPol;
    }

    public String getOrigenPago() {
        return origenPago;
    }

    public void setOrigenPago(String origenPago) {
        this.origenPago = origenPago;
    }

    public String gettDocPago() {
        return tDocPago;
    }

    public void settDocPago(String tDocPago) {
        this.tDocPago = tDocPago;
    }

    public int getFolioPago() {
        return folioPago;
    }

    public void setFolioPago(int folioPago) {
        this.folioPago = folioPago;
    }

    public int getSecPago() {
        return secPago;
    }

    public void setSecPago(int secPago) {
        this.secPago = secPago;
    }

    public String getNumCta() {
        return numCta;
    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public String getDesCta() {
        return desCta;
    }

    public void setDesCta(String desCta) {
        this.desCta = desCta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUuidCfdi() {
        return uuidCfdi;
    }

    public void setUuidCfdi(String uuidCfdi) {
        this.uuidCfdi = uuidCfdi;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCfdCbbSerie() {
        return cfdCbbSerie;
    }

    public void setCfdCbbSerie(String cfdCbbSerie) {
        this.cfdCbbSerie = cfdCbbSerie;
    }

    public String getCfdCbbNnumFol() {
        return cfdCbbNnumFol;
    }

    public void setCfdCbbNnumFol(String cfdCbbNnumFol) {
        this.cfdCbbNnumFol = cfdCbbNnumFol;
    }

    public String getNumFactExt() {
        return numFactExt;
    }

    public void setNumFactExt(String numFactExt) {
        this.numFactExt = numFactExt;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBanEmisNal() {
        return banEmisNal;
    }

    public void setBanEmisNal(String banEmisNal) {
        this.banEmisNal = banEmisNal;
    }

    public String getBanEmisExt() {
        return banEmisExt;
    }

    public void setBanEmisExt(String banEmisExt) {
        this.banEmisExt = banEmisExt;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getBenef() {
        return benef;
    }

    public void setBenef(String benef) {
        this.benef = benef;
    }

    public String getCtaOri() {
        return ctaOri;
    }

    public void setCtaOri(String ctaOri) {
        this.ctaOri = ctaOri;
    }

    public String getBancoOriNal() {
        return bancoOriNal;
    }

    public void setBancoOriNal(String bancoOriNal) {
        this.bancoOriNal = bancoOriNal;
    }

    public String getBancoOriExt() {
        return bancoOriExt;
    }

    public void setBancoOriExt(String bancoOriExt) {
        this.bancoOriExt = bancoOriExt;
    }

    public String getCtaDest() {
        return ctaDest;
    }

    public void setCtaDest(String ctaDest) {
        this.ctaDest = ctaDest;
    }

    public String getBancoDestNal() {
        return bancoDestNal;
    }

    public void setBancoDestNal(String bancoDestNal) {
        this.bancoDestNal = bancoDestNal;
    }

    public String getBancoDestExt() {
        return bancoDestExt;
    }

    public void setBancoDestExt(String bancoDestExt) {
        this.bancoDestExt = bancoDestExt;
    }

    public String getMetPagoPol() {
        return metPagoPol;
    }

    public void setMetPagoPol(String metPagoPol) {
        this.metPagoPol = metPagoPol;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTipCamb() {
        return tipCamb;
    }

    public void setTipCamb(BigDecimal tipCamb) {
        this.tipCamb = tipCamb;
    }

    public String getMetPagoAux() {
        return metPagoAux;
    }

    public void setMetPagoAux(String metPagoAux) {
        this.metPagoAux = metPagoAux;
    }
    
    
    
    
}
