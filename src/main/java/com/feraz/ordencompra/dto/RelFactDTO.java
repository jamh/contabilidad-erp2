/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelFactDTO {
    
    
    @JsonProperty("NUMERO_FE")
      public String numeroFe;
    
    @JsonProperty("ORIGEN")
      public String origen;
    
    @JsonProperty("FECHA")
      public String fecha;
    
    @JsonProperty("NOM_PROVEEDOR")
      public String nomProveedor;
    
    @JsonProperty("MONEDA")
      public String moneda;
    
    @JsonProperty("SUBTOTAL")
      public String subtotal;
    
    @JsonProperty("IVA")
      public String iva;
    
    @JsonProperty("TOTAL")
      public String total;
    
    @JsonProperty("CONCEPTO")
      public String concepto;
    
    @JsonProperty("ID_ORDEN")
      public String idOrden;
    
    @JsonProperty("ID_ORDEN_DET")
      public String idOrdenDet;
    
    @JsonProperty("ARCHIVO")
      public String archivo;
    
    @JsonProperty("COMPANIA")
      public String compania;
    
    @JsonProperty("ARCHIVO_ORDEN")
      public String archivoOrden;
    
    @JsonProperty("ESTATUS_CXP")
      public String estatusCXP;
    
    @JsonProperty("ESTATUS_CXP2")
      public String estatusCXP2;

    

}
