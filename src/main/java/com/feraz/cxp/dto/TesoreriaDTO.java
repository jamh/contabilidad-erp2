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
public class TesoreriaDTO {
    
    
      @JsonProperty("COMPANIA_TES")
    public String compania;
      
      @JsonProperty("NUMERO_TES")
    public String numero;
      
      @JsonProperty("FOLIO_TES")
    public String folio;
      
      @JsonProperty("FECHA_TES")
    public String fecha;
      
      @JsonProperty("TOTAL_TES")
    public String total;
      
      @JsonProperty("MONEDA_TES")
    public String moneda;
      
      @JsonProperty("RFC_EMISOR_TES")
    public String rfcEmisor;
      
      @JsonProperty("NOMBRE_EMISOR_TES")
    public String nombreEmisor;
      
      @JsonProperty("RFC_PROV_TES")
    public String rfcProv;
      
      @JsonProperty("NOMBRE_PROV_TES")
    public String nombreProv;
      
      @JsonProperty("BANCO_PROV")
    public String bancoProv;
      
      @JsonProperty("CTA_CLABE_PROV")
    public String ctaClabeProv;
      
      @JsonProperty("MONEDA_PAGO_TES")
    public String monedaPago;
      
       @JsonProperty("PAGO_CIE")
    public String pagoCie;
       
       @JsonProperty("REFERENCIA_CIE")
    public String referenciaCie;
       
       @JsonProperty("PAGO_CIE_COMPROBANTES")
    public String pagoCieComprobantes;
       
       @JsonProperty("REFERENCIA_CIE_COMPROBANTES")
    public String referenciaCieComprobantes;
       
       @JsonProperty("CIE")
    public String cie;
       
       @JsonProperty("CONCEPTO_CIE")
    public String conceptoCie;
       
       @JsonProperty("ORIGEN")
    public String origen;
       
       @JsonProperty("TIPO_PAGO_TES")
    public String tipoPagoTes;
            
            @JsonProperty("TOTAL_PAGAR_TES")
    public String totalAPagar;
            
            @JsonProperty("PORCENTAJE_PAGO_TES")
    public String porcentajePago;
            
            @JsonProperty("PAGO_ACUMULADO_CXP_TES")
    public String pagoAcumuladoCxpTes;
            
            @JsonProperty("IBAN")
    public String iban;
            
            @JsonProperty("SWIFT")
    public String swift;
            
            @JsonProperty("NUM_CTA_EXTRANJERA")
    public String numCtaExtranjero;
            
            @JsonProperty("BANCO_EXTRANJERO")
    public String bancoExtranjero;
            
            
            @JsonProperty("CUENTA_TARJETA")
    public String cuentaTarjeta;
            
             @JsonProperty("TIPO_GASTO")
    public String tipoGasto;
            
 
       
            
            
            
            
            
          



}
