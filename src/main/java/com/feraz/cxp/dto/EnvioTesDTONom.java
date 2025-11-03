/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvioTesDTONom {
    
    @JsonProperty("ID_NOM")
    public String idNom;
    
    @JsonProperty("EMPLEADO_FOL_NOM")
    public String empleadoFolNom;
    
    @JsonProperty("NOMBRE_EMPLEADO_FOL")
    public String nombreEmpleadoFol;
    
    @JsonProperty("NUMERO_CUENTA_FOL_NOM")
    public String numeroCuentaFolNom;
    
    @JsonProperty("BANCO_FOL_NOM")
    public String bancoFolNom;
    
    @JsonProperty("TOTAL_PAGO_NOM")
    public String totalPagoNom;
    
    @JsonProperty("FECHA_PAGO_FOL_NOM")
    public String fechaPagoFolNom;
    
    @JsonProperty("ESTATUS_FOL_NOM")
    public String estatusFolNom;
    
    @JsonProperty("TIPO_PAGO")
    public String tipoPago;
    
    @JsonProperty("DEPARTAMENTO")
    public String departamento;
    
 
     

   
}
