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
public class ProvPagoUnicoDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("ID_MOV")
    public String idMov;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("RFC")
    public String rfc;
    
    @JsonProperty("F_ALTA")
    public String fAlta;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("NUMERO_CUENTA")
    public String numeroCuenta;
    
    @JsonProperty("CUENTA_CLABE")
    public String cuentaClabe;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("USUARIO")
    public String usuario;
    
    @JsonProperty("PAGO_ACUMULADO_CXP")
    public String pagoAcumuladoCxp;
    
    @JsonProperty("FECHA_PAGO_CXP")
    public String fechaPagoCxp;
    
    @JsonProperty("ESTATUS_CXP")
    public String estatusCxp;
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("NOM_ESTATUS_CXP")
    public String nomEstatusCxp;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("TIPO_CAMBIO")
    public String tipoCambio;


    
}
