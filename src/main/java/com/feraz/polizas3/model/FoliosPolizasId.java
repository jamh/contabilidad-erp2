/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
public class FoliosPolizasId implements java.io.Serializable {
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ANO")
    private int ano;
    
   @Column (name = "MES")
    private int mes;
    
    @Column (name = "TIPO_POLIZA")
    private String tipoPoliza;
    
    public FoliosPolizasId(){
    
    }
    
    public FoliosPolizasId(String compania, int ano, int mes, String tipoPoliza){
        
        this.compania = compania;
        this.tipoPoliza = tipoPoliza;
        this.ano = ano;
        this.mes = mes;
    
    }

    public String getCompania() {
        return compania;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }
    
    
    
}
