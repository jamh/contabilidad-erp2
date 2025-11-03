/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dto;

/**
 *
 * @author 55555
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErpConcGastoDTO {
    
     @JsonProperty("COMPANIA_CONC")
    public String compania;
     
     @JsonProperty("CONCEPTO_CONC")
    public String concepto;
     
     @JsonProperty("NOMBRE_CONC")
    public String nombre;
     
     @JsonProperty("CUENTA_CONC")
    public String cuenta;
     

}
