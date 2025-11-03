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
public class ErpExtFacturasDto {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("INVOICE_NO")
    public String invoice;
    
    @JsonProperty("SEC")
    public String sec;
    
    @JsonProperty("INVOICE_DATE")
    public String invoiceDate;
    
    @JsonProperty("ITEM")
    public String item;
    
    @JsonProperty("PRICE")
    public String price;
    
    @JsonProperty("QUANTITY")
    public String quantity;
    
    @JsonProperty("CUSTOMER")
    public String customer;
    
    @JsonProperty("CUSTOMER_PO")
    public String customerPo;
    
    @JsonProperty("OPERATIVE_WEEK")
    public String operativeWeek;
    
    @JsonProperty("NOTES1")
    public String notes1;
    
    @JsonProperty("NOTES2")
    public String notes2;
    
    @JsonProperty("LINE_SUBTOTAL")
    public String lineSubtotal;
    
    @JsonProperty("TAX")
    public String tax;
    
    @JsonProperty("TAX_IMP")
    public String taxImp;
    
    @JsonProperty("LINE_TAX")
    public String lineTax;
    
    @JsonProperty("LINE_TOTAL")
    public String lineTotal;
    
    @JsonProperty("TERMS")
    public String terms;
    
    @JsonProperty("DUE_DATE")
    public String dueDate;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
    @JsonProperty("CUENTA_REAL")
    public String cuentaReal;

    
    
    
}
