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
public class ViaticosPendMXNDTO {
    
    
     @JsonProperty("COMISION")
    public String comision;
      
      @JsonProperty("F_INI_COMISION")
    public String fIniComision;
      
      
      @JsonProperty("F_FIN_COMISION")
    public String fFinComision;
      
      
      @JsonProperty("T_MONEDA")
    public String tMoneda;
      
      
      @JsonProperty("EMPLEADO")
    public String empleado;
      
      
      @JsonProperty("IMPORTE")
    public String importe;
      

    
}
