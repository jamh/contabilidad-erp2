/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class AuxNumCtaDetDTO {
        @JsonProperty("FECHA")
    public String FECHA;
        
        @JsonProperty("NUM_UNIDEN_POL")
    public String NUM_UNIDEN_POL;
        
        @JsonProperty("DEBE")
    public String DEBE;
        
        @JsonProperty("HABER")
    public String HABER;
        
         @JsonProperty("CONCEPTO")
    public String CONCEPTO;
    
}
