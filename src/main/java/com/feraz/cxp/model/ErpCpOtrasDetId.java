/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpCpOtrasDetId implements Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "ID")
    private int id;
    
    @Column(name = "SEC")
    private int sec;
    
    
    public ErpCpOtrasDetId(){
    
    }
    
    public ErpCpOtrasDetId(String compania,int id,int sec){
        
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
    
    
    
}
