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
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpProvPagoUnicoId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_MOV")
    private Integer idMov;
    
    public ErpProvPagoUnicoId(){
    
    }
    
    public ErpProvPagoUnicoId(String compania, Integer idMov){
        
        this.compania = compania;
        this.idMov = idMov;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdMov() {
        return idMov;
    }

    public void setIdMov(Integer idMov) {
        this.idMov = idMov;
    }
    
    
    
}
