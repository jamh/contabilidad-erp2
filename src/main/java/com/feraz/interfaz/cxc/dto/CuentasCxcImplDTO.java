/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CuentasCxcImplDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("CLAVE")
    public String clave;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
}
