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
public class PagosAllDTO {
    
    
     @JsonProperty("COMPANIA")
    public String compania;
     
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("FOLIO")
    public String folio;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("SUBTOTAL")
    public String subtotal;
    
    @JsonProperty("TOTAL")
    public String total;
    
    @JsonProperty("TIPO_CAMBIO")
    public String tipoCambio;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("UUID")
    public String uuid;
    
    @JsonProperty("PAGO_CXP")
    public String pagoCxp;
    
    @JsonProperty("FECHA_PAGO_CXP")
    public String fechaPagoCxp;
    
    @JsonProperty("ESTATUS_CXP")
    public String estatusCxp;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("RFC")
    public String rfc;
    
    @JsonProperty("IMPORTE_OPERACION")
    public String importeOperacion;
    
    @JsonProperty("TIPO_OPERACION")
    public String tipoOperacion;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("TOT_PESOS")
    public String totPesos;
    
    @JsonProperty("POLIZA")
    public String polizas;
    
    @JsonProperty("NOMIDCONCGASTOPAGOS")
    public String nomIdConGastoPagos;
    
    @JsonProperty("FLAG_POLIZA_PAGO")
    public String flagPolizaPago;
    
    @JsonProperty("TIPO_POLIZA_PAGO")
    public String tipoPolizaPago;
    
    @JsonProperty("FECHA_POL_PAGO")
    public String fechaPolPago;
    
    @JsonProperty("NUMERO_POL_PAGO")
    public String numeroPolPago;
    
    @JsonProperty("NO_CASO_PAGOS")
    public String noCasoPagos;
    
    @JsonProperty("IDCONCGASTO_PAGOS")
    public String idConcGastoPagos;
    
    
    
    
    
 

}
