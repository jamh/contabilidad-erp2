/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dto;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolizasArchivosDTO {
      
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("SEC")
    public String sec;
    
      @JsonProperty("PATH")
    public String path;
      @JsonProperty("TIPO")
    public String tipo;
      
      @JsonProperty("URL")
    public String url;
      
       @JsonProperty("NOMBRE")
    public String nombre;
       
       @JsonProperty("DESCRIPCION")
    public String descripcion;
       
    
}
