/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.model;

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
public class ErpParidadId implements Serializable {

    @Column(name = "DIVISA_ORIGEN")
    private String divisaOrigen;
    
    @Column(name = "DIVISA_DESTINO")
    private String divisaDestino;
                  
    @Column(name = "FECHA")    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public ErpParidadId() {
    }
    
     public ErpParidadId(String divisaOrigen, String divisaDestino, Date fecha) {
         
         this.divisaOrigen = divisaOrigen;
         this.divisaDestino = divisaDestino;
         this.fecha = fecha;
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
