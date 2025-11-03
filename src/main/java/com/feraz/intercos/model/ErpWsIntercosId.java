/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.model;

/**
 *
 * @author vavi
 */

    import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpWsIntercosId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "UUID")
    private String uuid;
    
    
    public ErpWsIntercosId(){
    
    }
    
    public ErpWsIntercosId(String compania, String uuid){
        
        this.compania = compania;
        this.uuid = uuid;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    
}
