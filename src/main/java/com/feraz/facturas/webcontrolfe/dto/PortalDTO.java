/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author LENOVO
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Ing JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortalDTO {
        
    @JsonProperty("COMPANIA")
    public String compania;
    
        @JsonProperty("DESCRIPCION_CANCELACION")
    public String descripcionCancelacion;
        
        @JsonProperty("FECHA_PAGO_CXP_FE")
    public String fechaPagoCxpFe;
        
        @JsonProperty("DESCRIPCION_PAGOS")
    public String descripcionnPagos;
        
        @JsonProperty("NUMERO")
    public String numero;
        
        @JsonProperty("FOLIO")
    public String folio;
        
        @JsonProperty("FECHA")
    public String fecha;
        
        @JsonProperty("SERIE")
    public String serie;
        
        @JsonProperty("SUBTOTAL")
    public String subtotal;
        
        @JsonProperty("TOTAL")
    public String total;
        
        @JsonProperty("TIPO_CAMBIO")
    public String tipoCambio;
        
        @JsonProperty("TIPO_DE_COMPROBANTE")
    public String tipoDeComprobante;
        
        @JsonProperty("CONDICIONES_DE_PAGO")
    public String condicionesDePago;
        
        @JsonProperty("DESCUENTO")
    public String descuento;
        
        @JsonProperty("MONEDA")
    public String moneda;
        
        @JsonProperty("MOTIVO_DESCUENTO")
    public String motivoDescuento;
        
        @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
        
        @JsonProperty("NUMERO_POL")
    public String numeroPol;
        
        @JsonProperty("FECHA_POL")
    public String fechaPol;
        
        @JsonProperty("XML")
    public String xml;
        
        @JsonProperty("PDF")
    public String pdf;
        
        @JsonProperty("DIR_PDF")
    public String dirPdf;
        
        @JsonProperty("DIR_XML")
    public String dirXml;
        
        @JsonProperty("RFC")
    public String rfc;
        
        @JsonProperty("UUID")
    public String uuid;
        
        @JsonProperty("FECHA_VENC_CXP")
    public String fechaVenc;
        
        @JsonProperty("MONEDA_PAGO")
    public String monedaPago;
        
        @JsonProperty("TOTAL_LETRA")
    public String totalLetra;
  
        @JsonProperty("ESTATUS_CXP")
    public String estatusCxp;
        
        @JsonProperty("RFC_EMPRESA")
    public String rfcEmpresa;
        
        @JsonProperty("BANCO")
    public String banco;
        
        @JsonProperty("CUENTA_CLABE")
    public String cuentaClabe;
        
        @JsonProperty("NUMERO_CUENTA")
    public String numeroCuenta;
        
        @JsonProperty("RAZON_SOCIAL")
    public String razonSocial;
        
        @JsonProperty("URL_COMP")
    public String urlComp;
        
        @JsonProperty("ORDEN_COMPRA")
    public String ordenCompra;
        
        @JsonProperty("ORDEN_RELACION")
    public String ordenRelacion;
	

  
        
}
