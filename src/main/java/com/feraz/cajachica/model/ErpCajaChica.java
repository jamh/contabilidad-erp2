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
@Table(name = "ERP_CAJA_CHICA")
public class ErpCajaChica implements java.io.Serializable{
    
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
    })

    private ErpCajaChicaId id;

    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    @Column(name = "CTO_CTO")
    private String ctocto;
    
    @Column(name = "ID_TIPO_NEGOCIO")
    private String idTipoNegocio;
    
    @Column(name = "ID_PAIS_CXP")
    private String idPaisCxp;
    
    @Column(name = "IMPORTE_RESTANTE")
    private BigDecimal importeRestante;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "USUARIO_CAP")
    private String usuarioCap;
    
    @Column(name = "BANCO")
    private String banco;
    
    @Column(name = "ESTATUS")
    private String estatus;
    
    public ErpCajaChica(){
    
    }
    
    public ErpCajaChica(ErpCajaChicaId id){
        
        this.id = id;
    
    }

    public ErpCajaChicaId getId() {
        return id;
    }

    public void setId(ErpCajaChicaId id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getCtocto() {
        return ctocto;
    }

    public void setCtocto(String ctocto) {
        this.ctocto = ctocto;
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

    public BigDecimal getImporteRestante() {
        return importeRestante;
    }

    public void setImporteRestante(BigDecimal importeRestante) {
        this.importeRestante = importeRestante;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioCap() {
        return usuarioCap;
    }

    public void setUsuarioCap(String usuarioCap) {
        this.usuarioCap = usuarioCap;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
