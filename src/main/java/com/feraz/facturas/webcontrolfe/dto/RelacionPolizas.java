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
import java.util.Date;

/**
 *
 * @author Ing JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelacionPolizas {
    
      @JsonProperty("COMPANIA")
    public String compania;
      
      @JsonProperty("NUMERO")
    public String numero;
      
      @JsonProperty("UUID")
    public String uuid;
       
      @JsonProperty("FOLIO")
    public String folio;
      
      @JsonProperty("FOLIO_FISCAL_ORIG")
    public String folioFiscalOrig;
      
       @JsonProperty("FECHA")
    public String fecha;
       
        @JsonProperty("FECHA_FOLIO_FISCAL_ORIG")
    public String fechaFolioFiscalOrig;
       
     @JsonProperty("SERIE")
    public String serie;
         
      @JsonProperty("SERIE_FOLIO_FISCAL_ORIG")
    public String serieFolioFiscalOrig;
       
       @JsonProperty("SUBTOTAL")
    public String subtotal;
       
       @JsonProperty("TOTAL")
    public String total;
       
       @JsonProperty("TIPO_CAMBIO")
    public String tipoCambio;
       
        @JsonProperty("TIPO_DE_COMPROBANTE")
    public String tipoComprobante;
        
        @JsonProperty("CONDICIONES_DE_PAGO")
    public String condicionesPago;
        
        
     @JsonProperty("DESCUENTO")
    public String descuento;
     
      @JsonProperty("FORMA_DE_PAGO")
    public String formaPago;
      
      @JsonProperty("METODO_DE_PAGO")
    public String metodoPago;
      
     @JsonProperty("MONEDA")
    public String moneda;
     
     @JsonProperty("MONTO_FOLIO_FISCAL_ORIG")
    public String montoFolioFiscalOrig;
     
     @JsonProperty("LUGAR_EXPEDICION")
    public String lugarExpedicion;
     
     @JsonProperty("MOTIVO_DESCUENTO")
    public String motivoDescuento;
       
      @JsonProperty("NO_CERTIFICADO")
    public String noCertificado;
      
       @JsonProperty("NUM_CTA_PAGO")
    public String numCtaPago;
  
     
       @JsonProperty("NO_CASO")
    public String noCaso;
       
       @JsonProperty("SELLO")
    public String sello;
       
        @JsonProperty("VERSION")
    public String version;
       
       @JsonProperty("CERTIFICADO")
    public String certificado;
      
      @JsonProperty("IDCONCGASTO")
    public String idConCGasto;
      
       @JsonProperty("NOMIDCONCGASTO")
    public String nomiIdConCGasto;
       
      @JsonProperty("ORIGEN")
    public String origen;
      
      @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
      
      @JsonProperty("NUMERO_POL")
    public String numeroPol;
      
      @JsonProperty("FECHA_POL")
    public String fechaPol;
      
     @JsonProperty("XML")
    public String xml;
     
      @JsonProperty("FECHA_CAP")
    public String fechaCap;
      
       @JsonProperty("DESCRIPCION")
    public String descripcion;
       
      @JsonProperty("RFC_EMISOR")
    public String efcEmisor;
      
      @JsonProperty("NOMBRE_EMISOR")
    public String nombreEmisor;
      
       @JsonProperty("RFC_RECEPTOR")
    public String rfcReceptor;
      

      
       @JsonProperty("FLAG_POLIZA")
    public String flagPoliza;
       
       
       
}
