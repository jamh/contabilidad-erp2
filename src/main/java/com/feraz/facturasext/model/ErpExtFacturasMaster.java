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
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_EXT_FACTURAS_MASTER" )
public class ErpExtFacturasMaster implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "invoiceNo", column = @Column(name = "INVOICE_NO",nullable = false))    
    })
    
    private ErpExtFacturasMasterId id;
     
     
    @Column (name = "INVOICE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoceDate;
    
    @Column (name = "INVOICE_DESC")
    private String invoiceDesc;
    
    @Column (name = "CUSTOMER")
    private String customer;
    
    @Column (name = "TERMS")
    private Integer terms;
    
    @Column (name = "STATUS")
    private String status;
    
    @Column (name = "CUENTA")
    private String cuenta;
    
    @Column (name = "NUMERO_POLIZA")
    private String numeroPoliza;
    
    @Column (name = "TIPO_POLIZA")
    private String tipoPoliza;
    
    @Column (name = "FECHA_POLIZA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPoliza;
    
    
    public ErpExtFacturasMaster(){
    
    }
    
    public ErpExtFacturasMaster(ErpExtFacturasMasterId id){
        this.id = id;
    }

    public ErpExtFacturasMasterId getId() {
        return id;
    }

    public void setId(ErpExtFacturasMasterId id) {
        this.id = id;
    }

    public Date getInvoceDate() {
        return invoceDate;
    }

    public void setInvoceDate(Date invoceDate) {
        this.invoceDate = invoceDate;
    }

    public String getInvoiceDesc() {
        return invoiceDesc;
    }

    public void setInvoiceDesc(String invoiceDesc) {
        this.invoiceDesc = invoiceDesc;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public Date getFechaPoliza() {
        return fechaPoliza;
    }

    public void setFechaPoliza(Date fechaPoliza) {
        this.fechaPoliza = fechaPoliza;
    }

   
    
    
    
    
}
