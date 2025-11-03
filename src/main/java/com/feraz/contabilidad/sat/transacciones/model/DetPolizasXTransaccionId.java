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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class DetPolizasXTransaccionId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
     private String compania;
     
      @Column(name = "TIPO_POLIZA")
     private String tipoPoliza;
      
       @Column(name = "FECHA")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date fecha;
       
        @Column(name = "NUMERO")
     private String numero;
        
         @Column(name = "SEC")
     private int sec;
         
          @Column(name = "ID_TRANSACCION")
     private int idTransaccion;
     
     public DetPolizasXTransaccionId(){
     
     }
     
     public DetPolizasXTransaccionId(String compania, String tipoPoliza, Date fecha, String numero, int sec, int idTransaccion){
         
         this.compania = compania;
         this.tipoPoliza = tipoPoliza;
         this.fecha = fecha;
         this.numero = numero;
         this.sec = sec;
         this.idTransaccion = idTransaccion;
     
     }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
     
     
}
