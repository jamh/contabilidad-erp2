/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.model;

/**
 *
 * @author Ing. David Ortiz García
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpExtCobranzaId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID_COBRANZA")
    private Integer idCobranza;
    
    @Column (name = "INVOICE_NO")
    private String invoiceNo;
    
    @Column (name = "SEC")
    private Integer sec;
    
    public ErpExtCobranzaId(){
    }
    
    public ErpExtCobranzaId(String compania, Integer idCobranza, String invoiceNo, Integer sec){
    
        this.compania=compania;
        this.idCobranza=idCobranza;
        this.invoiceNo=invoiceNo;
        this.sec=sec;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdCobranza() {
        return idCobranza;
    }

    public void setIdCobranza(Integer idCobranza) {
        this.idCobranza = idCobranza;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }
    
    
    
}
