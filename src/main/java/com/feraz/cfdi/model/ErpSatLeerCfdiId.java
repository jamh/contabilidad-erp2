/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cfdi.model;

/**
 *
 * @author Feraz3
 */
import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class ErpSatLeerCfdiId implements java.io.Serializable{
    
     @Column(name = "ID")
    private int id;
    
     @Column(name = "COMPANIA")
    private String compania;
    
   
    public ErpSatLeerCfdiId(){
    
    }
    
    public ErpSatLeerCfdiId(int id, String compania){
        
        this.id = id;
        this.compania = compania;
       
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }
    
    
    
}
