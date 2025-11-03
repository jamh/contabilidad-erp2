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
public class PedidosDetDTO {
    
    
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
        
           @JsonProperty("TIPO_PEDIDO")
  public String tipoPedido;
           
           @JsonProperty("TIPO_PRODUCCION")
  public String tipoProduccion;
           
           @JsonProperty("COSTO_UNITARIO")
  public String costoUnitario;
           
           @JsonProperty("MONTO_TOTAL")
  public String montoTotal;

           @JsonProperty("ID_FAMILIA")
  public String idFamilia;

           
           @JsonProperty("NOM_ID_FAMILIA")
  public String nomIdFamilia;

           
           @JsonProperty("ID_AREA")
  public String idArea;

           
           @JsonProperty("NOM_AREA")
  public String nomArea;
           
           @JsonProperty("MONEDA")
  public String moneda;

                 @JsonProperty("TIPO_GASTO")
        public String tipoGasto;
        
        @JsonProperty("T_G_DESCRIPCION")
        public String tGDescripcion;
        
        @JsonProperty("GRUPO_GASTO")
        public String grupoGasto;
        
        @JsonProperty("AVION_CC")
        public String avionCC;
        
        @JsonProperty("ESTACION")
        public String estacion;
        
        @JsonProperty("DEPARTAMENTO")
        public String departamento;
}
