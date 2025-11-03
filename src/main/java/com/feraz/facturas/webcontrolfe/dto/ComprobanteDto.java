/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Ing JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComprobanteDto {

    @JsonProperty("COMPANIA")
    public String COMPANIA;
    @JsonProperty("NUMERO")
    public String NUMERO;
    @JsonProperty("FOLIO")
    public String FOLIO;
    @JsonProperty("FOLIO_FISCAL_ORIG")
    public String FOLIO_FISCAL_ORIG;
    @JsonProperty("FECHA")
    public String FECHA;
    @JsonProperty("FECHA_FOLIO_FISCAL_ORIG")
    public String FECHA_FOLIO_FISCAL_ORIG;

    @JsonProperty("SERIE_FOLIO_FISCAL_ORIG")
    public String SERIE_FOLIO_FISCAL_ORIG;
    @JsonProperty("SUBTOTAL")
    public String SUBTOTAL;
    @JsonProperty("TOTAL")
    public String TOTAL;
    @JsonProperty("TIPO_CAMBIO")
    public String TIPO_CAMBIO;
    @JsonProperty("TIPO_DE_COMPROBANTE")
    public String TIPO_DE_COMPROBANTE;
    @JsonProperty("CONDICIONES_DE_PAGO")
    public String CONDICIONES_DE_PAGO;
    @JsonProperty("DESCUENTO")
    public String DESCUENTO;
    @JsonProperty("FORMA_DE_PAGO")
    public String FORMA_DE_PAGO;
    @JsonProperty("METODO_DE_PAGO")
    public String METODO_DE_PAGO;
    @JsonProperty("MONEDA")
    public String MONEDA;
    @JsonProperty("MONTO_FOLIO_FISCAL_ORIG")
    public String MONTO_FOLIO_FISCAL_ORIG;
    @JsonProperty("LUGAR_EXPEDICION")
    public String LUGAR_EXPEDICION;
    @JsonProperty("MOTIVO_DESCUENTO")
    public String MOTIVO_DESCUENTO;
    @JsonProperty("NO_CERTIFICADO")
    public String NO_CERTIFICADO;
    @JsonProperty("NUM_CTA_PAGO")
    public String NUM_CTA_PAGO;
    @JsonProperty("SELLO")
    public String SELLO;
    @JsonProperty("VERSION")
    public String VERSION;
    @JsonProperty("CERTIFICADO")
    public String CERTIFICADO;
    @JsonProperty("IDCONCGASTO")
    public String IDCONCGASTO;
    @JsonProperty("NOMIDCONCGASTO")
    public String NOMIDCONCGASTO;
    @JsonProperty("ORIGEN")
    public String ORIGEN;
    @JsonProperty("TIPO_POLIZA")
    public String TIPO_POLIZA;
    @JsonProperty("NUMERO_POL")
    public String NUMERO_POL;
    @JsonProperty("FECHA_POL")
    public String FECHA_POL;
    @JsonProperty("XML")
    public String XML;
    @JsonProperty("RFC")
    public String rfc;
    @JsonProperty("FECHA_CAP")
    public String FECHA_CAP;
    @JsonProperty("DESCRIPCION")
    public String DESCRIPCION;
    @JsonProperty("RFC_EMISOR")
    public String RFC_EMISOR;
    @JsonProperty("RFC_COMPANIA")
    public String RFC_COMPANIA;
    
     @JsonProperty("TIPO")
    public String TIPO;
     
     @JsonProperty("CUENTA_CONTABLE")
    public String CUENTA_CONTABLE;
     
      @JsonProperty("AUXILIAR_CONTABLE")
    public String AUXILIAR_CONTABLE;
    
    @JsonProperty("NOMBRE_EMISOR")
    public String NOMBRE_EMISOR;
    @JsonProperty("FLAG_POLIZA")
    public String FLAG_POLIZA;
    
    @JsonProperty("UUID")
    public String uuid;
    
     @JsonProperty("PDF")
    public String pdf;
    
    @JsonProperty("NO_CASO")
    public String noCaso;
    
    @JsonProperty("CXP_FECHA")
    public String cxpFecha;
    
     @JsonProperty("FECHA_CONTAB_PROV")
    public String fechaContabProv;
    
    @JsonProperty("NOMCC")
    public String nomCC;
    
    @JsonProperty("CONCEPTO")
    public String concepto;
    
    @JsonProperty("TIPO_CXP")
    public String tipoCxp;
    
     @JsonProperty("TIPO_GASTO")
    public String tipoGasto;
     
      @JsonProperty("BENEFICIARIO")
    public String beneficiario;
      
       @JsonProperty("OTROS_IMPUESTOS")
    public String otrosImpuestos;
       
       @JsonProperty("FOLIO_PAGOS")
    public String folioPagos;
       
       @JsonProperty("PAGO_CIE")
    public String pagoCie;
       
       @JsonProperty("REFERENCIA_CIE")
    public String referenciaCie;
       
       @JsonProperty("NUM_REL_OTRAS")
    public String numeroRelOtras;
       
       @JsonProperty("ID_PROYECTO")
    public String idProyecto;
       
       @JsonProperty("ID_TIPO_GASTO")
    public String idTipoGasto;
     
        @JsonProperty("DEDUCIBLE")
    public String deducible;
     
       
         @JsonProperty("IMPORTE_DEDUCIBLE")
    public String importeDeducible;
         
         @JsonProperty("ID_PAIS_CXP")
    public String idPaisCxp;
         
         @JsonProperty("ID_TIPO_NEGOCIO")
    public String idTipoNegocio;
         
         @JsonProperty("TOTAL_DEDUCCIONES")
    public String totalDeducciones;
     
         @JsonProperty("TOTAL_PERCEPCIONES")
    public String totalPercepciones;
         
         @JsonProperty("FECHA_PAGO")
    public String fechaPago;
         
         @JsonProperty("REGISTRO_PATRONAL")
    public String registroPatronal;
         
         @JsonProperty("CUENTA_BANCARIA")
    public String cuentaBancaria;
         
         @JsonProperty("PERIODICIDAD_PAGO")
    public String periocidadPago;
         
          @JsonProperty("PUESTO")
    public String puesto;
          
          @JsonProperty("DEPARTAMENTO")
    public String departamento;
      @JsonProperty("NUM_EMPLEADO")
    public String numEmpleado;
      
      @JsonProperty("FECHA_INI_REL_LABORAL")
    public String fechaIniRelLaboral;
      
      @JsonProperty("NUM_SEG_SOCIAL")
    public String numSegSocial;
      
      @JsonProperty("CURP")
    public String curp;

      
       
       
       
  
      

    public String id;

}
