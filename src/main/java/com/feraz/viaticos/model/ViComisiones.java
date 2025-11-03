/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.model;

/**
 *
 * @author FERAZ-14
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="VI_COMISIONES" )
public class ViComisiones implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "comision", column = @Column(name = "COMISION",nullable = false))
    
    })
    
    private ViComisionesId id;
    
     
      
    @Column (name = "FECHA_CASH_FLOW")
    private Date fechaCashFlow;
    
    public ViComisiones(){
    
    }
    
    public ViComisiones(ViComisionesId id){
        
        this.id = id;   
    
    }

    public ViComisionesId getId() {
        return id;
    }

    public void setId(ViComisionesId id) {
        this.id = id;
    }

    public Date getFechaCashFlow() {
        return fechaCashFlow;
    }

    public void setFechaCashFlow(Date fechaCashFlow) {
        this.fechaCashFlow = fechaCashFlow;
    }
    
    

    
}
