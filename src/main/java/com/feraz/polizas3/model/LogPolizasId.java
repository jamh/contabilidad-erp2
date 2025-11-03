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
public class LogPolizasId implements java.io.Serializable {
    
     @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "NUM_POLIZA")
    private String numPoliza;
    
    @Column(name = "FECHA_POLIZA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPoliza;
    
    @Column(name = "TIPO_POLIZA")
    private String tipoPoliza;
    
   
    public LogPolizasId(){
    
    }
    
    public LogPolizasId(String usuario, String numPoliza, Date fechaPoliza, String tipoPoliza){
        
        this.usuario = usuario;
        this.numPoliza = numPoliza;
        this.fechaPoliza = fechaPoliza;
        this.tipoPoliza = tipoPoliza;
      
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNumPoliza() {
        return numPoliza;
    }

    public Date getFechaPoliza() {
        return fechaPoliza;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    public void setFechaPoliza(Date fechaPoliza) {
        this.fechaPoliza = fechaPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }
    
    

    
}
