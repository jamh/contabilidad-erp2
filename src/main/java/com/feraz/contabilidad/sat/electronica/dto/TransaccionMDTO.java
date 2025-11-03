/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransaccionMDTO {

    @JsonProperty("SEC")
    public String sec;
     @JsonProperty("CUENTA")
    public String cuenta;
     
      @JsonProperty("CARGOS")
    public String cargos;
      
       @JsonProperty("ABONOS")
    public String abonos;
       
        @JsonProperty("DESCRIPCION")
    public String descripcion;

    @JsonProperty("DES_CTA_NOM")
    public String desCta;

    
}
