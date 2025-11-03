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
 * @author Administrador
 */
@Entity
@Table(name = "ERP_SAT_CATALOGO")
@XmlRootElement(name = "Catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatCatalogo implements Serializable {

    @XmlTransient
    @Id
    @Column(name = "PID")
    private String pid;
      @XmlTransient
    @Column(name = "COMPANIA")
//    @XmlElement(name = "COMPANIA")
    private String compania;
    @Column(name = "VERSIONES")
    @XmlElement(name = "VERSION")
    private String versiones;
    @Column(name = "RFC")
    @XmlElement(name = "RFC")
    private String rfc;
    @Column(name = "TOTALCTAS")
    @XmlElement(name = "TOTALCTAS")
    private String totalctas;
    @Column(name = "MES")
    @XmlElement(name = "MES")
    private String mes;
    @Column(name = "ANIO")
    @XmlElement(name = "ANIO")
    private String anio;

    public ErpSatCatalogo() {
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

    public String getTotalctas() {
        return totalctas;
    }

    public void setTotalctas(String totalctas) {
        this.totalctas = totalctas;
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

}
