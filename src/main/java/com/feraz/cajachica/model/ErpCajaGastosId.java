/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

/**
 *
 * @author LENOVO
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpCajaGastosId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_CAJA")
    private Integer idCaja;
    
    @Column(name = "SEC")
    private Integer sec;
    
    @Column(name = "ID_GASTO")
    private Integer idGasto;
    
    public ErpCajaGastosId(){
    
    }
    
    public ErpCajaGastosId(String compania, Integer idCaja, Integer sec, Integer idGasto){
    
        this.compania = compania;
        this.idCaja = idCaja;
        this.sec = sec;
        this.idGasto = idGasto;
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

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }

    
}
