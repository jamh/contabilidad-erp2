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
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author Ing. JAMH
 */
@Embeddable
public class ErpParidadCompaniaId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
     @Column(name = "DIVISA_ORIGEN")
    private String divisaOrigen;
    
    @Column(name = "DIVISA_DESTINO")
    private String divisaDestino;
                  
    @Column(name = "FECHA")    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public ErpParidadCompaniaId() {
    }
    
     public ErpParidadCompaniaId(String compania,String divisaOrigen, String divisaDestino, Date fecha) {
         
         this.compania = compania;
         this.divisaOrigen = divisaOrigen;
         this.divisaDestino = divisaDestino;
         this.fecha = fecha;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getDivisaOrigen() {
        return divisaOrigen;
    }

    public void setDivisaOrigen(String divisaOrigen) {
        this.divisaOrigen = divisaOrigen;
    }

    public String getDivisaDestino() {
        return divisaDestino;
    }

    public void setDivisaDestino(String divisaDestino) {
        this.divisaDestino = divisaDestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

     
    
}
