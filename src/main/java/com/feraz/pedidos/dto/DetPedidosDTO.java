/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author LENOVO
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetPedidosDTO {
    
    @JsonProperty("COMPANIA")
      public String compania;
    
    @JsonProperty("ID")
      public String id;
    
    @JsonProperty("ID_PRODUCTO")
      public String idProducto;
    
    @JsonProperty("NOM_PRODUCTO")
      public String nomProducto;
    
    @JsonProperty("CANTIDAD")
      public String cantidad;
   
    @JsonProperty("CTO_CTO")
      public String ctoCto;
    
    @JsonProperty("ESTATUS")
      public String estatus;
    
    @JsonProperty("FECHA_ENTREGA")
      public String fechaEntrega;
    
    @JsonProperty("SEC")
      public String sec;
    
    @JsonProperty("AUTORIZO")
      public String autorizo;
    
    @JsonProperty("CONC_GASTO")
      public String concGasto;
    
    @JsonProperty("DESCRIPCION")
      public String descripcion;
    
    @JsonProperty("SID")
      public String sid;
    
    @JsonProperty("ID_PROYECTO")
      public String idProyecto;
    
    @JsonProperty("NOM_PROYECTO")
      public String nomProyecto;
    
    @JsonProperty("TIPO_PEDIDO")
      public String tipoPedido;
    
    @JsonProperty("CLASIFICACION")
      public String clasificacion;
    
    @JsonProperty("NOM_CLASIFICACION")
      public String nomClasificacion;
    
    @JsonProperty("ID_PROVEEDOR")
      public String idProveedor;
    
    @JsonProperty("FOLIO_MAESTRO")
      public String folioMaestro;

    @JsonProperty("TIPO_PRODUCCION")
      public String tipoProduccion;
    
    @JsonProperty("COSTO_UNITARIO")
      public String costoUnitario;
    
    @JsonProperty("MONTO_TOTAL")
      public String montolTotal;
    
    @JsonProperty("ID_TIPO_NEGOCIO")
      public String idTipoNegocio;
      
    @JsonProperty("MONEDA")
      public String moneda;
    
    @JsonProperty("UNIDAD_MEDIDA")
      public String unidadMedida;
    
    @JsonProperty("LUGAR_ENTREGA")
      public String lugarEntrega;
    
    @JsonProperty("IMP_SEGUIMIENTO")
      public String impSeguimiento;
    
    @JsonProperty("PERIODO_USO")
      public String periodoUso;
    
    @JsonProperty("ID_TIPO_NEGOCIO_NOM")
      public String idTipoNegocioNom;
    
    @JsonProperty("UNIDAD_MEDIDA_NOM")
      public String unidadMedidaNom;
    
    @JsonProperty("LUGAR_ENTREGA_NOM")
      public String lugarEntregaNom;
    
    @JsonProperty("IMP_SEGUIMIENTO_NOM")
      public String impSeguimientoNom;
    
    @JsonProperty("PERIODO_USO_NOM")
      public String periodoUsoNom;
    
    @JsonProperty("TIPO")
      public String tipo;
        
        
      
        
    
}
