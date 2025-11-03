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
@Table(name = "ERP_CAJA_CHICA_DET")
public class ErpCajaChicaDet implements java.io.Serializable{
    
       @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "idCaja", column = @Column(name = "ID_CAJA", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })

    private ErpCajaChicaDetId id;

    @Column(name = "USUARIO_CAJA")
    private Integer usuarioCaja;
    
    @Column(name = "IMPORTE_DET")
    private BigDecimal importeDet;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "CTO_CTO")
    private String ctoCto;
    
    @Column(name = "ID_TIPO_NEGOCIO")
    private String idTipoNegocio;
    
    @Column(name = "ID_PAIS_CXP")
    private String idPaisCxp;
    
    @Column(name = "USUARIO_CAP")
    private String usuarioCap;
    
    
    @Column(name = "FECHA")
       @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "ESTATUS")
    private String estatus;
    
    
    public ErpCajaChicaDet(){
    
    }
    
    public ErpCajaChicaDet(ErpCajaChicaDetId id){
    
        this.id = id;
    }

    public ErpCajaChicaDetId getId() {
        return id;
    }

    public void setId(ErpCajaChicaDetId id) {
        this.id = id;
    }

    public Integer getUsuarioCaja() {
        return usuarioCaja;
    }

    public void setUsuarioCaja(Integer usuarioCaja) {
        this.usuarioCaja = usuarioCaja;
    }

    public BigDecimal getImporteDet() {
        return importeDet;
    }

    public void setImporteDet(BigDecimal importeDet) {
        this.importeDet = importeDet;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
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

    public String getUsuarioCap() {
        return usuarioCap;
    }

    public void setUsuarioCap(String usuarioCap) {
        this.usuarioCap = usuarioCap;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
