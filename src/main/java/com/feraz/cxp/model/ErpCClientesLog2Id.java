/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author lbadi
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpCClientesLog2Id implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name= "FOLIO")
    private int folio;
    
    public ErpCClientesLog2Id (){
    
    }
   
    public ErpCClientesLog2Id(String compania, int folio){
        
        this.compania = compania;
        this.folio = folio;
        
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }
    
    
    
}
