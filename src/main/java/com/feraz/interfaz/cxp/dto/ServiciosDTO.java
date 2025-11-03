/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author vavi
 */
  @JsonIgnoreProperties(ignoreUnknown = true)
public class ServiciosDTO {
  

    @JsonProperty("COMPANIA")
    public String COMPANIA;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("ID")
    public String id;
    
    @JsonProperty("CUENTA_CARGO")
    public String cuentaCargo;
    
    @JsonProperty("ESTATUS")
    public String estatus;
    
    @JsonProperty("CUENTA_CARGO_COMPLEMENTARIA")
    public String cuentaCargoComplementaria;
    
 
}
