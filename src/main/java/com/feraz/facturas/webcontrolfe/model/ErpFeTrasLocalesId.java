/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vavi
 */
@Embeddable
public class ErpFeTrasLocalesId implements java.io.Serializable{
    
    
      @Column (name = "COMPANIA")
    private String compania;
   
    @Column (name = "NUMERO")
    private int numero;
    
     @Column (name = "SEC")
    private int sec;
    
    public ErpFeTrasLocalesId (){
    
    
    }
    
    
     public ErpFeTrasLocalesId (String compania, int numero, int sec){
         
         this.compania = compania;
         this.numero = numero;
         this.sec = sec;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
     
     
     
}
