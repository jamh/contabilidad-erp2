/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author 55555
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompCajaDTO {
    
     @JsonProperty("COMISION")
    public String comision;
     
     @JsonProperty("EMPLEADO")
    public String empleado;
     
     @JsonProperty("SEC_COMP")
    public String secComp;
     
     @JsonProperty("CONC_GASTO")
    public String concGasto;
     
     @JsonProperty("NOM_COC_GASTO")
    public String nomConcGasto;
     
     @JsonProperty("FORMA_COMPROBACION")
    public String formaComprobacion;

     @JsonProperty("NOM_FORMA_COMPROBACION")
    public String nomFormaComprobacion;
     
     @JsonProperty("F_COMPROBANTE")
    public String fComprobante;
     
     @JsonProperty("T_MONEDA")
    public String tMoneda;
    
     @JsonProperty("IMPORTE_MONEDA_EXT")
    public String importeMonedaExt;

    @JsonProperty("IMPORTE_PESOS")
    public String importePesos;
    
    @JsonProperty("IVA")
    public String iva;
    
    @JsonProperty("ISH")
    public String ish;
    
    @JsonProperty("TOT_GTO")
    public String totGto;
    
    @JsonProperty("TOT_GTO_PESOS")
    public String totGtoPesos;
    
    @JsonProperty("CTO_CXP")
    public String ctoCxp;
    
    @JsonProperty("ID_PAIS_CXP")
    public String idPaisCxp;
    
    @JsonProperty("ID_TIPO_NECOGIO")
    public String idTipoNegocio;
    
    @JsonProperty("IEPS")
    public String ieps;
    
    @JsonProperty("RETENCION_ISR")
    public String retencionIsr;
    
    @JsonProperty("RETENCION_IVA")
    public String retencionIva;
    
    @JsonProperty("COMISION_VIATICO")
    public String comisionViatico;
    
    @JsonProperty("NUMERO_FE")
    public String numeroFe;
    
    @JsonProperty("FACTURA_PAGO")
    public String facturaPago;
    
    @JsonProperty("ORIGEN_FACTURA_PAGO")
    public String origenFacturaPago;
    
    @JsonProperty("TOT_COMP")
    public String totComp;
    
    @JsonProperty("VISTO_BUENO")
    public String vistoBueno;
    
    @JsonProperty("MOT_VISTO_BUENO")
    public String motVistoBueno;
    
    @JsonProperty("EMPLEADO_APLICA")
    public String empleadoAplica;
    
    @JsonProperty("SEC_CAJA")
    public String secCaja;
    
    
    
    
    
   
}
