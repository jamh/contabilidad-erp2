/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "DET_POLIZAS")
public class DetPolizas implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
        @AttributeOverride(name = "cuenta", column = @Column(name = "CUENTA", nullable = false, length = 50))
    })
     @JsonProperty("ID2")
    private DetPolizasId id;
    
    @JsonProperty("C_COSTOS")
    @Column(name = "C_COSTOS")
    private String cCostos;
    
    @JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JsonProperty("REFERENCIA")
    @Column(name = "REFERENCIA")
    private String referencia;
    
    @JsonProperty("CARGOS_BASE")
    @Column(name = "CARGOS_BASE")
    private BigDecimal cargosBase;
    
    @JsonProperty("ABONOS_BASE")
    @Column(name = "ABONOS_BASE")
    private BigDecimal abonosBase;
    
    @JsonProperty("CARGOS")
    @Column(name = "CARGOS")
    private BigDecimal cargos;
    
    @JsonProperty("ABONOS")
    @Column(name = "ABONOS")
    private BigDecimal abonos;
    
    @JsonProperty("RFC")
    @Column(name = "RFC")
    private String rfc;
    
    @JsonProperty("INDICADOR")
    @Column(name = "INDICADOR")
    private String indicador;
    
    @JsonProperty("FECHA_CAP")
    @Column(name = "FECHA_CAP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCap;
    
    @JsonProperty("CHEQUE")
    @Column(name = "CHEQUE")
    private String cheque;
    
    @JsonProperty("ESTATUS")
    @Column(name = "ESTATUS")
    private String estatus;
    
    @JsonProperty("HORA")
    @Column(name = "HORA")
    private String hora;
    
    @JsonProperty("CTO_TRABAJO")
    @Column(name = "CTO_TRABAJO")
    private String ctoTrabajo;
    
    @JsonProperty("REFERENCIA2")
    @Column(name = "REFERENCIA2")
    private String referencia2;
    
    @JsonProperty("FECHA_DOCUMENTO")
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDocumento;
       @JsonProperty("CUENTA")
    @Column(name = "CUENTA")
    private String cuenta;
       
         @JsonProperty("TC_PAGO")
    @Column(name = "TC_PAGO")
    private String tcPago;
         
           @JsonProperty("TC_PROV")
    @Column(name = "TC_PROV")
    private String tcProv;
           
             @JsonProperty("DIVISA")
    @Column(name = "DIVISA")
    private String divisa;
             
    @JsonProperty("ID_TRANSACCION")
    @Column(name = "ID_TRANSACCION")
    private String idTrasaccion;
    
    public DetPolizas(){
    
    }
    
    public DetPolizas(DetPolizasId id){
        
        this.id = id;
        
    }

    public DetPolizasId getId() {
        return id;
    }

    public String getcCostos() {
        return cCostos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public BigDecimal getCargosBase() {
        return cargosBase;
    }

    public BigDecimal getAbonosBase() {
        return abonosBase;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public String getRfc() {
        return rfc;
    }

    public String getIndicador() {
        return indicador;
    }

    public Date getFechaCap() {
        return fechaCap;
    }

    public String getCheque() {
        return cheque;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getHora() {
        return hora;
    }

    public String getCtoTrabajo() {
        return ctoTrabajo;
    }

    public String getReferencia2() {
        return referencia2;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setId(DetPolizasId id) {
        this.id = id;
    }

    public void setcCostos(String cCostos) {
        this.cCostos = cCostos;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setCargosBase(BigDecimal cargosBase) {
        this.cargosBase = cargosBase;
    }

    public void setAbonosBase(BigDecimal abonosBase) {
        this.abonosBase = abonosBase;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public void setFechaCap(Date fechaCap) {
        this.fechaCap = fechaCap;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setCtoTrabajo(String ctoTrabajo) {
        this.ctoTrabajo = ctoTrabajo;
    }

    public void setReferencia2(String referencia2) {
        this.referencia2 = referencia2;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTcPago() {
        return tcPago;
    }

    public void setTcPago(String tcPago) {
        this.tcPago = tcPago;
    }

    public String getTcProv() {
        return tcProv;
    }

    public void setTcProv(String tcProv) {
        this.tcProv = tcProv;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getIdTrasaccion() {
        return idTrasaccion;
    }

    public void setIdTrasaccion(String idTrasaccion) {
        this.idTrasaccion = idTrasaccion;
    }
    
    
    
    
    
}
