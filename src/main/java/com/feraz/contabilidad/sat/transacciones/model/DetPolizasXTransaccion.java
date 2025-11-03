/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.model;

/**
 *
 * @author 55555
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
@Table(name = "DET_POLIZASXTRANSACCION")
public class DetPolizasXTransaccion implements java.io.Serializable{
    
      
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
        @AttributeOverride(name = "idTransaccion", column = @Column(name = "ID_TRANSACCION", nullable = false))
    })
     
     private DetPolizasXTransaccionId id;
     
     
     public DetPolizasXTransaccion (){
     
     }
     
     public DetPolizasXTransaccion(DetPolizasXTransaccionId id){
         
         this.id = id;
     
     }

    public DetPolizasXTransaccionId getId() {
        return id;
    }

    public void setId(DetPolizasXTransaccionId id) {
        this.id = id;
    }
     
     
    
}
