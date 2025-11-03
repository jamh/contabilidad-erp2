/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
//@XmlRootElement(name="BalanzaId")
public class ErpSatBalanzaCtasId implements java.io.Serializable{
    
//     @JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
  //  @XmlElement
    private String compania;
    
//    @JsonProperty("PID")
    @Column(name = "PID")
    //@XmlElement
    private String pid;
    
    public ErpSatBalanzaCtasId(){
    }
    
    public ErpSatBalanzaCtasId(String compania, String pid){
        
        this.compania = compania;
        this.pid = pid;
   
    
    }

    public String getCompania() {
        return compania;
    }

    public String getPid() {
        return pid;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    
    
}
