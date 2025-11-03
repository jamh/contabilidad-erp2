/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class CuentasId implements java.io.Serializable{
    
     @JsonProperty("ESTRUCTURA")
    @Column(name = "ESTRUCTURA")
    private String estructura;
     
    @JsonProperty("CUENTA")
    @Column(name = "CUENTA")
    private String cuenta;
    
     public CuentasId(){
    
    }
    
    public CuentasId(String estructura, String cuenta){
        
        this.estructura = estructura;
        this.cuenta = cuenta;
       
    }

    public String getEstructura() {
        return estructura;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
}
