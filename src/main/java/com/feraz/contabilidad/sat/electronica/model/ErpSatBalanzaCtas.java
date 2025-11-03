/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_SAT_BALANZACTAS")
@XmlRootElement(name = "Balanza")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatBalanzaCtas implements java.io.Serializable {

    @XmlTransient   
    @Column(name = "PID")
    private String pid;
    @XmlTransient
    @Column(name = "COMPANIA")
    private String compania;
     @Id
    @Column(name = "NUMCTA")
    @XmlElement
    private String numCta;

    @Column(name = "SALDOINI")
    @XmlElement
    private BigDecimal saldoIni;

    @Column(name = "DEBE")
    @XmlElement
    private BigDecimal debe;

    @Column(name = "HABER")
    @XmlElement
    private BigDecimal haber;

    @Column(name = "SALDOFIN")
    @XmlElement
    private BigDecimal saldoFin;

    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private ErpSatBalanza erpSatBalanza;

    public ErpSatBalanzaCtas() {
    }

//        public ErpSatBalanzaCtas(ErpSatBalanzaCtasId id){
//
//            this.id = id;
//
//        }
//
//    public ErpSatBalanzaCtasId getId() {
//        return id;
//    }
    public String getNumCta() {
        return numCta;
    }

    public BigDecimal getSaldoIni() {
        return saldoIni;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public BigDecimal getHaber() {
        return haber;
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

    public BigDecimal getSaldoFin() {
        return saldoFin;
    }
//
//    public void setId(ErpSatBalanzaCtasId id) {
//        this.id = id;
//    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public void setSaldoIni(BigDecimal saldoIni) {
        this.saldoIni = saldoIni;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public void setSaldoFin(BigDecimal saldoFin) {
        this.saldoFin = saldoFin;
    }

    public ErpSatBalanza getErpSatBalanza() {
        return erpSatBalanza;
    }

    public void setErpSatBalanza(ErpSatBalanza erpSatBalanza) {
        this.erpSatBalanza = erpSatBalanza;
    }

}
