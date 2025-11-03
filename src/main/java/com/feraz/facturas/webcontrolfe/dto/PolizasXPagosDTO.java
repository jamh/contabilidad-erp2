/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author 55555
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolizasXPagosDTO {
    
     @JsonProperty("COMPANIA_REL_PAGOS")
      public String compania_rel;
     
      @JsonProperty("NUM_POL_REL_PAGOS")
      public String num_pol_rel;
      
       @JsonProperty("FECHA_POL_REL_PAGOS")
      public String fecha_pol_rel;
     
      @JsonProperty("TIPO_POL_REL_PAGOS")
      public String tipo_pol_rel;
      
       @JsonProperty("NOMBRE_REL_PAGOS")
      public String nombre_rel;
     
      @JsonProperty("CARGOS_REL_PAGOS")
      public String cargos_rel;
      
       @JsonProperty("ABONOS_REL_PAGOS")
      public String abonos_Rel;
     

}
