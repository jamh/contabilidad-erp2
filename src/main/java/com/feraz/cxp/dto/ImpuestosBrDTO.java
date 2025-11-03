/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author FERAZ-14
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpuestosBrDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("IMPUESTO")
    public String impuesto;
    
    @JsonProperty("TIPO_IMPUESTO")
    public String tipoImpuesto;
    
    @JsonProperty("ESTADO")
    public String estado;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("NOMBRE_TIPO")
    public String nombreTipo;
    
    @JsonProperty("NOMBRE_ESTADO")
    public String nombreEstado;
    

    
}
