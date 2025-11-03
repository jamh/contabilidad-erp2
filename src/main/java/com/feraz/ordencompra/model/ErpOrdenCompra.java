/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.model;

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
@Table (name="ERP_ORDEN_COMPRA" )
public class ErpOrdenCompra implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID",nullable = false))
    
    })
    
    private ErpOrdenCompraId id;
     
     
    @Column (name = "FOLIO")
    private String folio;
    
    @Column (name = "CALENDARIO")
    private Integer calendario;
    
    @Column (name = "PERIODO")
    private Integer periodo;
    
    @Column (name = "IMPORTE")
    private BigDecimal importe;
    
    @Column (name = "USUARIO")
    private String usuario;
    
    @Column (name = "ESTATUS")
    private String estatus;
    
    @Column (name = "RFC")
    private String rfc;
    
    @Column (name = "ID_PROVEEDOR")
    private String idProveedor;
    
    @Column (name = "USUARIO_AUTORIZO")
    private String usuarioAutorizo;
    
    @Column (name = "USUARIO_COMPRADOR")
    private String usuarioComprador;
    
    @Column (name = "CONDICIONES_PAGO")
    private String condicionesPago;
    
    @Column (name = "FECHA_ORDEN")
    private Date fechaOrden;
    
    @Column (name = "FECHA_REQUERIDA")
    private Date fechaRequerida;
    
    @Column (name = "CLASIFICACION")
    private String clasificacion;
    
    @Column (name = "TOTAL")
    private BigDecimal total;
    
    @Column (name = "TOTAL_PENDIENTE")
    private BigDecimal totalPendiente;
    
    @Column (name = "ID_ALMACEN")
    private Integer idAlmacen;
    
    @Column (name = "CTO_CTO")
    private String ctoCto;
    
    @Column (name = "NOMBRE")
    private String nombre;
    
    @Column (name = "DESCRIPCION")
    private String descripcion;
    
    @Column (name = "ID_WS")
    private String idWs;
    
    @Column (name = "TIPO")
    private String tipo;
    
    @Column (name = "VALIDACION_GASTO")
    private String validacionGasto;
    
    @Column (name = "ID_CONDICIONES")
    private String idCondiciones;
    
    
    
    
    
    public ErpOrdenCompra (){
    
    }
    
    
    public ErpOrdenCompra (ErpOrdenCompraId id){
    
        this.id = id;
    
    }

    public ErpOrdenCompraId getId() {
        return id;
    }

    public void setId(ErpOrdenCompraId id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getCalendario() {
        return calendario;
    }

    public void setCalendario(Integer calendario) {
        this.calendario = calendario;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getUsuarioAutorizo() {
        return usuarioAutorizo;
    }

    public void setUsuarioAutorizo(String usuarioAutorizo) {
        this.usuarioAutorizo = usuarioAutorizo;
    }

    public String getUsuarioComprador() {
        return usuarioComprador;
    }

    public void setUsuarioComprador(String usuarioComprador) {
        this.usuarioComprador = usuarioComprador;
    }

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(String condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(Date fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(BigDecimal totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdWs() {
        return idWs;
    }

    public void setIdWs(String idWs) {
        this.idWs = idWs;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValidacionGasto() {
        return validacionGasto;
    }

    public void setValidacionGasto(String validacionGasto) {
        this.validacionGasto = validacionGasto;
    }

    public String getIdCondiciones() {
        return idCondiciones;
    }

    public void setIdCondiciones(String idCondiciones) {
        this.idCondiciones = idCondiciones;
    }
    

    
}
