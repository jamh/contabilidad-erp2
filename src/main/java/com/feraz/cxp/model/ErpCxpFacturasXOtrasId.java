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
 * @author Ing. David Ortiz García
 */
@Embeddable
public class ErpCxpFacturasXOtrasId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "SEC")
    private int sec;

    public ErpCxpFacturasXOtrasId(String compania, int sec) {
        this.compania = compania;
        this.sec = sec;
    }

    public ErpCxpFacturasXOtrasId() {
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
    
    

    
}
