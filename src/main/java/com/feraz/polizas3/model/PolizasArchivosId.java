/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class PolizasArchivosId implements java.io.Serializable{
    
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
    private BigDecimal sec;
    
     
    public PolizasArchivosId(){
    
    }
    
    public PolizasArchivosId(String compania, String tipoPoliza, Date fecha, String numero, BigDecimal sec){
        
        this.compania = compania;
        this.tipoPoliza = tipoPoliza;
        this.fecha = fecha;
        this.numero = numero;
        this.sec = sec;
      
    }

    public String getCompania() {
        return compania;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSec() {
        return sec;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSec(BigDecimal sec) {
        this.sec = sec;
    }
    
    
    
}
