/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.model;

/**
 *
 * @author vavi
 */

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

@Entity
@Table (name="ERP_PEDIDO_MAESTRO" )
public class ErpPedidoMaestro implements java.io.Serializable{
    
        
     @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "folio", column = @Column(name = "FOLIO",nullable = false))
    
    })
    
    private ErpPedidoMaestroId id;
     
     
    @Column (name = "NOMBRE")
    private String nombre;
    
    @Column (name = "FECHA_REQUERIDO")
    private Date fechaRequerido;
    
    @Column (name = "ESTATUS")
    private String estatus;
    
    @Column (name = "USUARIO")
    private String usuario;
    
    @Column (name = "USUARIO_AUTORIZO")
    private String usuarioAutorizo;
    
    @Column (name = "CTO_CTO")
    private String ctoCto;
    
    @Column (name = "FECHA_CANCELA")
    private Date fechaCancela;
    
    @Column (name = "MOTIVO_CANCELA")
    private String motivoCancela;
    
    @Column (name = "USUARIO_CANCELA")
    private String usuarioCancela;
    
    @Column (name = "TIPO_PEDIDO")
    private Integer tipoPedido;
    
    @Column (name = "ID_PROVEEDOR")
    private String idProveedor;
    
    @Column (name = "ID_PROYECTO")
    private Integer idProyecto;
    
    @Column (name = "TIPO_PRODUCCION")
    private Integer tipoProduccion;
    
    @Column (name = "ID_AREA")
    private Integer idArea;
    
    @Column (name = "ENVIADO_A_AUTORIZAR")
    private String enviadoaAutorizar;
    
    @Column (name = "SOLICITUD_URGENTE")
    private String solicitudUrgente;
    
    @Column (name = "CORREO_PROV")
    private String correoProv;
    
    @Column (name = "AVION_CC")
    private String avionCC;
    
    @Column (name = "ESTACION")
    private String estacion;
    
    @Column (name = "ID_CLIENTE")
    private String idCliente;
    
    
    @Column (name = "ID_CONDICIONES")
    private String idCondiciones;
    
    @Column (name = "DEPARTAMENTO")
    private Integer departamento;
    
    @Column (name = "FECHA_AUTORIZACION")
    private Date fechaAutorizacion;
    
    
    
    public ErpPedidoMaestro(){
    
    }
    
    public ErpPedidoMaestro(ErpPedidoMaestroId id){
    
            this.id = id;
    }

    public ErpPedidoMaestroId getId() {
        return id;
    }

    public void setId(ErpPedidoMaestroId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRequerido() {
        return fechaRequerido;
    }

    public void setFechaRequerido(Date fechaRequerido) {
        this.fechaRequerido = fechaRequerido;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioAutorizo() {
        return usuarioAutorizo;
    }

    public void setUsuarioAutorizo(String usuarioAutorizo) {
        this.usuarioAutorizo = usuarioAutorizo;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
    }

    public Date getFechaCancela() {
        return fechaCancela;
    }

    public void setFechaCancela(Date fechaCancela) {
        this.fechaCancela = fechaCancela;
    }

    public String getMotivoCancela() {
        return motivoCancela;
    }

    public void setMotivoCancela(String motivoCancela) {
        this.motivoCancela = motivoCancela;
    }

    public String getUsuarioCancela() {
        return usuarioCancela;
    }

    public void setUsuarioCancela(String usuarioCancela) {
        this.usuarioCancela = usuarioCancela;
    }

    public Integer getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(Integer tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getTipoProduccion() {
        return tipoProduccion;
    }

    public void setTipoProduccion(Integer tipoProduccion) {
        this.tipoProduccion = tipoProduccion;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getEnviadoaAutorizar() {
        return enviadoaAutorizar;
    }

    public void setEnviadoaAutorizar(String enviadoaAutorizar) {
        this.enviadoaAutorizar = enviadoaAutorizar;
    }

    public String getSolicitudUrgente() {
        return solicitudUrgente;
    }

    public void setSolicitudUrgente(String solicitudUrgente) {
        this.solicitudUrgente = solicitudUrgente;
    }

    public String getCorreoProv() {
        return correoProv;
    }

    public void setCorreoProv(String correoProv) {
        this.correoProv = correoProv;
    }

    public String getAvionCC() {
        return avionCC;
    }

    public void setAvionCC(String avionCC) {
        this.avionCC = avionCC;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCondiciones() {
        return idCondiciones;
    }

    public void setIdCondiciones(String idCondiciones) {
        this.idCondiciones = idCondiciones;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }
    
    
    
    
}
