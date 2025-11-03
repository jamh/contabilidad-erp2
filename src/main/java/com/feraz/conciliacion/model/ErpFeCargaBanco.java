/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.model;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author JAMH
 */

@Entity
@Table(name = "ERP_FE_CARGA_BANCO")
public class ErpFeCargaBanco implements java.io.Serializable{
    
        @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))

    })

    //@JsonProperty("ID")
    private ErpFeCargaBancoId id;
        
     @Column(name = "NO_CUENTA")
     private String noCuenta;
     
     @Column(name = "NOM_ARCHIVO")
     private String nomArchivo;
     
     @Column(name = "DESCRIPCION")
     private String descripcion;
     
     @Column(name = "REFERENCIA")
     private String referencia;
     
     @Column(name = "FECHA_EMISION")
        @Temporal(javax.persistence.TemporalType.DATE)
     private Date fechaEmision;
     
     @Column(name = "FECHA_PAGADO")
        @Temporal(javax.persistence.TemporalType.DATE)
     private Date fechaPagado;
     
     @Column(name = "IMPORTE")
     private BigDecimal importe;
     
     @Column(name = "ID_TIPO_PAGO")
     private String idTipoPago;
     
     @Column(name = "ESTATUS")
     private String estatus;
     
     @Column(name = "TIPO_MOVTO")
     private String tipoMovto;
     
     @Column(name = "CONCEPTO")
     private String concepto;
     
     
     public ErpFeCargaBanco(){
         
     
     }
    
     
     public ErpFeCargaBanco(ErpFeCargaBancoId id){
         
         this.id = id;
     
     }

    public ErpFeCargaBancoId getId() {
        return id;
    }

    public void setId(ErpFeCargaBancoId id) {
        this.id = id;
    }

    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    public String getNomArchivo() {
        return nomArchivo;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(String idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTipoMovto() {
        return tipoMovto;
    }

    public void setTipoMovto(String tipoMovto) {
        this.tipoMovto = tipoMovto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
     
     
}
