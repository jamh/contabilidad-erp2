/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dto;

/**
 *
 * @author Ing. David Ortiz García
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErpExtFacturasMasterDto {
    
     @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("INVOICE_NO")
    public String invoice;
    
    @JsonProperty("INVOICE_DATE")
    public String invoiceDate;
    
    @JsonProperty("CUSTOMER")
    public String customer;
    
     @JsonProperty("INVOICE_DESC")
    public String invoiceDesc;
    
    @JsonProperty("NOMCLIENTE")
    public String nomCliente;
    
    @JsonProperty("TERMS")
    public String terms;
    
    @JsonProperty("STATUS")
    public String status;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
    @JsonProperty("CUENTA_REAL")
    public String cuentaReal;
    
    @JsonProperty("NUMERO_POLIZA")
    public String numeroPoliza;
       
       
    @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
          
    @JsonProperty("FECHA_POLIZA")
    public String fechaPoliza;
 
}
