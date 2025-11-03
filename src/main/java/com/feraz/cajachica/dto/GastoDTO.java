/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author LENOVO
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GastoDTO {
    
    
     @JsonProperty("COMPANIA")
    public String compania;
     
     @JsonProperty("ID_CAJA")
    public String idCaja;
     
     @JsonProperty("SEC")
    public String sec;
     
     @JsonProperty("ID_GASTO")
    public String idGasto;
     
     @JsonProperty("NOMBRE")
    public String nombre;
     
     @JsonProperty("DESCRIPCION")
    public String descripcion;
     
     @JsonProperty("MONEDA")
    public String moneda;

     @JsonProperty("IMPORTE")
    public String importe;
     
     @JsonProperty("TOT_COMP_CAJA_GAST")
    public String totCompCajaGast;


}
