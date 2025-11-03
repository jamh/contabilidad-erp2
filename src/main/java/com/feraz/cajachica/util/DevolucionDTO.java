/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.util;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DevolucionDTO {
    
    
     @JsonProperty("COMPANIA_DEV")
    public String compania;
     
     @JsonProperty("COMISION_DEV")
    public String comision;
     
     @JsonProperty("EMPLEADO_DEV")
    public String empleado;
     
     @JsonProperty("SEC_DEV")
    public String secDev;
     
     @JsonProperty("F_COMPROBANTE_DEV")
    public String fComprobante;
     
    @JsonProperty("MONEDA_DEV")
    public String moneda;
    
    @JsonProperty("TOTAL_DEV")
    public String total;
    
    @JsonProperty("ESTATUS_CAJA_DEV")
    public String estatusCaja;
    
    @JsonProperty("ESTATUS_CAJA_DEV_NOM")
    public String estatusCajaNom;


    
}
