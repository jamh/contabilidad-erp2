/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RelacionCxpDTO {
    
    
     @JsonProperty("COMPANIA_FACT")
      public String compania;
     
      @JsonProperty("NUMERO_FACT")
      public String numero;
      
       @JsonProperty("FOLIO_FACT")
      public String folio;
       
      @JsonProperty("FECHA_FACT")
      public String fecha;
      
      @JsonProperty("SERIE_FACT")
      public String serie;
      
      @JsonProperty("SUBTOTAL_FACT")
      public String subtotal;
      
      @JsonProperty("TOTAL_FACT")
      public String total;
      
      @JsonProperty("RFC_EMISOR_FACT")
      public String emisor;
      
      @JsonProperty("NOMBRE_EMISOR_FACT")
      public String nombre;
      
      @JsonProperty("NUM_FACTURA_REL")
      public String numFact;
      
      @JsonProperty("DOC_SEC_REL")
      public String secDoc;
      
      @JsonProperty("NUM_DOCUMENTO_REL")
      public String numDoc;
      
      @JsonProperty("FACT_SEC_REL")
      public String secFact;
      
      @JsonProperty("IMPORTE_FACT")
      public String importeFact;
      
      @JsonProperty("IMPORTE_DOC")
      public String importeDoc;
      
      @JsonProperty("TIPO_DE_COMPROBANTE_FACT")
      public String tipoComprobanteFact;
      
      @JsonProperty("SALDOS_CXP_FACT")
      public String saldoCxpFact;
      
      @JsonProperty("TOTAL_SIN_MOV")
      public String totalSinMov;
      
      
      
 

}
