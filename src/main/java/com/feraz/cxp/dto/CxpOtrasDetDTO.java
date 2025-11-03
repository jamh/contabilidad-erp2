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
public class CxpOtrasDetDTO {
    

    @JsonProperty("COMPANIA_CXP_DET")
    public String compania;
    
    
    @JsonProperty("ID_CXP_DET")
    public String id;
    
    @JsonProperty("SEC_CXP_DET")
    public String sec;
    
    @JsonProperty("IMPORTE_CXP_DET")
    public String importe;
    
    @JsonProperty("ID_TIPO_GASTO_CXP_DET")
    public String idTipoGasto;
    
    @JsonProperty("ID_TIPO_NEGOCIO_CXP_DET")
    public String idTipoNegocio;
    
    @JsonProperty("ID_PAIS_CXP_CXP_DET")
    public String idPaisCxp;
    
    @JsonProperty("ID_SERVICIO_CXP_DET")
    public String idServicio;
    
    @JsonProperty("CTO_CXP_CXP_DET")
    public String ctoCxp;
    
    @JsonProperty("SERVICIO_CXP_DET")
    public String servicioCxp;
    
    
}
