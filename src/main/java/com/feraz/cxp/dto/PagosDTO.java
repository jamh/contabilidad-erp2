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
public class PagosDTO {
    
     @JsonProperty("COMPANIA_DET")
    public String compania;
     
      @JsonProperty("NUMERO_FE_DET")
    public String numFe;
      
      @JsonProperty("FOLIO_DET")
    public String folio;
      
      @JsonProperty("SEC_DET")
    public String sec;
      
      @JsonProperty("USUARIO_DET")
    public String usuarioDet;
      
       @JsonProperty("T_OPERACION_DET")
    public String tOperacionDet;
        @JsonProperty("IMPORTE_OPERACION_DET")
    public String importeOperacionDet;
        
        @JsonProperty("BANCO_DET")
    public String banco;
        
         @JsonProperty("FECHA_PAGO_CXP_FE_DET")
    public String fechaPago;
         
          @JsonProperty("TOT_PESOS_DET")
    public String totPesos;

           @JsonProperty("OBSERVACIONES_DET")
    public String observaciones;
           
            @JsonProperty("PARIDAD_DET")
    public String paridad;
            
            @JsonProperty("CUENTA_BANCO")
    public String cuentaBanco;
            
             @JsonProperty("NOM_BANCO")
    public String nomBanco;
             
             
             @JsonProperty("ID_TRANSACCION")
    public String idTransaccion;
             
             @JsonProperty("ORIGEN")
    public String origen;
            
             @JsonProperty("BANCO_PAGO")
    public String bancoPago;
             
             @JsonProperty("CUENTA_PAGO")
    public String cuentaPago;
             
             @JsonProperty("MONEDA_PAGO")
    public String monedaPago;
             
             @JsonProperty("NOM_BANCO_PAGO")
    public String nomBancoPago;


            
             
            
      

}
