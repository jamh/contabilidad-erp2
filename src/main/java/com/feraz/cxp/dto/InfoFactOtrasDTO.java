/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoFactOtrasDTO {
    
    @JsonProperty("COMISION")
    public String comision;
    
    @JsonProperty("SEC")
    public String sec;
    
     @JsonProperty("COMPANIA_REL_INFO")
    public String compania;
    
    @JsonProperty("NUMERO_REL_INFO")
    public String numero;
  
    @JsonProperty("FOLIO_REL_INFO")
    public String folio;
  
    @JsonProperty("FECHA_REL_INFO")
    public String fecha;
    
    @JsonProperty("TOTAL_REL_INFO")
    public String total;
    
    @JsonProperty("RFC_REL_INFO")
    public String rfc;

    @JsonProperty("NOMBRE_REL_INFO")
    public String nombre;
    
    @JsonProperty("ESTATUS_CXP_REL_INFO")
    public String estatusCxp;
    
    @JsonProperty("ORIGEN_REL_INFO")
    public String origen;
    
    @JsonProperty("TIPO_GASTO_REL_INFO")
    public String tipoGasto;
    
    @JsonProperty("NOMBRE_GASTO_REL_INFO")
    public String nombreGasto;
    
    @JsonProperty("NUM_RELACION_FE_INFO")
    public String numRelacion;
    
    @JsonProperty("SUBTOTAL_REL_INFO")
    public String subtotal;
    
    @JsonProperty("URL")
    public String url;


    
 
    
}
