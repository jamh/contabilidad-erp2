/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvioTesViaDTO {
    
    @JsonProperty("FECHA")
    public String fecha;
        
    @JsonProperty("T_MONEDA")
    public String tMoneda;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("EMPLEADO")
    public String empleado;
    
    @JsonProperty("FOLIO")
    public String folio;
    
    @JsonProperty("TIPO_PAGO")
    public String tipoPago;
    
    @JsonProperty("COMISION")
    public String comision;
    
    @JsonProperty("ESTATUS")
    public String estatus;
    
    @JsonProperty("CUENTA_CLABE")
    public String cuentaClabe;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("NUMERO_CUENTA")
    public String numeroCuenta;

     @JsonProperty("TIPO_PAGO_NOM")
    public String tipoPgoNom;
     
     @JsonProperty("FECHA_FIN")
    public String fechaFin;
    

    
}
