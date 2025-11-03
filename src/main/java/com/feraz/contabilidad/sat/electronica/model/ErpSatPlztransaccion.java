/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ERP_SAT_PLZTRANSACCION")
//@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "Transaccion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatPlztransaccion implements Serializable{

  
    @Column(name = "PID")
    @XmlTransient
    private String pid;
       @XmlTransient
      @Id
        @Column(name = "SEC")
    private long sec;

    @Column(name = "COMPANIA")
//    // @XmlElement(name="Transaccion")
    private String compania;

    @Column(name = "NUMCTA")
    // @XmlElement(name = "NUMCTA")
    private String numcta;
    @Column(name = "CONCEPTO")
   // @XmlElement(name = "CONCEPTO")
    private String concepto;
    @Column(name = "DEBE")
    // @XmlElement(name = "DEBE")
    private String debe;
    
    @Column(name = "HABER")
   // @XmlElement(name = "HABER")
    private String haber;
    
    @Column(name = "MONEDA")
   // @XmlElement(name = "MONEDA")
    private String moneda;
     @XmlTransient
     @Column(name = "NUMERO")
    private long numero;
    
//    @ManyToOne(fetch=FetchType.EAGER)  
// @JoinColumn(name = "pid")  
//        // @XmlElement
    @XmlTransient
      @ManyToOne(fetch=FetchType.EAGER)  
// @JoinColumn(name = "pid")  
     @JoinColumns({
        @JoinColumn(name = "pid", referencedColumnName = "pid"),
        @JoinColumn(name = "compania", referencedColumnName = "compania"),
         @JoinColumn(name = "numero", referencedColumnName = "numero")
//        @JoinColumn(name = "column_3", referencedColumnName = "column_3"),
//        @JoinColumn(name = "column_4", referencedColumnName = "column_4")
    })
 private ErpSatPoliza erpSatPoliza;  

    public ErpSatPlztransaccion() {
    }

    public ErpSatPlztransaccion(String pid) {
        this.pid = pid;
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

    public String getNumcta() {
        return numcta;
    }

    public void setNumcta(String numcta) {
        this.numcta = numcta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDebe() {
        return debe;
    }

    public void setDebe(String debe) {
        this.debe = debe;
    }

    public String getHaber() {
        return haber;
    }

    public void setHaber(String haber) {
        this.haber = haber;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public ErpSatPoliza getErpSatPoliza() {
        return erpSatPoliza;
    }

    public void setErpSatPoliza(ErpSatPoliza erpSatPoliza) {
        this.erpSatPoliza = erpSatPoliza;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }
    
    

}
