package com.feraz.polizas3.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "POLIZAS")
public class Polizas implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false, length = 10))
    })    
    private PolizasId id;
    
    @JsonProperty("COMPANIA")
    @Transient
    private String companiaTemp;
    
    @JsonProperty("TIPO_POLIZA")
    @Transient
    private String tipoPolizaTemp;
    
    @JsonProperty("FECHA")
    @Transient
    private Date fechaTemp;
    
    @JsonProperty("NUMERO")
    @Transient
    private String numeroTemp;

    
    @Column (name = "NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;
    
    @Column (name = "DIVISA")
    @JsonProperty("DIVISA")
    private String divisa;
    
    @Column (name = "PARIDAD")
    @JsonProperty("PARIDAD")
    private BigDecimal paridad;
    
    @Column (name = "FUENTE")
    @JsonProperty("FUENTE")
    private String fuente;
    
    @Column (name = "REFERENCIA")
    @JsonProperty("REFERENCIA")
    private String referencia;
    
    @Column (name = "CARGOS_BASE")
    @JsonProperty("CARGOS_BASE")
    private BigDecimal cargosBase;
    
    @Column (name = "ABONOS_BASE")
    @JsonProperty("ABONOS_BASE")
    private BigDecimal abonosBase;
    
    @Column (name = "CARGOS")
    @JsonProperty("CARGOS")
    private BigDecimal cargos;
    
    @Column (name = "ABONOS")
    @JsonProperty("ABONOS")
    private BigDecimal abonos;
    
    @Column (name = "ESTATUS")
    @JsonProperty("ESTATUS")
    private BigDecimal estatus;
    
    @Column (name = "USUARIO")
    @JsonProperty("USUARIO")
    private String usuario;
    
    @Column (name = "FECHA_CAP")
    @JsonProperty("FECHA_CAP")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaCap;
    
    @Column (name = "HORA")
    @JsonProperty("HORA")
    private String hora;
    
    @Column (name = "MODULO_ORIG")
    @JsonProperty("MODULO_ORIG")
    private String moduloOrig;
    
    @Column (name = "REVISO")
    @JsonProperty("REVISO")
    private String reviso;
    
    @Column (name = "AUTORIZO")    
    @JsonProperty("AUTORIZO")
    private String autorizo;
    
    @Column (name = "TIPO_SOLICITUD")    
    @JsonProperty("TIPO_SOLICITUD")
    private String tipoSolicitud;
    
    @Column (name = "NUM_ORDEN")    
    @JsonProperty("NUM_ORDEN")
    private String numOrden;
    
    @Column (name = "NUM_TRAMITE")    
    @JsonProperty("NUM_TRAMITE")
    private String numTramite;

    
    
    public Polizas(){
    }
    
    public Polizas(PolizasId id){
        
        this.id = id;
        
    }

    public PolizasId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDivisa() {
        return divisa;
    }

    public BigDecimal getParidad() {
        return paridad;
    }

    public String getFuente() {
        return fuente;
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

    public BigDecimal getEstatus() {
        return estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public Date getFechaCap() {
        return fechaCap;
    }

    public String getHora() {
        return hora;
    }

    public String getModuloOrig() {
        return moduloOrig;
    }

    public String getReviso() {
        return reviso;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setId(PolizasId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public void setParidad(BigDecimal paridad) {
        this.paridad = paridad;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
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

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFechaCap(Date fechaCap) {
        this.fechaCap = fechaCap;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setModuloOrig(String moduloOrig) {
        this.moduloOrig = moduloOrig;
    }

    public void setReviso(String reviso) {
        this.reviso = reviso;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public void setCompaniaTemp(String companiaTemp) {
        this.companiaTemp = companiaTemp;
    }

    public void setTipoPolizaTemp(String tipoPolizaTemp) {
        this.tipoPolizaTemp = tipoPolizaTemp;
    }

    public void setFechaTemp(Date fechaTemp) {
        this.fechaTemp = fechaTemp;
    }

    public String getCompaniaTemp() {
        return companiaTemp;
    }

    public String getTipoPolizaTemp() {
        return tipoPolizaTemp;
    }

    public Date getFechaTemp() {
        return fechaTemp;
    }

    public String getNumeroTemp() {
        return numeroTemp;
    }

    public void setNumeroTemp(String numeroTemp) {
        this.numeroTemp = numeroTemp;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(String numOrden) {
        this.numOrden = numOrden;
    }

    public String getNumTramite() {
        return numTramite;
    }

    public void setNumTramite(String numTramite) {
        this.numTramite = numTramite;
    }

    
    
    @Override
    public String toString(){
        return id.getCompania()+":"+id.getNumero()+":"+id.getTipoPoliza();
    }
    
}
