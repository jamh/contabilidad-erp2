/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

/**
 *
 * @author Administrador
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "ERP_SEGUI_DOCUMENTOS")
public class ErpSeguiDocumentos implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 3)),
        @AttributeOverride(name = "tDoc", column = @Column(name = "T_DOC", nullable = false, length = 5)),
        @AttributeOverride(name = "serie", column = @Column(name = "SERIE", nullable = false, length = 50)),
        @AttributeOverride(name = "numFe", column = @Column(name = "NUMERO_FE", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
     
     private ErpSeguiDocumentosId id;
     
      
       
        
        @Column(name = "USUARIO")
        private String usuario;
        
        @Column(name = "ESTATUS_LEYO")
        private String estatusLeyo;
        
        @Column(name = "ESTATUS_DEJO")
        private String estatusDejo;
        
        @Column(name = "F_MOVIMIENTO")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date fMovimiento;
        
        @Column(name = "OBSERVACIONES")
        private String observaciones;
        
        @Column(name = "T_OPERACION")
        private String tOperacion;
        
        @Column(name = "IMPORTE_DES")
        private BigDecimal importeDes;
        
        @Column(name = "IMPORTE_IMP1")
        private BigDecimal importeImp1;
        
        @Column(name = "SUBTOTAL")
        private BigDecimal subtotal;
        
        @Column(name = "IMPORTE_OPERACION")
        private BigDecimal importeOperacion;
        
        @Column(name = "MONEDA")
        private String moneda;
        
        @Column(name = "T_DOC_INV")
        private String tDocInv;
        
        @Column(name = "BANCO")
        private String banco;
        
        @Column(name = "REFERENCIA")
        private String referencia;
        
        @Column(name = "T_DOC_REF")
        private String tDocRef;
        
        @Column(name = "ORIGEN_REF")
        private String origenRef;
        
        @Column(name = "SERIE_REF")
        private String serieRef;
        
        @Column(name = "FOLIO_REF")
        private long folioRef;
        
        @Column(name = "TIPO_POLIZA")
        private String tipoPoliza;
        
        @Column(name = "FECHA")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date fecha;
        
        @Column(name = "NUMERO")
        private String numero;
        
        @Column(name = "TOT_PESOS")
        private BigDecimal totPesos;
        
        @Column(name = "TOT_IVA_PESOS")
        private BigDecimal totIvaPesos;
        
        @Column(name = "PARIDAD")
        private BigDecimal paridad;
        
        @Column(name = "PAGO_ACUMULADO_CXP_FE")
        private BigDecimal pagoAcumuladoCxpFe;
        
        @Column(name = "PAGO_CXP_FE")
        private BigDecimal pagoCxpFe;
                
       @Column(name = "FECHA_PAGO_CXP_FE")
     @Temporal(javax.persistence.TemporalType.DATE)
        private Date fechaPagoCxpFe;
       
       @Column(name = "USUARIO_CXP_FE")
        private String usuarioCxpFe;
       
        @Column(name = "CUENTA_CXP_FE")
        private String cuentaCxpFe;
        
        @Column(name = "CUENTA_BANCO")
        private String cuentaBanco;
        
        @Column(name = "TIPO")
        private String tipo;
        
        
        
       @Column(name = "FOLIO")
        private int folio;
       
       @Column(name = "ID_TRANSACCION")
        private int idTransaccion;
       
       @Column(name = "IMPORTE_TOTAL_PAGO")
        private BigDecimal importeTotalPago;
       
       @Column(name = "BANCO_PAGO")
        private String bancoPago;
       
       @Column(name = "CUENTA_PAGO")
        private String cuentaPago;
       
        
        
        public ErpSeguiDocumentos(){
        
        }
        
        public ErpSeguiDocumentos(ErpSeguiDocumentosId id){
            
            this.id = id;
        
        }

    public ErpSeguiDocumentosId getId() {
        return id;
    }

    public void setId(ErpSeguiDocumentosId id) {
        this.id = id;
    }

   
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstatusLeyo() {
        return estatusLeyo;
    }

    public void setEstatusLeyo(String estatusLeyo) {
        this.estatusLeyo = estatusLeyo;
    }

    public String getEstatusDejo() {
        return estatusDejo;
    }

    public void setEstatusDejo(String estatusDejo) {
        this.estatusDejo = estatusDejo;
    }

    public Date getfMovimiento() {
        return fMovimiento;
    }

    public void setfMovimiento(Date fMovimiento) {
        this.fMovimiento = fMovimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String gettOperacion() {
        return tOperacion;
    }

    public void settOperacion(String tOperacion) {
        this.tOperacion = tOperacion;
    }

    public BigDecimal getImporteDes() {
        return importeDes;
    }

    public void setImporteDes(BigDecimal importeDes) {
        this.importeDes = importeDes;
    }

    public BigDecimal getImporteImp1() {
        return importeImp1;
    }

    public void setImporteImp1(BigDecimal importeImp1) {
        this.importeImp1 = importeImp1;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImporteOperacion() {
        return importeOperacion;
    }

    public void setImporteOperacion(BigDecimal importeOperacion) {
        this.importeOperacion = importeOperacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String gettDocInv() {
        return tDocInv;
    }

    public void settDocInv(String tDocInv) {
        this.tDocInv = tDocInv;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String gettDocRef() {
        return tDocRef;
    }

    public void settDocRef(String tDocRef) {
        this.tDocRef = tDocRef;
    }

    public String getOrigenRef() {
        return origenRef;
    }

    public void setOrigenRef(String origenRef) {
        this.origenRef = origenRef;
    }

    public String getSerieRef() {
        return serieRef;
    }

    public void setSerieRef(String serieRef) {
        this.serieRef = serieRef;
    }

    public long getFolioRef() {
        return folioRef;
    }

    public void setFolioRef(long folioRef) {
        this.folioRef = folioRef;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getTotPesos() {
        return totPesos;
    }

    public void setTotPesos(BigDecimal totPesos) {
        this.totPesos = totPesos;
    }

    public BigDecimal getTotIvaPesos() {
        return totIvaPesos;
    }

    public void setTotIvaPesos(BigDecimal totIvaPesos) {
        this.totIvaPesos = totIvaPesos;
    }

    public BigDecimal getParidad() {
        return paridad;
    }

    public void setParidad(BigDecimal paridad) {
        this.paridad = paridad;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public BigDecimal getPagoAcumuladoCxpFe() {
        return pagoAcumuladoCxpFe;
    }

    public void setPagoAcumuladoCxpFe(BigDecimal pagoAcumuladoCxpFe) {
        this.pagoAcumuladoCxpFe = pagoAcumuladoCxpFe;
    }

    public BigDecimal getPagoCxpFe() {
        return pagoCxpFe;
    }

    public void setPagoCxpFe(BigDecimal pagoCxpFe) {
        this.pagoCxpFe = pagoCxpFe;
    }

    public Date getFechaPagoCxpFe() {
        return fechaPagoCxpFe;
    }

    public void setFechaPagoCxpFe(Date fechaPagoCxpFe) {
        this.fechaPagoCxpFe = fechaPagoCxpFe;
    }

    public String getUsuarioCxpFe() {
        return usuarioCxpFe;
    }

    public void setUsuarioCxpFe(String usuarioCxpFe) {
        this.usuarioCxpFe = usuarioCxpFe;
    }

    public String getCuentaCxpFe() {
        return cuentaCxpFe;
    }

    public void setCuentaCxpFe(String cuentaCxpFe) {
        this.cuentaCxpFe = cuentaCxpFe;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public BigDecimal getImporteTotalPago() {
        return importeTotalPago;
    }

    public void setImporteTotalPago(BigDecimal importeTotalPago) {
        this.importeTotalPago = importeTotalPago;
    }

    public String getBancoPago() {
        return bancoPago;
    }

    public void setBancoPago(String bancoPago) {
        this.bancoPago = bancoPago;
    }

    public String getCuentaPago() {
        return cuentaPago;
    }

    public void setCuentaPago(String cuentaPago) {
        this.cuentaPago = cuentaPago;
    }

    
  
}
