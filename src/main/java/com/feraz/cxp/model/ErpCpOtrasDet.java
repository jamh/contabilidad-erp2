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
 * @author Administrador
 */
@Entity
@Table(name = "ERP_CP_OTRAS_DET")
public class ErpCpOtrasDet implements Serializable{
    
     @EmbeddedId
    @AttributeOverrides({      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
      private  ErpCpOtrasDetId id;
     
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    @Column(name = "ID_TIPO_GASTO")
    private Integer idTipoGasto;
    
    @Column(name = "ID_TIPO_NEGOCIO")
    private String idTipoNegocio;
    
    @Column(name = "ID_PAIS_CXP")
    private String idPaisCxp;
    
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;
    
    
    @Column(name = "CTO_CXP")
    private String ctoCxp;
    
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "PRECION_UNITARIO")
    private BigDecimal precioUnitario;
    
    
    
    public ErpCpOtrasDet(){
    
    }
    
    
    public ErpCpOtrasDet(ErpCpOtrasDetId id){
        
        this.id=id;
    
    }

    public ErpCpOtrasDetId getId() {
        return id;
    }

    public void setId(ErpCpOtrasDetId id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(Integer idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public String getIdTipoNegocio() {
        return idTipoNegocio;
    }

    public void setIdTipoNegocio(String idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }

    public String getIdPaisCxp() {
        return idPaisCxp;
    }

    public void setIdPaisCxp(String idPaisCxp) {
        this.idPaisCxp = idPaisCxp;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getCtoCxp() {
        return ctoCxp;
    }

    public void setCtoCxp(String ctoCxp) {
        this.ctoCxp = ctoCxp;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
   
    
    
}
