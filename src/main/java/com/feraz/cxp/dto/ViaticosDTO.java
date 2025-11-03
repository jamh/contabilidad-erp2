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
public class ViaticosDTO {
    
    
    @JsonProperty("COMPANIA_VIATIC")
    public String compania;
    
    @JsonProperty("NUMERO_VIATIC")
    public String numero;
    
    @JsonProperty("T_GASTO_VIATIC")
    public String tGasto;
    
    @JsonProperty("NOMBRE_T_GASTO_VIATIC")
    public String  nombreTGasto;
    
    @JsonProperty("FECHA_VIATIC")
    public String fecha;
    
    @JsonProperty("RFC_EMISOR_VIATIC")
    public String rfcEmisor;
    
    @JsonProperty("NOMBRE_EMISOR_VIATIC")
    public String nombreEmisor;
    
    
    @JsonProperty("DESCRIPCION_VIATIC")
    public String descripcion;
    
    
    @JsonProperty("SUBTOTAL_VIATIC")
    public String subtotal;

    @JsonProperty("TOTAL_VIATIC")
    public String total;
    
    @JsonProperty("CC_VIATIC")
    public String cc;
    
    @JsonProperty("NOMCC_VIATIC")
    public String nomCC;
    
    @JsonProperty("ESTATUS_CXP_VIATIC")
    public String estatus;
    
    @JsonProperty("BANCO_VIATIC")
    public String banco;
    
    @JsonProperty("NOM_BANCO_VIATIC")
    public String nomBanco;
    
    @JsonProperty("CUENTA_CLABE_VIATIC")
    public String cuentaClabe;
    
    @JsonProperty("REEMBOLSO")
    public String reembolso;
    
    @JsonProperty("FECHA_CASH_FLOW")
    public String fechaCashFlow;
  
  

    
}
