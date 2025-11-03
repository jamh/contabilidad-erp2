/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dto;

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
public class ConciliaDTO {
    
    @JsonProperty("SEC")
    public String sec;
    
    @JsonProperty("NO_CUENTA")
    public String noCuenta;
    
    @JsonProperty("NOM_ARCHIVO")
    public String nomArchivo;
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("REFERENCIA")
    public String referencia;
    
    @JsonProperty("FECHA_EMISION")
    public String fechaEmision;
    
    @JsonProperty("FECHA_PAGADO")
    public String fechaPagado;
    
    @JsonProperty("FECHA_PAGO_CXP_FE")
    public String fechaPagoCxpFe;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("TOT_PESOS")
    public String totPesos;
    
    
    @JsonProperty("ID_TIPO_PAGO")
    public String idTipoPago;
    
    @JsonProperty("CONCEPTO")
    public String concepto;
    
    @JsonProperty("NUMERO_FE")
    public String numeroFe;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("FLAG_POLIZACONC")
    public String flagPolizasConc;
    
    @JsonProperty("T_DOC")
    public String tDoc;
    
    
    
    
    
}
