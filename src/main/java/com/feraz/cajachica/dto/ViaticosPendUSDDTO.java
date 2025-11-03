/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaticosPendUSDDTO {
    
      @JsonProperty("COMISION_USD")
    public String comisionUsd;
      
      @JsonProperty("F_INI_COMISION_USD")
    public String fIniComisionUsd;
      
      
      @JsonProperty("F_FIN_COMISION_USD")
    public String fFinComisionUsd;
      
      
      @JsonProperty("T_MONEDA_USD")
    public String tMonedaUsd;
      
      
      @JsonProperty("EMPLEADO_USD")
    public String empleadoUsd;
      
      
      @JsonProperty("IMPORTE_USD")
    public String importeUsd;
      

       
    
}
