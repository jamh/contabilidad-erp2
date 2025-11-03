/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
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
@Table(name = "ERP_SAT_REP_AUX_FOL_DET")
@XmlRootElement(name = "DetAuxFol")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatRepAuxFolDet implements java.io.Serializable{
    
     
    @XmlTransient
    @Column(name = "PID")
    private String pid;
      @XmlTransient
    @Column(name = "COMPANIA")
//    @XmlElement(name = "COMPANIA")
    private String compania;
      
    @Id 
    @Column(name = "SEC")
    @XmlElement(name = "SEC")
    private int sec;
    
    @Column(name = "NUM_UNIDEN_POL")
    @XmlElement(name = "NUM_UNIDEN_POL")
    private String numUniDenPol;
    
    @Column(name = "FECHA")
    @XmlElement(name = "fecha", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    @Column(name = "TIPO_POL")
    private String tipoPol;
    
    public ErpSatRepAuxFolDet(){
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

    public String getNumUniDenPol() {
        return numUniDenPol;
    }

    public void setNumUniDenPol(String numUniDenPol) {
        this.numUniDenPol = numUniDenPol;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPol() {
        return tipoPol;
    }

    public void setTipoPol(String tipoPol) {
        this.tipoPol = tipoPol;
    }
    
    
    
    
    
}
