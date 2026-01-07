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
 * @author Ing. David Ortiz
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeRetencionDrId implements java.io.Serializable{
    
    @JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;
    
    @JsonProperty("NUMERO")
    @Column(name = "NUMERO")
    private int numero;
    
    @JsonProperty("ID_PAGO")
    @Column(name = "ID_PAGO")
    private int idPago;
    
    @JsonProperty("ID_DOC_REL")
    @Column(name = "ID_DOC_REL")
    private int idDocRel;
    
    @JsonProperty("ID")
    @Column(name = "ID")
    private int id;
    
    public ErpFeRetencionDrId(){
        
    }
    
    public ErpFeRetencionDrId(String compania, int numero, int idDocRel, int id,int idPago){
        this.compania = compania;
        this.numero = numero;
        this.id = id;
        this.idPago = idPago;
        this.idDocRel = idDocRel;
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

    public int getIdDocRel() {
        return idDocRel;
    }

    public void setIdDocRel(int idDocRel) {
        this.idDocRel = idDocRel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
