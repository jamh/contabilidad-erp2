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
public class DetProrrateoDTO {
    
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("SEC")
    public String sec;
    
    @JsonProperty("CTO_CTO")
    public String ctoCto;
    
    @JsonProperty("PORCENTAJE")
    public String porcentaje;
    
    @JsonProperty("IMPORTE_PRORRATEADO")
    public String importeProrrateo;
    
    @JsonProperty("TOTAL_CONCEPTO")
    public String totalConcepto;
    


}
