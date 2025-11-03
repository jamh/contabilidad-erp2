/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.model;

/**
 *
 * @author vavi
 */


import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_WS_INTERCOS" )
public class ErpWsIntercos implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "uuid", column = @Column(name = "UUID",nullable = false,length = 50))
    
    })
    
    private ErpWsIntercosId id;
    
    
    @Column (name = "URL_XML")
    private String urlXml;
    
    @Column (name = "URL_PDF")
    private String urlPdf;
    
    @Column (name = "RFC_RECEPTOR")
    private String rfcReceptor;
    
    @Column (name = "RFC_EMISOR")
    private String rfcEmisor;
    
    @Column (name = "OPERACION")
    private String operacion;
    
    @Column (name = "COMPANIA_CFDI")
    private String companiaCFDI;
    
    @Column (name = "NUMERO_FE")
    private Integer numeroFe;
    
    
    public ErpWsIntercos(){
    
    }
    
    public ErpWsIntercos(ErpWsIntercosId id){
        
        this.id = id;
    
    }

    public ErpWsIntercosId getId() {
        return id;
    }

    public void setId(ErpWsIntercosId id) {
        this.id = id;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getCompaniaCFDI() {
        return companiaCFDI;
    }

    public void setCompaniaCFDI(String companiaCFDI) {
        this.companiaCFDI = companiaCFDI;
    }

    public Integer getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(Integer numeroFe) {
        this.numeroFe = numeroFe;
    }
    
    
    

    
}
