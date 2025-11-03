/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.dto;

/**
 *
 * @author LENOVO
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ComprobacionViaDTO {
    
    
    @JsonProperty("COMPANIA_COMP_VIA")
    public String compania;
    
    @JsonProperty("NUMERO_COMP_VIA")
    public String numero;
    
    @JsonProperty("FOLIO_COMP_VIA")
    public String folio;
    
    @JsonProperty("FECHA_COMP_VIA")
    public String fecha;
    
    @JsonProperty("TOTAL_COMP_VIA")
    public String total;
    
    @JsonProperty("RFC_COMP_VIA")
    public String rfc;
    
    @JsonProperty("NOMBRE_COMP_VIA")
    public String nombre;
    
    @JsonProperty("ESTATUS_CXP_COMP_VIA")
    public String estatusCxp;
    
    @JsonProperty("ORIGEN_COMP_VIA")
    public String origen;
    
    @JsonProperty("TIPO_GASTO_COMP_VIA")
    public String tipoGasto;
    
    @JsonProperty("NOMBRE_GASTO_COMP_VIA")
    public String nombreGasto;
    
    @JsonProperty("NUM_RELACION_COMP_VIA")
    public String numRel;
    
    @JsonProperty("SUBTOTAL_COMP_VIA")
    public String subtotal;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("CUENTA_CONTABLE")
    public String cuentaContable;
    
    @JsonProperty("NUMERO_FE")
    public String numeroFe;
    
    @JsonProperty("VISTO_BUENO")
    public String vistoBueno;
    
    @JsonProperty("MOT_VISTO_BUENO")
    public String motVistoBueno;
    
   
    
}
