/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

/**
 *
 * @author vavi
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpCajaChicaDetId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_CAJA")
    private Integer idCaja;
    
    @Column(name = "SEC")
    private Integer sec;
    
    public ErpCajaChicaDetId(){
    
    }
    
    public ErpCajaChicaDetId(String compania, Integer idCaja, Integer sec){
    
        this.compania = compania;
        this.idCaja = idCaja;
        this.sec = sec;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }
    
    
}
