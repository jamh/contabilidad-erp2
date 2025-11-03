/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CajaDispDTO {
    
    @JsonProperty("MONTO_RESTANTE")
    public String montoRestante;
    
    @JsonProperty("USUARIO_CAJA")
    public String usuarioCaja;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("ID_CAJA")
    public String idCaja;
    
    @JsonProperty("SEC")
    public String sec;
    
    

}
