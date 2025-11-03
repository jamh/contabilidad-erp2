/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.model;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpFeCargaBancoId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "SEC")
    private Integer sec;
    
    
    public ErpFeCargaBancoId(){
    }

    public ErpFeCargaBancoId(String compania,Integer sec){
        
        this.compania = compania;
        this.sec = sec;
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }
    
    

}

