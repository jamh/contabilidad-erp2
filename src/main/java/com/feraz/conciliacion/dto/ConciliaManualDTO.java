/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dto;

/**
 *
 * @author FERAZ-14
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author 55555
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConciliaManualDTO {
    
     @JsonProperty("NUMERO")
     public String numero;
     
     @JsonProperty("FOLIO")
     public String folio;
     
     @JsonProperty("ORIGEN")
     public String origen;
     
     @JsonProperty("RFC")
     public String rfc;
     
     @JsonProperty("NOMBRE")
     public String nombre;
     
     @JsonProperty("FECHA_PAGO")
     public String fechaPago;
     
     @JsonProperty("PAGO")
     public String pago;
     
     @JsonProperty("UUID")
     public String uuid;
     
     @JsonProperty("EDO_CTA")
     public String edoCta;
   
     
     
     
    
}
