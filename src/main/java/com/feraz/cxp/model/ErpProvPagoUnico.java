/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author vavi
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
@Table(name = "ERP_PROV_PAGO_UNICO")
public class ErpProvPagoUnico implements java.io.Serializable{
    
       @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "idMov", column = @Column(name = "ID_MOV", nullable = false, length = 12))

    })

    private ErpProvPagoUnicoId id;
       
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "RFC")
    private String rfc;
    
    @Column(name = "BANCO")
    private String banco;
    
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    
    @Column(name = "CUENTA_CLABE")
    private String cuentaClabe;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "PAGO_ACUMULADO_CXP")
    private BigDecimal pagoAcumuladoCxp;
    
    @Column(name = "FECHA_PAGO_CXP")
       @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPagoCxp;
    
    @Column(name = "ESTATUS_CXP")
    private String estatusCxp;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "TIPO_CAMBIO")
    private BigDecimal tipoCambio;
    
    @Column(name = "FOLIO_PAGOS")
    private Integer folioPagos;
    
    
    
    
    
    public ErpProvPagoUnico(){
    
    }
    
    public ErpProvPagoUnico(ErpProvPagoUnicoId id){
    
            this.id = id;
    }

    public ErpProvPagoUnicoId getId() {
        return id;
    }

    public void setId(ErpProvPagoUnicoId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCuentaClabe() {
        return cuentaClabe;
    }

    public void setCuentaClabe(String cuentaClabe) {
        this.cuentaClabe = cuentaClabe;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPagoAcumuladoCxp() {
        return pagoAcumuladoCxp;
    }

    public void setPagoAcumuladoCxp(BigDecimal pagoAcumuladoCxp) {
        this.pagoAcumuladoCxp = pagoAcumuladoCxp;
    }

    public Date getFechaPagoCxp() {
        return fechaPagoCxp;
    }

    public void setFechaPagoCxp(Date fechaPagoCxp) {
        this.fechaPagoCxp = fechaPagoCxp;
    }

    public String getEstatusCxp() {
        return estatusCxp;
    }

    public void setEstatusCxp(String estatusCxp) {
        this.estatusCxp = estatusCxp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Integer getFolioPagos() {
        return folioPagos;
    }

    public void setFolioPagos(Integer folioPagos) {
        this.folioPagos = folioPagos;
    }
    
    
    
    
}
