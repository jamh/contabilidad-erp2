/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_XML")
public class ErpFeXML implements Serializable {
    
    @Column (name = "COMPANIA")
    @JsonProperty("COMPANIA")
    private String compania;
   
    
    @Column (name = "HORA")
    @JsonProperty("HORA")
    private String hora;
    
    @Column (name = "XML")
    @JsonProperty("XML")
    private String xml;
    
    @Column (name = "DIR_XML")
    @JsonProperty("DIR_XML")
    private String dir_xml;
    
    @Column (name = "PDF")
    @JsonProperty("PDF")
    private String pdf;
    
    @Column (name = "DIR_PDF")
    @JsonProperty("DIR_PDF")
    private String dir_pdf;
    
    @Id
     @Column (name = "SEC")
    @JsonProperty("SEC")
    private String sec;
    
     @Column (name = "FECHA")
    @JsonProperty("FECHA")
        @Temporal(TemporalType.TIMESTAMP) 
    private Date fecha;

    public ErpFeXML() {
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public void setDir_xml(String dir_xml) {
        this.dir_xml = dir_xml;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public void setDir_pdf(String dir_pdf) {
        this.dir_pdf = dir_pdf;
    }

    public String getCompania() {
        return compania;
    }

    public String getHora() {
        return hora;
    }

    public String getXml() {
        return xml;
    }

    public String getDir_xml() {
        return dir_xml;
    }

    public String getPdf() {
        return pdf;
    }

    public String getDir_pdf() {
        return dir_pdf;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
}
