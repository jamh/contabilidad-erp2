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
@Table (name="ERP_ORDEN_COMPRA_DET" )
public class ErpOrdenCompraDet implements java.io.Serializable{
    
       @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "idOrdenCompra", column = @Column(name = "ID_ORDEN_COMPRA",nullable = false)),
        @AttributeOverride(name = "linea", column = @Column(name = "LINEA",nullable = false))
    
    })
    
    private ErpOrdenCompraDetId id;
     
     
    @Column (name = "ID")
    private String idDet;
    
    @Column (name = "ID_PRODUCTO")
    private String idProducto;
    
    @Column (name = "DESCRIPCION")
    private String descripcion;
    
    @Column (name = "CANTIDAD_PEDIDA")
    private BigDecimal cantidadPedida;
    
    @Column (name = "CANTIDAD_LLEGO")
    private BigDecimal cantidadLlego;
    
    @Column (name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;
    
    @Column (name = "IVA")
    private BigDecimal iva;
    
    @Column (name = "TOTAL")
    private BigDecimal total;
    
    @Column (name = "IMPORTE_COTIZACION")
    private BigDecimal importeCotizacion;
    
    @Column (name = "ID_ALMACEN")
    private Integer idAlmacen;
    
    @Column (name = "ID_ESTATUS")
    private Integer idEstatus;
    
    @Column (name = "ID_PRIORIDAD")
    private Integer idPrioridad;
    
    @Column (name = "ESTATUS_LINEA")
    private String estatusLinea;
    
    @Column (name = "ID_LINEA_WS")
    private String idLineaWS;
    
    @Column (name = "ID_WS")
    private String idWs;
     @Column(name="IMPORTE_COTIZACION_SUB")
  private BigDecimal importeCotizacionSub;
  @Column(name="IMPORTE_COTIZACION_IVA")
  private BigDecimal importeCotizacionIva;
  @Column(name="DESCUENTO")
  private BigDecimal descuento;
  @Column(name="ID_PROYECTO")
  private String idProyecto;
  
  @Column(name="ID_MONEDA")
  private String idMoneda;
  
  @Column(name="TASA_IVA")
  private String tasaIva;
  
  @Column(name="RETENCION")
  private Integer retencion;
  
  
  @Column(name="TIPO_PRODUCCION")
  private Integer tipoProduccion;
  
  @Column(name="DESCUENTO_PROV")
  private BigDecimal descuentoProv;
  
  @Column(name="C_COSTOS")
  private String cCostos;
  
  @Column(name="ID_FAMILIA")
  private Integer idFamilia;
  
  @Column(name="ID_DET_FAMILIA")
  private Integer idDetFamilia;
  
  @Column(name="ID_GERENCIA")
  private Integer idGerencia;
  
  @Column(name="FECHA_ENTREGA")
  private Date fechaEntrega;
  
  @Column(name="IEPS")
  private BigDecimal ieps;
  
  @Column(name="TIPO_GASTO")
  private String tipoGasto;
  
  @Column(name="T_G_DESCRIPCION")
  private String tGDescripcion;
  
  @Column(name="GRUPO_GASTO")
  private String grupoGasto;
  
  @Column(name="AVION_CC")
  private String avionCC;
  
  @Column(name="DEPARTAMENTO")
  private Integer departamento;
  
  @Column(name="ESTACION")
  private String estacion;
  
  @Column(name="RETENCION2")
  private Integer retencion2;
  
  @Column(name="DESCUENTO2")
  private BigDecimal descuento2;
  
  
  

  
    
    
    public ErpOrdenCompraDet(){
    
    }
    
    public ErpOrdenCompraDet(ErpOrdenCompraDetId id){
    
        this.id = id;
    }

    public ErpOrdenCompraDetId getId() {
        return id;
    }

    public void setId(ErpOrdenCompraDetId id) {
        this.id = id;
    }

    public String getIdDet() {
        return idDet;
    }

    public void setIdDet(String idDet) {
        this.idDet = idDet;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(BigDecimal cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public BigDecimal getCantidadLlego() {
        return cantidadLlego;
    }

    public void setCantidadLlego(BigDecimal cantidadLlego) {
        this.cantidadLlego = cantidadLlego;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getImporteCotizacion() {
        return importeCotizacion;
    }

    public void setImporteCotizacion(BigDecimal importeCotizacion) {
        this.importeCotizacion = importeCotizacion;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getEstatusLinea() {
        return estatusLinea;
    }

    public void setEstatusLinea(String estatusLinea) {
        this.estatusLinea = estatusLinea;
    }

    public String getIdLineaWS() {
        return idLineaWS;
    }

    public void setIdLineaWS(String idLineaWS) {
        this.idLineaWS = idLineaWS;
    }

    public String getIdWs() {
        return idWs;
    }

    public void setIdWs(String idWs) {
        this.idWs = idWs;
    }

    public BigDecimal getImporteCotizacionSub() {
        return importeCotizacionSub;
    }

    public void setImporteCotizacionSub(BigDecimal importeCotizacionSub) {
        this.importeCotizacionSub = importeCotizacionSub;
    }

    public BigDecimal getImporteCotizacionIva() {
        return importeCotizacionIva;
    }

    public void setImporteCotizacionIva(BigDecimal importeCotizacionIva) {
        this.importeCotizacionIva = importeCotizacionIva;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getTasaIva() {
        return tasaIva;
    }

    public void setTasaIva(String tasaIva) {
        this.tasaIva = tasaIva;
    }

    public Integer getRetencion() {
        return retencion;
    }

    public void setRetencion(Integer retencion) {
        this.retencion = retencion;
    }

    public Integer getTipoProduccion() {
        return tipoProduccion;
    }

    public void setTipoProduccion(Integer tipoProduccion) {
        this.tipoProduccion = tipoProduccion;
    }

    public BigDecimal getDescuentoProv() {
        return descuentoProv;
    }

    public void setDescuentoProv(BigDecimal descuentoProv) {
        this.descuentoProv = descuentoProv;
    }

    public String getcCostos() {
        return cCostos;
    }

    public void setcCostos(String cCostos) {
        this.cCostos = cCostos;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Integer getIdDetFamilia() {
        return idDetFamilia;
    }

    public void setIdDetFamilia(Integer idDetFamilia) {
        this.idDetFamilia = idDetFamilia;
    }

    public Integer getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(Integer idGerencia) {
        this.idGerencia = idGerencia;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getIeps() {
        return ieps;
    }

    public void setIeps(BigDecimal ieps) {
        this.ieps = ieps;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String gettGDescripcion() {
        return tGDescripcion;
    }

    public void settGDescripcion(String tGDescripcion) {
        this.tGDescripcion = tGDescripcion;
    }

    public String getGrupoGasto() {
        return grupoGasto;
    }

    public void setGrupoGasto(String grupoGasto) {
        this.grupoGasto = grupoGasto;
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

    public Integer getRetencion2() {
        return retencion2;
    }

    public void setRetencion2(Integer retencion2) {
        this.retencion2 = retencion2;
    }

    public BigDecimal getDescuento2() {
        return descuento2;
    }

    public void setDescuento2(BigDecimal descuento2) {
        this.descuento2 = descuento2;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

 
    
    
}
