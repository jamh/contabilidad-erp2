/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiciosDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;

    @JsonProperty("ID_FAMILIA")
    public String idFamilia;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
    @JsonProperty("ID_PROVEEDOR")
    public String idProveedor;
    
}
