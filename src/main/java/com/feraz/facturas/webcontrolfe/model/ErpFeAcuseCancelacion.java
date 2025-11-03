/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author LENOVO
 */

import javax.persistence.AttributeOverride;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ERP_FE_ACUSE_CANCELACION")
public class ErpFeAcuseCancelacion implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "uuid", column = @Column(name = "UUID", nullable = false))
    })
    private ErpFeAcuseCancelacionId id;

    @Column(name = "ESTATUS_UUID")
    //@JsonProperty("FOLIO")
    private String estatusId;
    
     @Column(name = "DIR_XML")
    //@JsonProperty("FOLIO")
    private String dirXml;
     
     @Column(name = "DIR_PDF")
    //@JsonProperty("FOLIO")
    private String dirPdf;
     
     @Column(name = "DESCRIPCION")
    //@JsonProperty("FOLIO")
    private String descripcion;
     
     @Column(name = "XML")
    //@JsonProperty("FOLIO")
    private String xml;
     
     @Column(name = "PDF")
    //@JsonProperty("FOLIO")
    private String pdf;
     
     
    
    
    
    
    public ErpFeAcuseCancelacion(){
    
    }
    
    public ErpFeAcuseCancelacion(ErpFeAcuseCancelacionId id){
        
        this.id = id;
    
    }

    public ErpFeAcuseCancelacionId getId() {
        return id;
    }

    public void setId(ErpFeAcuseCancelacionId id) {
        this.id = id;
    }

    public String getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(String estatusId) {
        this.estatusId = estatusId;
    }

    public void setDirXml(String dirXml) {
        this.dirXml = dirXml;
    }

    public void setDirPdf(String dirPdf) {
        this.dirPdf = dirPdf;
    }

    public String getDirXml() {
        return dirXml;
    }

    public String getDirPdf() {
        return dirPdf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
    
    
    
}
