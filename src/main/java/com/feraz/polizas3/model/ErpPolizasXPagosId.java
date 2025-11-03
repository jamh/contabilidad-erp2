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
public class ErpPolizasXPagosId implements java.io.Serializable{
    
     
    @Column(name = "COMPANIA")
    private String compania;
   

    
    @Column(name = "NUMERO_POL")
    private String numeroPol;
    
     @Column(name = "FECHA_POL")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPol;
    
    @Column(name = "TIPO_POL")
    private String tipoPol;
    


     
     
    public ErpPolizasXPagosId(){
    
    }
    
    public ErpPolizasXPagosId(String compania, String numeroPol, Date fechaPol, String tipoPol){
        
        this.compania = compania;
        this.numeroPol = numeroPol;
        this.fechaPol = fechaPol;
        this.tipoPol = tipoPol;
    
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


    
    
    
   
    
}
