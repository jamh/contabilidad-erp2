/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

/**
 *
 * @author 55555
 */
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ERP_CXP_FACTURASXOTRAS")
public class ErpCxpFacturasXOtras implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
      private  ErpCxpFacturasXOtrasId id;
     


    @Column(name = "NUMERO_OTRAS")
    private Integer numeroOtras;
    
    @Column(name = "NUMERO_FE")
    private Integer numeroFe;
    
    @Column(name = "ORIGEN")
    private String origen;
    
    
      public ErpCxpFacturasXOtras() {
    }
      
        public ErpCxpFacturasXOtras(ErpCxpFacturasXOtrasId id ) {
            
            this.id = id;
    }

    public ErpCxpFacturasXOtrasId getId() {
        return id;
    }

    public void setId(ErpCxpFacturasXOtrasId id) {
        this.id = id;
    }

    public Integer getNumeroOtras() {
        return numeroOtras;
    }

    public void setNumeroOtras(Integer numeroOtras) {
        this.numeroOtras = numeroOtras;
    }

    public Integer getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(Integer numeroFe) {
        this.numeroFe = numeroFe;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
        
        

    
    
}
