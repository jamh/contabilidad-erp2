/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.model;

/**
 *
 * @author Feraz3
 */

import java.io.Serializable;
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
@Table(name = "ERP_NOM_CANCELA")

public class ErpNomCancela implements java.io.Serializable{
    
    
     @EmbeddedId
       @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))

    })
     
     private ErpNomCancelaId id;
      
     @Column(name = "OPERACION")
     private String operacion;
     
//      @Column(name = "FECHA")
//     @Temporal(javax.persistence.TemporalType.DATE)
//     private Date fecha;
      
       @Column(name = "PID")
     private String pid;
       
        @Column(name = "COMPANIA")
     private String compania;
        
        @Column(name = "USUARIO")
     private String usuario;
        
        public ErpNomCancela (){
        
        }
        
        public ErpNomCancela (ErpNomCancelaId id){
            
            this.id = id;
        
        }

    public ErpNomCancelaId getId() {
        return id;
    }

    public String getOperacion() {
        return operacion;
    }

//    public Date getFecha() {
//        return fecha;
//    }

    public String getPid() {
        return pid;
    }

    public String getCompania() {
        return compania;
    }

    public void setId(ErpNomCancelaId id) {
        this.id = id;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
        
    
      
    
}
