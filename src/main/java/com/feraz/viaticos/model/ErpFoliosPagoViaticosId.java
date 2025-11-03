/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.model;

/**
 *
 * @author vavi
 */
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpFoliosPagoViaticosId implements java.io.Serializable{
    
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "FOLIO")
    private Integer folio;
    
    
    @Column (name = "COMISION")
    private BigDecimal comision;
    
    
    public ErpFoliosPagoViaticosId (){
    
    }
    
    public ErpFoliosPagoViaticosId(String compania, Integer folio, BigDecimal comision){
        
        this.compania = compania;
        this.folio = folio;
        this.comision = comision;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    
    
    
}
