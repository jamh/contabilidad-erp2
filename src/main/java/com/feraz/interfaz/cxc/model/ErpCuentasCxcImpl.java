/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.model;

/**
 *
 * @author vavi
 */
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_CUENTAS_CXC_IMPL" )
public class ErpCuentasCxcImpl implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "clave", column = @Column(name = "CLAVE",nullable = false))
    
    })
     
      private ErpCuentasCxcImplId id;
     
     @Column (name = "NOMBRE")
     private String nombre;
    
     @Column (name = "CUENTA")
     private String cuenta;
     
     
     public ErpCuentasCxcImpl(){
     
     }
     
     
     public ErpCuentasCxcImpl(ErpCuentasCxcImplId id){
     
         this.id  = id;
     }

    public ErpCuentasCxcImplId getId() {
        return id;
    }

    public void setId(ErpCuentasCxcImplId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
     
     
     
}
