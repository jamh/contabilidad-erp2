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
import javax.persistence.Embeddable;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpSatPolizaId implements java.io.Serializable{
    
    
     //@JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;
    
    //@JsonProperty("PID")
    @Column(name = "PID")
    private String pid;
    
    public ErpSatPolizaId(){
    }
    
    public ErpSatPolizaId(String compania, String pid){
        
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
