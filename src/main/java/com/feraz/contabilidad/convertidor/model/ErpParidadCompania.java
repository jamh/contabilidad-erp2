/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.convertidor.model;

/**
 *
 * @author 55555
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_PARIDAD_COMPANIA")
public class ErpParidadCompania implements java.io.Serializable{
    
     
    @EmbeddedId
   @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "divisaOrigen", column = @Column(name = "DIVISA_ORIGEN", nullable = false, length = 10)),
        @AttributeOverride(name = "divisaDestino", column = @Column(name = "DIVISA_DESTINO", nullable = false, length = 25)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false, length = 25))
       
    })
    private ErpParidadCompaniaId id;
    
    @Column(name = "FACTOR")
    private String factor;
    
    
    public ErpParidadCompania(){
    
    }
    
    public ErpParidadCompania(ErpParidadCompaniaId id){
        
        this.id = id;
    
    }

    public ErpParidadCompaniaId getId() {
        return id;
    }

    public void setId(ErpParidadCompaniaId id) {
        this.id = id;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    
}
