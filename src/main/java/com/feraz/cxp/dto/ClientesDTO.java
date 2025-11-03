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

public class ClientesDTO {
    
     @JsonProperty("COMPANIA")
     public String compania;
     
     @JsonProperty("ID_CLIENTE")
     public String idCliente;
     
     @JsonProperty("ORIGEN")
     public String origen;
     
     @JsonProperty("NOMBRE")
     public String nombre;
     
     @JsonProperty("RFC")
     public String rfc;
     
     @JsonProperty("ACT_ECONOMICA")
     public String actEconomica;
     
     @JsonProperty("F_ALTA")
     public String fAlta;
     
     @JsonProperty("RAZON_SOCIAL")
     public String razonSocial;
 
     @JsonProperty("PAPELERIA")
     public String papeleria;
     
     @JsonProperty("T_PERSONA")
     public String tPersona;
     
     @JsonProperty("T_CLIEPROV")
     public String tClieprov;
     
     @JsonProperty("T_TERCERO")
     public String tTercero;
     
     @JsonProperty("T_OPERACION")
     public String tOperacion;
     
     @JsonProperty("ID_FISCAL")
     public String idFiscal;
     
     @JsonProperty("NOMBRE_EXTRANJERO")
     public String nombreExtranjero;
     
     @JsonProperty("PAIS_RESIDENCIA")
     public String paisResidencia;
     
     @JsonProperty("NACIONALIDAD")
     public String nacionalidad;
     
     @JsonProperty("AUXILIAR")
     public String auxiliar;
     
     @JsonProperty("CTO_CTO")
     public String ctoCto;
     
     @JsonProperty("TIPO_INGRESO")
     public String tipoIngreso;
     
     @JsonProperty("CUENTA")
     public String cuenta;
     
     @JsonProperty("BANCO")
     public String banco;
     
     @JsonProperty("NUMERO_CUENTA")
     public String numeroCuenta;
     
     @JsonProperty("CUENTA_CLABE")
     public String cuentaClabe;
     
     @JsonProperty("CORREO")
     public String correo;
     
     @JsonProperty("MGS_ERR")
     public String msgErr;
   

    
}
