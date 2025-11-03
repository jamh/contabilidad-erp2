/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.diot.model;

/**
 *
 * @author Administrador
 */

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "DET_DIOT")

public class DetDIOT implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
        @AttributeOverride(name = "cuenta", column = @Column(name = "CUENTA", nullable = false, length = 50)),
        @AttributeOverride(name = "concepto", column = @Column(name = "CONCEPTO", nullable = false, length = 5))
    })
    
    private DetDIOTId id;
     
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    public DetDIOT(){
    
    }
    
    public DetDIOT(DetDIOTId id){
        
        this.id = id;
        
    }

    public DetDIOTId getId() {
        return id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setId(DetDIOTId id) {
        this.id = id;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    
    
    
    
    
    
    
    
}
