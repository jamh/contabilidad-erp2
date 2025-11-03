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
public class PedidosDTO {
    
    
    @JsonProperty("COMPANIA")
      public String compania;
    
    
    @JsonProperty("ID")
      public String id;
    
    @JsonProperty("ID_PRODUCTO")
      public String idProducto;
    
    @JsonProperty("CANTIDAD")
      public String cantidad;
    
    @JsonProperty("USUARIO")
      public String usuario;
    
    @JsonProperty("CTO_CTO")
      public String ctoCto;
    
    @JsonProperty("FECHA_CAP")
      public String fechaCap;
    
    @JsonProperty("ESTATUS")
      public String estatus;
    
    @JsonProperty("FECHA_CANCELA")
      public String fechaCancela;
    
    @JsonProperty("MOTIVO_CANCELA")
      public String motivoCancela;
    
    @JsonProperty("FECHA_ENTREGA")
      public String fechaEntrega;
    
    
    @JsonProperty("SEC")
      public String sec;
    
     @JsonProperty("AUTORIZO")
      public String autorizo;
     
      @JsonProperty("NOMBRE_PRODUCT")
      public String nombreProduct;
      
       @JsonProperty("CONC_GASTO")
      public String concGasto;
       
        @JsonProperty("NOMBRE_CONCEPTO")
      public String nombreConcepto;

        @JsonProperty("DESCRIPCION")
      public String descripcion;
        
          @JsonProperty("ID_PROYECTO")
  public String idProyecto;
        
          @JsonProperty("NOM_ID_FAMILIA")
  public String nomServicio;
          
          @JsonProperty("ID_AREA")
  public String idArea;
          
          @JsonProperty("NOM_AREA")
  public String nomArea;
          
          @JsonProperty("ID_FAMILIA")
  public String idFamilia;
          
        @JsonProperty("COSTO_UNITARIO")
          public String costoUnitario;
        
        @JsonProperty("MONEDA")
          public String moneda;

        
    
}
