/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuxNumCtaDTO {
    
        @JsonProperty("NUM_CTA")
    public String cuenta;
        
        @JsonProperty("DES_CTA")
    public String desCta;
        
        @JsonProperty("SALDO_INI")
    public String saldoIni;
        
        @JsonProperty("SALDO_FIN")
    public String saldoFin;
    
}
