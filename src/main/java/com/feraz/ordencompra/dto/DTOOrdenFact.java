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
import com.feraz.cxp.dto.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTOOrdenFact {
    
     @JsonProperty("ORDEN_COMPRA")
    public String ordenCompra;
     
      @JsonProperty("COMPANIA")
    public String compania;
      
      @JsonProperty("NUMERO")
    public String numero;
      
      @JsonProperty("FECHA")
    public String fecha;
      @JsonProperty("SUBTOTAL")
    public String subtotal;
      @JsonProperty("TOTAL")
    public String total;
      @JsonProperty("IVA")
    public String iva;
      
      @JsonProperty("RFC_EMISOR")
    public String rfcEmisor;
      
      @JsonProperty("NOMBRE_EMISOR")
    public String nombreEmisor;
      
      @JsonProperty("RFC_RECEPTOR")
    public String rfcReceptor;
      
      @JsonProperty("NOMBRE_RECEPTOR")
    public String nombreReceptor;
      
      @JsonProperty("FOLIO")
    public String folio;
      
      @JsonProperty("MONEDA")
    public String moneda;
      
      @JsonProperty("FECHA_CAP_ORDEN")
    public String fechaCapOrden;
      
      @JsonProperty("RFC_ORDEN")
    public String rfcOrden;
      
      @JsonProperty("NOMBRE_PROV_ORDEN")
    public String nombreProvOrden;
      
      @JsonProperty("CONDICIONES_PAGO")
    public String condicionesPago;
      
      @JsonProperty("NOMBRE")
    public String nombre;
      
      @JsonProperty("DESCRIPCION")
    public String descripcion;
      
      @JsonProperty("CTO")
    public String cto;
      
      @JsonProperty("FECHA_ORDEN")
    public String fechaOrden;
      
      @JsonProperty("ESTATUS")
    public String estatus;
      
      @JsonProperty("USUARIO")
    public String usuario;
      
      @JsonProperty("USUARIO_AUTORIZO")
    public String usuarioAutorizo;
      
      @JsonProperty("FOLIO_ORDEN")
    public String folioOrden;
      
      @JsonProperty("NOM_ESTATUS_AUT")
    public String nomEstatusAut;
      
      @JsonProperty("ESTATUS_AUT")
    public String estatusAut;

      @JsonProperty("ARCHIVO")
    public String archivo;
      
    
}
