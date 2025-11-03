/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.model;

/**
 *
 * @author Feraz3
 */

import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class ConceptosDIOTId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "CONCEPTO")
    private String concepto;
    
    public ConceptosDIOTId(){
    
    }
    
    public ConceptosDIOTId(String compania, String concepto){
        
        this.compania = compania;
        this.concepto = concepto;
    
    }

    public String getCompania() {
        return compania;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
    
}
