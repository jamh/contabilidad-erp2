/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.model;

/**
 *
 * @author 55555
 */
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuCedulasId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "CEDULA")
    private String cedula;
    
     public AuCedulasId() {
    }
    
     public AuCedulasId(String compania, String cedula) {
         
         this.compania = compania;
         this.cedula = cedula;
         
         
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
     
     
    
}
