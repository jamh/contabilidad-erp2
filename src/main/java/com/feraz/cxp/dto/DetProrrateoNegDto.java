/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author LENOVO
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetProrrateoNegDto {
    
    @JsonProperty("COMPANIA_NEG")
    public String compania;
    
    @JsonProperty("NUMERO_NEG")
    public String numero;
    
    @JsonProperty("SEC_NEG")
    public String sec;
    
    @JsonProperty("CTO_CTO_NEG")
    public String ctoCto;
    
    @JsonProperty("PORCENTAJE_NEG")
    public String porcentaje;
    
    @JsonProperty("IMPORTE_PRORRATEADO_NEG")
    public String importeProrrateado;
    
    @JsonProperty("TOTAL_CONCEPTO_NEG")
    public String totalConcepto;
    
    @JsonProperty("TIPO_NEGOCIO_NEG")
    public String tipoNegocio;
    
    @JsonProperty("NOM_TIPO_NEGOCIO_NEG")
    public String nomTipoNegocio;
    
    @JsonProperty("TIPO_NEG")
    public String tipo;
    
    @JsonProperty("ID_FAMILIA")
    public String idFamilia;
    
    
    
    
}
