/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.model;

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
public class DetDIOTId implements java.io.Serializable{
    
    
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
    
    @Column(name = "CUENTA")
    private String cuenta;
    
    @Column(name = "CONCEPTO")
    private String concepto;
    
    public DetDIOTId(){
    
    }
    
    public DetDIOTId(String compania, String tipoPoliza, Date fecha, String numero, int sec, String cuenta, String concepto){
   
        this.compania = compania;
        this.tipoPoliza = tipoPoliza;
        this.fecha = fecha;
        this.numero = numero;
        this.sec = sec;
        this.cuenta = cuenta;
        this.concepto = concepto;
    
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

    public int getSec() {
        return sec;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getConcepto() {
        return concepto;
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

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
    
}
