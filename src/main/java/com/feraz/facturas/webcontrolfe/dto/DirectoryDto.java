/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectoryDto {
 
    @JsonProperty("COMPANIA")
    private String compania;
    @JsonProperty("NUMERO")
    private int numero;
    @JsonProperty("XML")    
    private String xml;
    @JsonProperty("PDF")
    private String pdf;
    @JsonProperty("DIR_XML")
    private String dir_xml;
    @JsonProperty("DIR_PDF")
    private String dir_pdf;
    @JsonProperty("FLAG_CARGA")
    private String flagCarga;
    
     @JsonProperty("msgErr")
    private String msgErr;

    public DirectoryDto() {
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public void setDir_xml(String dir_xml) {
        this.dir_xml = dir_xml;
    }

    public void setDir_pdf(String dir_pdf) {
        this.dir_pdf = dir_pdf;
    }

    public String getCompania() {
        return compania;
    }

    public int getNumero() {
        return numero;
    }

    public String getXml() {
        return xml;
    }

    public String getPdf() {
        return pdf;
    }

    public String getDir_xml() {
        return dir_xml;
    }

    public String getDir_pdf() {
        return dir_pdf;
    }

    public String getFlagCarga() {
        return flagCarga;
    }

    public void setFlagCarga(String flagCarga) {
        this.flagCarga = flagCarga;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }
    
    
    
 
    
}
