/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dto;

/**
 *
 * @author 55555
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CxpOtrasDTO {

  
    @JsonProperty("COMPANIA_OTROS")
    public String compania;
    
    @JsonProperty("NUMERO_OTROS")
    public String id;
    
    @JsonProperty("NUMERO_RELACION_OTRAS")
    public String numeroRelacionOtras;
    
    
    
    @JsonProperty("CXP_FECHA_OTROS")
    public String fechaCxp;
    
    @JsonProperty("NOMCC_OTROS")
    public String cc;
    
    @JsonProperty("CONCEPTO_OTROS")
    public String concepto;
    
    @JsonProperty("ESTATUS_CXP_OTROS")
    public String estatusCxp;
    
    @JsonProperty("NOM_ESTATUS_CXP_OTROS")
    public String nomEstatusCxpOtros;
    
    
    
    @JsonProperty("FECHA_OTROS")
    public String fecha;
    
    @JsonProperty("SUBTOTAL_OTROS")
    public String subtotal;
    
    @JsonProperty("TOTAL_OTROS")
    public String total;
    
    @JsonProperty("TIPO_CAMBIO_OTROS")
    public String tipoCambio;
    
    @JsonProperty("TIPO_DE_COMPROBANTE_OTROS")
    public String tipoComprobante;

    @JsonProperty("CONDICIONES_DE_PAGO_OTROS")
    public String condicionesPago;
    
    @JsonProperty("DESCUENTO_OTROS")
    public String descuentos;
    
    @JsonProperty("FORMA_DE_PAGO_OTROS")
    public String formaPago;
    
    @JsonProperty("METODO_DE_PAGO_OTROS")
    public String metodoPago;
    
    @JsonProperty("MONEDA_OTROS")
    public String moneda;
    
    @JsonProperty("IDCONCGASTO_OTROS")
    public String idconcgasto;
    
    @JsonProperty("ORIGEN_OTROS")
    public String origen;
    
    @JsonProperty("FECHA_CAP_OTROS")
    public String fechaCap;
    
    @JsonProperty("DESCRIPCION_OTROS")
    public String descripcion;
    
    @JsonProperty("RFC_EMISOR_OTROS")
    public String rfcEmisor;
    
    @JsonProperty("NOMBRE_EMISOR_OTROS")
    public String nombreEmisor;
    
    @JsonProperty("RFC_COMPANIA_OTROS")
    public String rfcCompania;
    
    @JsonProperty("TIPO_CXP_OTROS")
    public String tipoCxp;
    
    @JsonProperty("TIPO_GASTO_OTROS")
    public String tipoGasto;
    
    @JsonProperty("BENEFICIARIO_OTROS")
    public String beneficiario;
    
    @JsonProperty("OTROS_IMPUESTOS_OTROS")
    public String otrosImpuestos;
    
    @JsonProperty("NOMBRE_T_GASTO_OTROS")
    public String nombreTGastos;
    
    @JsonProperty("IVA_OTROS")
    public String ivaOtros;
            
    @JsonProperty("DESCRIPCION_OTRAS_OTROS")
    public String descripcionOtrasOtros;
    
    @JsonProperty("FACTURABLE_OTROS")
    public String facturableOtros;
    
    @JsonProperty("REEMBOLSO")
    public String reembolso;
    
    @JsonProperty("ID_PROVEEDOR_OTROS")
    public String idProveedoreOtros;
    
    @JsonProperty("ID_TIPO_GASTO_OTROS")
    public String idTipoGastoOtros;
    
    @JsonProperty("FECHA_CONTAB_PROV_OTROS")
    public String fechaContabProvOtros;
    
    @JsonProperty("NUMERO_POL_OTR")
    public String numeroPolOtr;
    
    @JsonProperty("TIPO_POL_OTR")
    public String tipoPolOtr;
    
    @JsonProperty("FECHA_POL_OTR")
    public String fechaPolOtr;
    
    @JsonProperty("ID_TIPO_NEGOCIO_OTROS")
    public String idTipoNegocioOtros;
    
    @JsonProperty("ID_PAIS_CXP_OTROS")
    public String idPaisCxpOtros;
    
    @JsonProperty("RETENCION_RENTA")
    public String retencionRenta;
    
    @JsonProperty("RETENCION_IVA")
    public String retencionIva;
    
    @JsonProperty("ID_SERVICIO")
    public String idServicio;
    
    @JsonProperty("SERVICIO")
    public String servicio;
    
    @JsonProperty("ARCHIVO_OTRAS")
    public String archivoOtras;
    
    @JsonProperty("IMPORTE_NOTA")
    public String importeNota;
    
    @JsonProperty("FOLIO_GASTO")
    public String folioGasto;
    
    @JsonProperty("ARCHIVO_CXPO")
    public String archivoCxp;
    
    @JsonProperty("RUTA_CXPO")
    public String rutaCxp;
    
    @JsonProperty("ORDEN_COMPRA")
    public String ordencompra;
    
    @JsonProperty("ID_ORDEN_COMPRA")
    public String idOrdenCompras;
    
    @JsonProperty("FECHA_CASH_FLOW")
    public String fechaCashFlow;
  
    

}
