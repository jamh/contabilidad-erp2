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
public class RembolsoDTO {
    
    
      @JsonProperty("COMPANIA_TES_REM")
    public String compania;
      
      @JsonProperty("NUMERO_TES_REM")
    public String numeroRem;
      
      @JsonProperty("FOLIO_TES_REM")
    public String folioRem;
      
      @JsonProperty("FECHA_TES_REM")
    public String fechaRem;
      
      @JsonProperty("TOTAL_TES_REM")
    public String totalRem;
      
      @JsonProperty("MONEDA_TES_REM")
    public String monedaRem;
      
      @JsonProperty("RFC_EMISOR_TES_REM")
    public String rfcEmisorRem;
      
      @JsonProperty("MONEDA_PAGO_TES_REM")
    public String monedaPagoRem;
      
      @JsonProperty("NOMBRE_EMISOR_TES_REM")
    public String nombreEmisorRem;
      
      @JsonProperty("CTA_CLABE_PROV_REM")
    public String ctaClabeRem;
      
      @JsonProperty("EMPLEADO_REM")
    public String empleadoRem;

      @JsonProperty("BANCO_PROV_REM")
    public String bancoRem;


}
