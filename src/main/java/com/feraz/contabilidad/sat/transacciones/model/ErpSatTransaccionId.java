/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.transacciones.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;


@Embeddable
public class ErpSatTransaccionId implements java.io.Serializable{
    
    
    @Column(name = "COMPANIA")
    private String compania;
     

    @Column(name = "ID")
    private BigDecimal id;
    
     public ErpSatTransaccionId(){
    
    }
    
    public ErpSatTransaccionId(String compania, BigDecimal id){
        
        this.compania = compania;
        this.id = id;
       
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    
    
}
