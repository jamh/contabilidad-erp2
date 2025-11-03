/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 *
 * @author FERAZ-14
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EdoxErpDTO {
    
    @JsonProperty("ID_EDO_CUENTA")
    public String idEdoCuenta;
    
    @JsonProperty("ID_ERP")
    public String idErp;
    
    @JsonProperty("T_DOC_ERP")
    public String tDocErp;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("RFC")
    public String rfc;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("TOTAL")
    public String total;
    
    @JsonProperty("FOLIO")
    public String folio;
    

        
    
}
