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

/**
 *
 * @author 55555
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErpFeCargaCfdiDTO {

    @JsonProperty("RFC_EMISOR")
    public String rfcEmisor;

 
    
    @JsonProperty("UUID")
    public String UUID;
    
    @JsonProperty("RFC_RECEPTOR")
    public String RFC_RECEPTOR;
    
    @JsonProperty("MET_PAGO_AUX")
    public String MET_PAGO_AUX;
    
    @JsonProperty("MONTO_TOTAL")
    public String MONTO_TOTAL;
    
    @JsonProperty("MONEDA")
    public String MONEDA;
    
    @JsonProperty("TIPO_CAMBIO")
    public String TIPO_CAMBIO;
    
   
    
    
}
