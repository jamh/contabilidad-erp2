/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ing. JAMH
 */
@Entity
@Table(name = "ERP_SAT_POLIZAS")
@XmlRootElement(name = "Polizas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatPolizas implements Serializable {

    @Id
    @XmlTransient
    @Column(name = "PID")
    private String pid;
      @XmlTransient
    @Column(name = "COMPANIA")
//    @XmlElement(name = "COMPANIA")
    private String compania;
    @Column(name = "VERSION")
    @XmlElement(name = "VERSION")
    private String versiones;
    @Column(name = "RFC")
    @XmlElement(name = "RFC")
    private String rfc;
    @Column(name = "MES")
    @XmlElement(name = "MES")
    private String mes;
    @Column(name = "ANIO")
    @XmlElement(name = "ANIO")
    private String anio;
    
     @Column(name = "TIPO_SOLICITUD")
    @XmlElement(name = "TIPO_SOLICITUD")
    private String tipoSolicitud;
      @Column(name = "NUM_ORDEN")
    @XmlElement(name = "NUM_ORDEN")
    private String numOrden;
       @Column(name = "NUM_TRAMITE")
    @XmlElement(name = "NUM_TRAMITE")
    private String numTramite;
       
      @Column(name = "SELLO")
    @XmlElement(name = "SELLO")
    private String sello;
      
      @Column(name = "NO_CERTIFICADO")
    @XmlElement(name = "NO_CERTIFICADO")
    private String noCertificado;
      
       @Column(name = "CERTIFICADO")
    @XmlElement(name = "CERTIFICADO")
    private String certificado;

    public ErpSatPolizas() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getVersiones() {
        return versiones;
    }

    public void setVersiones(String versiones) {
        this.versiones = versiones;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(String numOrden) {
        this.numOrden = numOrden;
    }

    public String getNumTramite() {
        return numTramite;
    }

    public void setNumTramite(String numTramite) {
        this.numTramite = numTramite;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public String getNoCertificado() {
        return noCertificado;
    }

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    
    
}
