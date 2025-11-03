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
public class BancoDTO {
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("BANCO_SAT")
    public String bancoSat;
    
    @JsonProperty("CUENTA_CLABE")
    public String cuentaClabe;
    
    @JsonProperty("CUENTA_BANCO")
    public String cuentaBanco;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
    @JsonProperty("CUENTA_COMPLEMENTARIA")
    public String cuentaComplementaria;
    

}
