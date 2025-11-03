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
public class OrdenDetDTO {
    
      @JsonProperty("ID")
      public String id;
      
      @JsonProperty("LINEA")
      public String linea;
      
      @JsonProperty("ID_ORDEN_COMPRA")
      public String idOrdenCompra;
      
      @JsonProperty("COMPANIA")
      public String compania;
      
      @JsonProperty("ID_PRODUCTO")
      public String idProducto;
      
      @JsonProperty("DESCRIPCION")
      public String descripcion;
      
      @JsonProperty("CANTIDAD_PEDIDA")
      public String cantidadPedida;
      
      @JsonProperty("CANTIDAD_LLEGO")
      public String cantidadLlego;
      
      @JsonProperty("PRECIO_UNITARIO")
      public String precioUnitario;
      
      @JsonProperty("IVA")
      public String iva;
      
      @JsonProperty("TOTAL")
      public String total;
      
      @JsonProperty("IMPORTE_COTIZACION")
      public String importeCotizacion;
      
      @JsonProperty("ID_ALMACEN")
      public String idAlmacen;
      
      @JsonProperty("ID_ESTATUS")
      public String idEstatus;
      
      @JsonProperty("ID_PRIORIDAD")
      public String idPrioridad;
      
      @JsonProperty("ESTATUS_LINEA")
      public String estatusLinea;
      
      @JsonProperty("ID_PEDIDO")
      public String idPedido;
      
        @JsonProperty("IMPORTE_COTIZACION_SUB")
        public String importeCotizacionSub;
        @JsonProperty("IMPORTE_COTIZACION_IVA")
        public String importeCotizacionIva;
        @JsonProperty("DESCUENTO")
        public String descuento;
        @JsonProperty("ID_PROYECTO")
        public String idProyecto;
        
        @JsonProperty("ID_MONEDA")
        public String idMoneda;
        
        
        @JsonProperty("TASA_IVA")
        public String tasaIva;
        
        @JsonProperty("RETENCION")
        public String retencion;
        
        @JsonProperty("TIPO_PRODUCCION")
        public String tipoProduccion;
        
        @JsonProperty("DESCUENTO_PROV")
        public String descuentoProv;
        
        @JsonProperty("FOLIO_PEDIDO")
        public String folioPedido;
        
        
        @JsonProperty("USUARIO_PEDIDO_AUT")
        public String usuarioPedidoAut;
        
        @JsonProperty("USUARIO_PEDIDO")
        public String usuarioPedido;
            
       
        @JsonProperty("DESCUENTO_FACT")
        public String descuentoFact;
        
        @JsonProperty("ID_GERENCIA")
        public String idGerencia;
        
        @JsonProperty("ID_FAMILIA")
        public String idFamilia;
        
        @JsonProperty("ID_AUTORIZACION")
        public String idAutorizacion;
        
        @JsonProperty("ID_AUTORIZACION_NOM")
        public String idAutorizacionNom;
        
        @JsonProperty("FECHA_REQUERIDA_DET")
        public String fechaRequeridaDet;
        @JsonProperty("IEPS")
        public String ieps;
        
        @JsonProperty("TIPO_GASTO")
        public String tipoGasto;
        
        @JsonProperty("T_G_DESCRIPCION")
        public String tgDescripcion;
        
        @JsonProperty("GRUPO_GASTO")
        public String grupoGasto;
        
        @JsonProperty("AVION_CC")
        public String avionCC;
        
        
        
        @JsonProperty("ESTACION")
        public String estacion;
        
        @JsonProperty("AVION_CC_NOM")
        public String avionCCnom;
        
        @JsonProperty("ESTACION_NOM")
        public String estacionNom;
        
        @JsonProperty("GRUPO_GASTO_NOM")
        public String grupoGastoNom;
        
        @JsonProperty("TIPO_GASTO_NOM")
        public String tipoGastoNom;
        
        @JsonProperty("T_G_DESCRIPCION_NOM")
        public String tgDescripcionNom;
        
        @JsonProperty("ID_GERENCIA_NOM")
        public String idGerenciaNom;
        
        @JsonProperty("RETENCION2")
        public String retencion2;
        
        @JsonProperty("DESCUENTO2")
        public String descuento2;
        
        @JsonProperty("DEPARTAMENTO")
        public String departamento;
 

    
        
        
     
    
}
