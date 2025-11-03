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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jamh.tools.DateAdapter;

@Entity
@Table(name = "ERP_SAT_AUXILIAR_CTAS_DET")
@XmlRootElement(name = "AuxiliarCtasDet")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatAuxiliarCtasDet implements java.io.Serializable{
    
     @Id
    @XmlTransient
    @Column(name = "PID")
    private String pid;
      @XmlTransient
    @Column(name = "COMPANIA")
//    @XmlElement(name = "COMPANIA")
    private String compania;
    
      
    @XmlTransient
    @Column(name = "SEC")
    private int sec;
    
    @XmlTransient
    @Column(name = "NUM_CTA")
    private String numCta;
    
    @XmlTransient
    @Column(name = "DES_CTA")
    private String desCta;
    
    @XmlTransient
    @Column(name = "SALDO_INI")
    private BigDecimal saldoIni;
    
    @XmlTransient
    @Column(name = "SALDO_FIN")
    private BigDecimal saldoFin;
    
    @Column(name = "FECHA")
    @XmlElement(name = "fecha", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    @XmlTransient
    @Column(name = "NUM_UNIDEN_POL")
    private String numUnidenPol;
    
    @XmlTransient
    @Column(name = "CONCEPTO")
    private String concepto;
    
    @XmlTransient
    @Column(name = "DEBE")
    private BigDecimal debe;
    
    @XmlTransient
    @Column(name = "HABER")
    private BigDecimal haber;
    
    public ErpSatAuxiliarCtasDet(){
    
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

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String getNumCta() {
        return numCta;
    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public String getDesCta() {
        return desCta;
    }

    public void setDesCta(String desCta) {
        this.desCta = desCta;
    }

    public BigDecimal getSaldoIni() {
        return saldoIni;
    }

    public void setSaldoIni(BigDecimal saldoIni) {
        this.saldoIni = saldoIni;
    }

    public BigDecimal getSaldoFin() {
        return saldoFin;
    }

    public void setSaldoFin(BigDecimal saldoFin) {
        this.saldoFin = saldoFin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumUnidenPol() {
        return numUnidenPol;
    }

    public void setNumUnidenPol(String numUnidenPol) {
        this.numUnidenPol = numUnidenPol;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }
    
    
}
