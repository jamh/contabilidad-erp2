/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author vavi
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpDetProrrateoXConceptoId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "NUMERO")
    private Integer numero;
    
    @Column (name = "SEC")
    private Integer sec;
    
    @Column (name = "CTO_CTO")
    private String ctoCto;
    
    
    
    public ErpDetProrrateoXConceptoId(){
    
    }
    
    public ErpDetProrrateoXConceptoId(String compania, Integer numero,Integer sec,String ctoCto){
        
        this.compania = compania;
        this.numero = numero;
        this.sec = sec;
        this.ctoCto = ctoCto;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
    }
    
    
    
}
