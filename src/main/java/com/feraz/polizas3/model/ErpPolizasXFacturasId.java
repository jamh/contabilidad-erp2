/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpPolizasXFacturasId implements java.io.Serializable{
    
     
    @Column(name = "COMPANIA")
    private String compania;
   

    
    @Column(name = "NUMERO_POL")
    private String numeroPol;
    
     @Column(name = "FECHA_POL")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPol;
    
    @Column(name = "TIPO_POL")
    private String tipoPol;
    


    
    @Column(name = "OPERACION")
    private String operacion;
    

     
     
    public ErpPolizasXFacturasId(){
    
    }


    public String getCompania() {
        return compania;
    }



    public String getNumeroPol() {
        return numeroPol;
    }

    public Date getFechaPol() {
        return fechaPol;
    }

    public String getTipoPol() {
        return tipoPol;
    }


    public String getOperacion() {
        return operacion;
    }



    public void setCompania(String compania) {
        this.compania = compania;
    }

 

    public void setNumeroPol(String numeroPol) {
        this.numeroPol = numeroPol;
    }

    public void setFechaPol(Date fechaPol) {
        this.fechaPol = fechaPol;
    }

    public void setTipoPol(String tipoPol) {
        this.tipoPol = tipoPol;
    }


    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

   
    
    
    
   
    
}
