/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_SAT_CATALOGOCTAS")
@XmlRootElement(name = "catalogocuentas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErpSatCatalogoCtas implements java.io.Serializable {

//      @EmbeddedId
//    @AttributeOverrides({
//        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
//        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable = false, length = 25))
//        
//    })   
//      @XmlTransient
//       @XmlElement
//    private ErpSatCatalogoCtasId id;
      @XmlTransient
    @Column(name = "COMPANIA")
    private String compania;
  @XmlTransient
    // @JsonProperty("PID")
    @Column(name = "PID")
    private String pid;

    @Column(name = "CODAGRUP")
    //@JsonProperty("CODAGRUP")
    @XmlElement
    private String codAgrup;
    @Id
    @Column(name = "NUMCTA")
    //@JsonProperty("NUMCTA")
    @XmlElement
    private String numCta;

    @Column(name = "DESCRIP")
    //@JsonProperty("DESCRIP")
    @XmlElement
    private String descrip;

    @Column(name = "NIVEL")
    //@JsonProperty("NIVEL")
    @XmlElement
    private int nivel;

    @Column(name = "NATUR")
    @XmlElement
    private String natur;
    
     @Column(name = "SUB_CTA_DE")
    @XmlElement
    private String subCtaDe;

    public ErpSatCatalogoCtas() {
    }

    public String getCodAgrup() {
        return codAgrup;
    }

    public String getNumCta() {
        return numCta;
    }

    public String getDescrip() {
        return descrip;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNatur() {
        return natur;
    }

    public void setCodAgrup(String codAgrup) {
        this.codAgrup = codAgrup;
    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNatur(String natur) {
        this.natur = natur;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSubCtaDe() {
        return subCtaDe;
    }

    public void setSubCtaDe(String subCtaDe) {
        this.subCtaDe = subCtaDe;
    }

    
}
