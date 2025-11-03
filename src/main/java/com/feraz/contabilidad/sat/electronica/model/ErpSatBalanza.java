/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jamh.tools.DateAdapter;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ERP_SAT_BALANZA")
@XmlRootElement(name = "Balanza")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatBalanza implements Serializable {
     @XmlTransient
    @Id
    @Column(name = "PID")
    private String pid;
      @XmlTransient
    @Column(name = "COMPANIA")
    private String compania;
    @Column(name = "VERSIONES")
     @XmlElement(name = "VERSION")
    private String versiones;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "TOTALCTAS")
    private String totalctas;
    @Column(name = "MES")
    private String mes;
    @Column(name = "ANIO")
    private String anio;
    
      @Column(name = "TIPO_ENVIO")
    private String tipoEnvio;
      
       @Column(name = "FECHA_MOD_BAL")
       @XmlElement(name = "FechaModBal", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaModBal;
    
     @XmlElement(name="Cuentas")
    @OneToMany(mappedBy = "erpSatBalanza",fetch=FetchType.EAGER)
    private List<ErpSatBalanzaCtas> erpSatBalanzaCtas;

    public ErpSatBalanza() {
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

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }
    
    

    public List<ErpSatBalanzaCtas> getErpSatBalanzaCtas() {
        return erpSatBalanzaCtas;
    }

    public void setErpSatBalanzaCtas(List<ErpSatBalanzaCtas> erpSatBalanzaCtas) {
        this.erpSatBalanzaCtas = erpSatBalanzaCtas;
    }

    public Date getFechaModBal() {
        return fechaModBal;
    }

    public void setFechaModBal(Date fechaModBal) {
        this.fechaModBal = fechaModBal;
    }
    
    
    

}
