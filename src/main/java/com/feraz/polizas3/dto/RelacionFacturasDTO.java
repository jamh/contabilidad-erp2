/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelacionFacturasDTO {


      
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("FOLIO")
    public String folio;
    
      @JsonProperty("NUMERO_FE")
    public String numeroFe;
      
      @JsonProperty("SUBTOTAL")
    public String subtotal;
      @JsonProperty("TOTAL")
    public String total;
      
       @JsonProperty("SERIE")
    public String serie;
      
      @JsonProperty("UUID")
    public String uuid;
      
      @JsonProperty("RFC_EMISOR")
    public String rfcEmisor;
      
       @JsonProperty("NOMBRE_EMISOR")
    public String nombreEmisor;
       
        @JsonProperty("NUMERO_POL")
    public String numeroPol;
    
          
       
     
    
}

