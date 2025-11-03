/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ing. JAMH
 */
@Embeddable
public class ErpCpConcPagoId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "CONCEPTO")
    private String concepto;

    
    public ErpCpConcPagoId(){}
    
    public ErpCpConcPagoId (String compania, String concepto){
        
        this.compania = compania;
        this.concepto = concepto;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
    
}
