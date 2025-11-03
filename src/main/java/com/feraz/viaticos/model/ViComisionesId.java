/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.model;

/**
 *
 * @author FERAZ-14
 */
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ViComisionesId implements java.io.Serializable{
    
     @Column (name = "COMPANIA")
    private String compania;
  
    
    @Column (name = "COMISION")
    private String comision;
    
    public ViComisionesId(){};
    
    public ViComisionesId(String compania, String comision){
        
        this.compania = compania;
        this.comision = comision;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }
    
    
    
}
