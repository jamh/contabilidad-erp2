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
public class RelacionFactXOtrasDTO {
    
    @JsonProperty("COMPANIA_REL")
    public String compania;
    
    @JsonProperty("NUMERO_REL")
    public String numero;
    
    @JsonProperty("FOLIO_REL")
    public String folio;
    
    @JsonProperty("FECHA_REL")
    public String fechaRel;
    
    @JsonProperty("TOTAL_REL")
    public String total;
    
    @JsonProperty("SUBTOTAL_REL")
    public String subTotal;
    
    
    @JsonProperty("RFC_REL")
    public String rfc;
    
    @JsonProperty("NOMBRE_REL")
    public String nombre;
    
    @JsonProperty("ESTATUS_CXP_REL")
    public String estatusCxp;
    
    @JsonProperty("ORIGEN_REL")
    public String origen;
    
    @JsonProperty("TIPO_GASTO_REL")
    public String tipoGasto;
    
    @JsonProperty("NOMBRE_GASTO_REL")
    public String nombreGasto;
    
    @JsonProperty("NUM_RELACION_FE")
    public String numRelacionFe;
    
    
    @JsonProperty("TIPO_GASTO_ORIGEN")
    public String tipoGastoOrigen;
    
    
    
 
    
}
