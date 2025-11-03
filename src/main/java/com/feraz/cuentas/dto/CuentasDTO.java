/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class CuentasDTO {


      @JsonProperty("ESTRUCTURA")
    public String estructura;
      
      @JsonProperty("CUENTA")
    public String cuenta;
      
      @JsonProperty("NOMBRE")
    public String nombre;
       
      @JsonProperty("AFECTABLE")
    public String afectable;
      
      @JsonProperty("CIERRE")
    public String cierre;

     
       @JsonProperty("ESTATUS")
    public String estatus;
        
      
        @JsonProperty("NATURALEZA")
    public String naturaleza;
        
       
     @JsonProperty("CLAVE_PRESUP")
    public String clavePresup;
     
     
      @JsonProperty("TIPO")
    public String tipo;
      
       @JsonProperty("MAYOR")
    public String mayor;
  

       @JsonProperty("CTA_COMPLEMENTARIA")
    public String ctaComplementaria;
       
       @JsonProperty("DIVISA")
    public String divisa;

        @JsonProperty("CUENTA_SAT")
    public String cuentaSat;
        
        @JsonProperty("CUENTA_ALIAS")
    public String cuentaAlias;
   
      
     @JsonProperty("SALDO")
    public String saldo;
    
     
      @JsonProperty("DET_POLIZA")
    public String detPoliza;

      @JsonProperty("ID")
    public String id;

     @JsonProperty("BANCO")
    public String banco;
     
     @JsonProperty("CUENTA_PADRE")
    public String cuentaPadre;
     
     @JsonProperty("SALDO_NUM")
    public String saldoNum;
     
    

    
}
