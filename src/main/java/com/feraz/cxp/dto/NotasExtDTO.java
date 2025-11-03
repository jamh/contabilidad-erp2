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
public class NotasExtDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("ID")
    public String id;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("BENEFICIARIO")
    public String beneficiario;
    
    @JsonProperty("TOTAL")
    public String total;
    
    @JsonProperty("ID_REL")
    public String idRel;
    
    @JsonProperty("ID_FACT")
    public String idFact;
    
    @JsonProperty("IMP_APLICA_NOTA")
    public String impAplicaNota;

      
    
}
