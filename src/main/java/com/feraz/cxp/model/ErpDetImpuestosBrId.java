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
 * @author Ing. David O
 */
@Embeddable
public class ErpDetImpuestosBrId implements Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "NUMERO")
    private int numero;
    
    @Column(name = "IMPUESTO")
    private String impuesto;
 
    public ErpDetImpuestosBrId(String compania, int numero,String impuesto) {
        this.compania = compania;
        this.numero = numero;
        this.impuesto = impuesto;
      
    }
    
    public ErpDetImpuestosBrId(){
    
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

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    

    
}
