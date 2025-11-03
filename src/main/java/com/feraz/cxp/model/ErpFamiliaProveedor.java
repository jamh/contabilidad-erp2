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

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_FAMILIA_PROVEEDOR")
public class ErpFamiliaProveedor implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "idProveedor", column = @Column(name = "ID_PROVEEDOR", nullable = false, length = 12)),
        @AttributeOverride(name = "idFamilia", column = @Column(name = "ID_FAMILIA", nullable = false))

    })
     
      private ErpFamiliaProveedorId id;
    
     
      @Column(name = "ESTATUS")
      private String estatus;
      
      public ErpFamiliaProveedor(){
      
      }
     public ErpFamiliaProveedor (ErpFamiliaProveedorId id){
         
         this.id = id;
     
     }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public ErpFamiliaProveedorId getId() {
        return id;
    }

    public void setId(ErpFamiliaProveedorId id) {
        this.id = id;
    }
     
     
    
}
