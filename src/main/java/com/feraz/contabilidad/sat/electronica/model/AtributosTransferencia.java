/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
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

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtributosTransferencia implements java.io.Serializable{
    
    @JsonProperty("CTAORI")
    @Column(name = "CTAORI")
    private String ctaOriTransferencia;
    
     @JsonProperty("BANCOORI")
    @Column(name = "BANCOORI")
    private String bancoOriTransferencia;
     
     @JsonProperty("MONTO")
    @Column(name = "MONTO")
    private BigDecimal montoTransferencia;
     
    @JsonProperty("CTADEST")
    @Column(name = "CTADEST")
    private String ctaDestTransferencia;
    
    @JsonProperty("BANCODEST")
    @Column(name = "BANCODEST")
    private String bancoDestTransferencia;
    
    @JsonProperty("FECHA")
    @Column(name = "FECHA")
    private Date fechaTransferencia;
    
    @JsonProperty("BENEF")
    @Column(name = "BENEF")
    private String benefTransferencia;
    
    @JsonProperty("RFC")
    @Column(name = "RFC")
    private String rfcTransferencia;
    
    public AtributosTransferencia(){
    }
    
    public AtributosTransferencia(String ctaOriTransferencia, String bancoOriTransferencia, BigDecimal montoTransferencia, String ctaDestTransferencia, String bancoDestTransferencia, Date fechaTransferencia, String benefTransferencia, String rfcTransferencia){
        
        this.ctaOriTransferencia = ctaOriTransferencia;
        this.bancoOriTransferencia = bancoOriTransferencia;
        this.montoTransferencia = montoTransferencia;
        this.ctaDestTransferencia = ctaDestTransferencia;
        this.bancoDestTransferencia = bancoDestTransferencia;
        this.fechaTransferencia = fechaTransferencia;
        this.benefTransferencia = benefTransferencia;
        this.rfcTransferencia = rfcTransferencia;
        
    
    }

    public String getCtaOriTransferencia() {
        return ctaOriTransferencia;
    }

    public String getBancoOriTransferencia() {
        return bancoOriTransferencia;
    }

    public BigDecimal getMontoTransferencia() {
        return montoTransferencia;
    }

    public String getCtaDestTransferencia() {
        return ctaDestTransferencia;
    }

    public String getBancoDestTransferencia() {
        return bancoDestTransferencia;
    }

    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    public String getBenefTransferencia() {
        return benefTransferencia;
    }

    public String getRfcTransferencia() {
        return rfcTransferencia;
    }

    public void setCtaOriTransferencia(String ctaOriTransferencia) {
        this.ctaOriTransferencia = ctaOriTransferencia;
    }

    public void setBancoOriTransferencia(String bancoOriTransferencia) {
        this.bancoOriTransferencia = bancoOriTransferencia;
    }

    public void setMontoTransferencia(BigDecimal montoTransferencia) {
        this.montoTransferencia = montoTransferencia;
    }

    public void setCtaDestTransferencia(String ctaDestTransferencia) {
        this.ctaDestTransferencia = ctaDestTransferencia;
    }

    public void setBancoDestTransferencia(String bancoDestTransferencia) {
        this.bancoDestTransferencia = bancoDestTransferencia;
    }

    public void setFechaTransferencia(Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public void setBenefTransferencia(String benefTransferencia) {
        this.benefTransferencia = benefTransferencia;
    }

    public void setRfcTransferencia(String rfcTransferencia) {
        this.rfcTransferencia = rfcTransferencia;
    }
    
    
    
}
