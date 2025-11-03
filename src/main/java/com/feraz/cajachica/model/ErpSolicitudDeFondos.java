/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

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
@Table(name = "ERP_SOLICITUD_DE_FONDOS")
public class ErpSolicitudDeFondos implements java.io.Serializable{
    
       
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "idSolicitud", column = @Column(name = "ID_SOLICITUD", nullable = false))
    })

    private ErpSolicitudDeFondosId id;

    @Column(name = "USUARIO_SOLICITA")
    private String usuarioSolicita;
    
    @Column(name = "USUARIO_AUTORIZA")
    private String usuarioAutoriza;
    
//    @Column(name = "FECHA_DE_SOLICITUD")
//    private Date fechaDeSolicitud;

    @Column(name = "FECHA_DE_AUTORIZACION")
    private Date fechaDeAutorizacion;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "IMPORTE_REQUERIDO")
    private BigDecimal importeRequerido;
    
    @Column(name = "TIPO")
    private String tipo;
    
    @Column(name = "BANCO")
    private String banco;
    
    @Column(name = "CASA_DE_CAMBIO")
    private Integer casaDeCambio;
    
    @Column(name = "MONEDA_DE_PAGO")
    private String monedaDePago;
    
    @Column(name = "IMPORTE_DE_PAGO")
    private BigDecimal importeDePago;
    
    
    @Column(name = "SOLICITAR_A_CAJA")
    private String solicitarACaja;
    
    @Column(name = "NUMERO_DE_OPERACION")
    private String numeroDeOperacion;
    
    @Column(name = "FECHA_DE_RETIRO")
    private Date fechaDeRetiro;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "TC_IMPORTE_PAGO")
    private BigDecimal tcImportePago;
    
    
     @Column(name = "FOLIO_PAGOS")
    private Integer folioPagos;
     
     @Column(name = "ID_AREA")
    private Integer idArea;
    
     
     @Column(name = "AUTORIZA_AREA")
    private String autorizaArea;
     
    @Column(name = "FECHA_AUTORIZO_AREA")
    private Date fechaAutorizoArea;
    
            
            @Column(name = "USUARIO_AUTORIZO_AREA")
    private String usuarioAutorizaArea;
            
            @Column(name = "MOT_RECHAZO")
    private String motRechazo;
            
    
    public ErpSolicitudDeFondos(){
    
    }
    
    public ErpSolicitudDeFondos(ErpSolicitudDeFondosId id){
        
        this.id = id;
    
    }

    public ErpSolicitudDeFondosId getId() {
        return id;
    }

    public void setId(ErpSolicitudDeFondosId id) {
        this.id = id;
    }

    public String getUsuarioSolicita() {
        return usuarioSolicita;
    }

    public void setUsuarioSolicita(String usuarioSolicita) {
        this.usuarioSolicita = usuarioSolicita;
    }

    public String getUsuarioAutoriza() {
        return usuarioAutoriza;
    }

    public void setUsuarioAutoriza(String usuarioAutoriza) {
        this.usuarioAutoriza = usuarioAutoriza;
    }

 
    public Date getFechaDeAutorizacion() {
        return fechaDeAutorizacion;
    }

    public void setFechaDeAutorizacion(Date fechaDeAutorizacion) {
        this.fechaDeAutorizacion = fechaDeAutorizacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getImporteRequerido() {
        return importeRequerido;
    }

    public void setImporteRequerido(BigDecimal importeRequerido) {
        this.importeRequerido = importeRequerido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getCasaDeCambio() {
        return casaDeCambio;
    }

    public void setCasaDeCambio(Integer casaDeCambio) {
        this.casaDeCambio = casaDeCambio;
    }

    public String getMonedaDePago() {
        return monedaDePago;
    }

    public void setMonedaDePago(String monedaDePago) {
        this.monedaDePago = monedaDePago;
    }

    public BigDecimal getImporteDePago() {
        return importeDePago;
    }

    public void setImporteDePago(BigDecimal importeDePago) {
        this.importeDePago = importeDePago;
    }

    public String getSolicitarACaja() {
        return solicitarACaja;
    }

    public void setSolicitarACaja(String solicitarACaja) {
        this.solicitarACaja = solicitarACaja;
    }

    public String getNumeroDeOperacion() {
        return numeroDeOperacion;
    }

    public void setNumeroDeOperacion(String numeroDeOperacion) {
        this.numeroDeOperacion = numeroDeOperacion;
    }

    public Date getFechaDeRetiro() {
        return fechaDeRetiro;
    }

    public void setFechaDeRetiro(Date fechaDeRetiro) {
        this.fechaDeRetiro = fechaDeRetiro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getTcImportePago() {
        return tcImportePago;
    }

    public void setTcImportePago(BigDecimal tcImportePago) {
        this.tcImportePago = tcImportePago;
    }

    public Integer getFolioPagos() {
        return folioPagos;
    }

    public void setFolioPagos(Integer folioPagos) {
        this.folioPagos = folioPagos;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getAutorizaArea() {
        return autorizaArea;
    }

    public void setAutorizaArea(String autorizaArea) {
        this.autorizaArea = autorizaArea;
    }

    public Date getFechaAutorizoArea() {
        return fechaAutorizoArea;
    }

    public void setFechaAutorizoArea(Date fechaAutorizoArea) {
        this.fechaAutorizoArea = fechaAutorizoArea;
    }

    public String getUsuarioAutorizaArea() {
        return usuarioAutorizaArea;
    }

    public void setUsuarioAutorizaArea(String usuarioAutorizaArea) {
        this.usuarioAutorizaArea = usuarioAutorizaArea;
    }

    public String getMotRechazo() {
        return motRechazo;
    }

    public void setMotRechazo(String motRechazo) {
        this.motRechazo = motRechazo;
    }
    
    
    
}
