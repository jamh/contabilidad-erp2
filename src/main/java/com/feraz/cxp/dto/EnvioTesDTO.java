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
public class EnvioTesDTO {
    
  
        @JsonProperty("COMPANIA_FOL")
    public String compania;
        
        @JsonProperty("FOLIO_FOL")
    public String folioCxp;
        
        @JsonProperty("NUMERO_FOL")
    public String numero;
        
        @JsonProperty("FOLIO_FACT_FOL")
    public String folioFact;
        
        @JsonProperty("NOMBRE_FOL")
    public String nombre;
        
        @JsonProperty("RFC_FOL")
    public String rfc;
        
        @JsonProperty("ESTATUS_FOL")
    public String estatus;
        
        @JsonProperty("FECHA_FACT_FOL")
    public String fecha;
        
        @JsonProperty("TOTAL_FACT_FOL")
    public String total;
        
        @JsonProperty("ORIGEN_FOL")
    public String origenFol;
        
        @JsonProperty("CUENTA_CLABE_FOL")
    public String cuentasClabe;
        
        @JsonProperty("BANCO_FOL")
    public String bancoFol;
        
        @JsonProperty("PAGO_CIE_FOL")
    public String pagoCieFol;
        
        @JsonProperty("REFERENCIA_CIE_FOL")
    public String referenciaCieFol;
        
        @JsonProperty("CONCEPTO_CIE_FOL")
    public String conceptoCieFol;
        
        @JsonProperty("TOTAL_A_PAGAR_FOL")
    public String totalAPagarFol;
        
        @JsonProperty("IBAN_FOL")
    public String ibanFol;
        
        @JsonProperty("SWIFT_FOL")
    public String swiftFol;
        
        @JsonProperty("NUM_CTA_EXTRANJERA_FOL")
    public String numCtaExtranjera;
        
        @JsonProperty("BANCO_EXTRANJERO_FOL")
    public String bancoExtranjeroFol;
        
        @JsonProperty("CUENTA_TARJETA_FOL")
    public String cuentaTarjetaFol;
        
      
    
}
