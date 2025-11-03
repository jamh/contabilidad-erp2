/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpCatConvertidorId implements Serializable{

    //@JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;

    //@JsonProperty("ORIGEN")
    @Column(name = "ORIGEN")
    private String origen;

    //@JsonProperty("IDCONCGASTO")
    @Column(name = "IDCONCGASTO")
    private String idconcgasto;
    
    //@JsonProperty("NO_CASO")
    @Column(name = "NO_CASO")
    private BigDecimal noCaso;

    public ErpCatConvertidorId() {
    }
    
     public ErpCatConvertidorId(String compania, String origen, String idconcgasto, BigDecimal noCaso) {
         
         this.compania = compania;
         this.origen = origen;
         this.idconcgasto = idconcgasto;
         this.noCaso = noCaso;
         
         
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getIdconcgasto() {
        return idconcgasto;
    }

    public void setIdconcgasto(String idconcgasto) {
        this.idconcgasto = idconcgasto;
    }

    public BigDecimal getNoCaso() {
        return noCaso;
    }

    public void setNoCaso(BigDecimal noCaso) {
        this.noCaso = noCaso;
    }

    
    
}
