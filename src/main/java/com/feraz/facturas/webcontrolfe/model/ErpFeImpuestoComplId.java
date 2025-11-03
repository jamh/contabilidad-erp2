/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Armando
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeImpuestoComplId implements java.io.Serializable{
    
    @JsonProperty
    @Column(name = "COMPANIA")
    private String compania;
    
    @JsonProperty
    @Column(name = "NUMERO")
    private int numero;
    
    @JsonProperty
    @Column(name = "ID_PAGO")
    private int idPago;
    
    public ErpFeImpuestoComplId(){
        
    }
    
    public ErpFeImpuestoComplId(String compania, int numero,int idPago){
        this.compania = compania;
        this.numero = numero;
        this.idPago = idPago;
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    
    
}
