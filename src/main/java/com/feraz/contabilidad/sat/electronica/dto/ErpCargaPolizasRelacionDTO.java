/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.electronica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 55555
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErpCargaPolizasRelacionDTO {
    
      
     @JsonProperty("COMPANIA")
    public String compania;
     
      @JsonProperty("REFERENCIA")
    public String referencia;
      
       @JsonProperty("NUMIDENPOL")
    public String NUMIDENPOL;
       
        @JsonProperty("FECHA")
    public String FECHA;

  
    
}


  


