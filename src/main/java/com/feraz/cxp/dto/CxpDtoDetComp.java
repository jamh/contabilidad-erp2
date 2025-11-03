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
public class CxpDtoDetComp {
    
    @JsonProperty("COMPANIA_DET_CXP")
    public String compania;
    
    @JsonProperty("ID_DET_CXP")
    public String id;
    
    @JsonProperty("SEC_DET_CXP")
    public String sec;
    
    @JsonProperty("IMPORTE_DET_CXP")
    public String importe;
    
    @JsonProperty("CANTIDAD_DET_CXP")
    public String cantidad;
    
    @JsonProperty("DESCRIPCION_DET_CXP")
    public String descripcion;
 
}
