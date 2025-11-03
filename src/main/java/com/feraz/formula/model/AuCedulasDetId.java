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
public class AuCedulasDetId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "CEDULA")
    private String cedula;
    
    @Column(name = "RENGLON")
    private int renglon;
    
    @Column(name = "COLUMNA")
    private int columna;
    
    public AuCedulasDetId(){
    
    }
    
    public AuCedulasDetId(String compania, String cedula, int renglon, int columna){
        
        this.compania = compania;
        this.cedula = cedula;
        this.renglon = renglon;
        this.columna = columna;
    
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

    public int getRenglon() {
        return renglon;
    }

    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
}
