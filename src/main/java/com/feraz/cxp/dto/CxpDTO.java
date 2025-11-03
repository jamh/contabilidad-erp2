/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CxpDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("FOLIO")
    public String folio;
    
    @JsonProperty("UUID")
    public String uuid;
    
    @JsonProperty("PDF")
    public String pdf;
    
    @JsonProperty("IVA")
    public String iva;
    
    @JsonProperty("CXP_FECHA")
    public String cxpFecha;
    
    @JsonProperty("NOMCC")
    public String nomCC;
    
    @JsonProperty("CONCEPTO")
    public String concepto;
    
    @JsonProperty("ESTATUS_CXP")
    public String estatusCxp;
    
    @JsonProperty("FOLIO_FISCAL_ORIG")
    public String folioFiscalOrig;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("FECHA_FOLIO_FISCAL_ORIG")
    public String fechaFolioFiscalOri;
    
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
    
    @JsonProperty("ORIGEN")
    public String origen;
    
    @JsonProperty("FECHA_CAP")
    public String fechaCap;
    
    @JsonProperty("XML")
    public String xml;
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("RFC_EMISOR")
    public String rfcEmisor;
    
    @JsonProperty("RFC_RECEPTOR")
    public String rfcReceptor;
    
    @JsonProperty("NOMBRE_EMISOR")
    public String nombreEmisor;
    
    @JsonProperty("NOMBRE_RECEPTOR")
    public String nombreReceptor;
    
    @JsonProperty("FLAG_POLIZA")
    public String flagPoliza;
    
    @JsonProperty("RFC_COMPANIA")
    public String rfcCompania;
    
    @JsonProperty("T_DISTRIBUCION")
    public String tDistribucion;
    
    @JsonProperty("PRORRATEO")
    public String prorrateo;
    
    @JsonProperty("TIPO_CXP")
    public String tipoCxp;
    
    @JsonProperty("TIPO_GASTO")
    public String tipoGasto;
    
    @JsonProperty("BENEFICIARIO")
    public String beneficiario;
    
    @JsonProperty("OTROS_IMPUESTOS")
    public String otrosImpuestos;
    
    @JsonProperty("ESTATUSV")
    public String estatusV;
    
    @JsonProperty("NUM_REL_OTRAS")
    public String numeroRelOtras;
    
    @JsonProperty("NOM_ESTATUS_CXP")
    public String nomEstatus_CXP;
    
    
    

    
}
