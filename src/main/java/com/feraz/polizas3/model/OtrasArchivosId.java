/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.model;

/**
 *
 * @author 55555
 */
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class OtrasArchivosId implements java.io.Serializable {
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SEC")
    private Integer sec;
    
     
    public OtrasArchivosId(){
    
    }
    
    public OtrasArchivosId(String compania, Integer id, Integer sec){
        
        this.compania = compania;
        this.id = id;
        this.sec = sec;
      
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }
    
    
}
