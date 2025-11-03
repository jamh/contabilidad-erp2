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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_EXT_FACTURAS" )
public class ErpExtFacturas implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "invoiceNo", column = @Column(name = "INVOICE_NO",nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC",nullable = false))
    
    })
    
    private ErpExtFacturasId id;
     
     
    @Column (name = "INVOICE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoceDate;
    
    @Column (name = "ITEM")
    private String item;
    
    @Column (name = "PRICE")
    private BigDecimal price;
    
    @Column (name = "QUANTITY")
    private Integer quantity;
    
    @Column (name = "CUSTOMER")
    private String customer;
    
    @Column (name = "CUSTOMER_PO")
    private String customerPo;
    
    @Column (name = "OPERATIVE_WEEK")
    private String operativeWeek;
    
    @Column (name = "NOTES1")
    private String notes1;
    
    @Column (name = "NOTES2")
    private String notes2;
    
    @Column (name = "LINE_SUBTOTAL")
    private BigDecimal lineSubtotal;
    
    @Column (name = "TAX")
    private String tax;
    
    @Column (name = "TAX_IMP")
    private BigDecimal taxImp;
    
    @Column (name = "LINE_TAX")
    private BigDecimal lineTax;
    
    @Column (name = "LINE_TOTAL")
    private BigDecimal lineTotal;
    
    @Column (name = "TERMS")
    private Integer terms;
    
    @Column (name = "DUE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    
    @Column (name = "CUENTA")
    private String cuenta;
    
    public ErpExtFacturas(){
    
    }
    
    public ErpExtFacturas(ErpExtFacturasId id){
    
        this.id = id;
    }

    public ErpExtFacturasId getId() {
        return id;
    }

    public void setId(ErpExtFacturasId id) {
        this.id = id;
    }

    public Date getInvoceDate() {
        return invoceDate;
    }

    public void setInvoceDate(Date invoceDate) {
        this.invoceDate = invoceDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerPo() {
        return customerPo;
    }

    public void setCustomerPo(String customerPo) {
        this.customerPo = customerPo;
    }

    public String getOperativeWeek() {
        return operativeWeek;
    }

    public void setOperativeWeek(String operativeWeek) {
        this.operativeWeek = operativeWeek;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    public BigDecimal getLineSubtotal() {
        return lineSubtotal;
    }

    public void setLineSubtotal(BigDecimal lineSubtotal) {
        this.lineSubtotal = lineSubtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public BigDecimal getTaxImp() {
        return taxImp;
    }

    public void setTaxImp(BigDecimal taxImp) {
        this.taxImp = taxImp;
    }

    public BigDecimal getLineTax() {
        return lineTax;
    }

    public void setLineTax(BigDecimal lineTax) {
        this.lineTax = lineTax;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    
}
