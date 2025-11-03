/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

/**
 *
 * @author 55555
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpPolizasVSFacturasAutErrId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "FOLIO")
    private String folio;
    
    @Column (name = "TIPO")
    private String tipo;
     
    
    public ErpPolizasVSFacturasAutErrId(){
    }
    
    public ErpPolizasVSFacturasAutErrId(String compania, String folio, String tipo){
        
        this.compania = compania;
        this.folio = folio;
        this.tipo = tipo;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
