/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.saldos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Feraz3
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable

public class SaldosId implements java.io.Serializable{
    


   @Column(name = "COMPANIA")
    private String compania;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "CALENDARIO")
    private Integer calendario;
    @Column(name = "PERIODO")
    private Integer periodo;
    @Column(name = "CUENTA")
    private String cuenta;
    @Column(name = "C_COSTOS")
    private String cCostos;
    
     public SaldosId(){
    
    }
    
    public SaldosId(String compania, String tipo, Integer calendario, Integer periodo, String cuenta, String cCostos){
        
        this.compania = compania;
        this.tipo = tipo;
        this.calendario = calendario;
        this.periodo = periodo;
        this.cuenta = cuenta;
        this.cCostos = cCostos;
       
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCalendario() {
        return calendario;
    }

    public void setCalendario(Integer calendario) {
        this.calendario = calendario;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getcCostos() {
        return cCostos;
    }

    public void setcCostos(String cCostos) {
        this.cCostos = cCostos;
    }

  
  
}
