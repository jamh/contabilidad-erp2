/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolizasDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @JsonProperty("COMPANIA")
    private String compania;
    @JsonProperty("TIPO_POLIZA")
    private String tipoPoliza;
    @JsonProperty("FECHA")
    private String fecha;
    @JsonProperty("NUMERO")
    private String numero;
    @JsonProperty("NOMBRE")
    private String nombre;
    @JsonProperty("DIVISA")
    private String divisa;
    @JsonProperty("PARIDAD")
    private BigDecimal paridad;
    @JsonProperty("FUENTE")
    private String fuente;
    @JsonProperty("REFERENCIA")
    private String referencia;
    @JsonProperty("DIVISA_NOM")
    private BigDecimal divisaNom;
    @JsonProperty("CARGOS_BASE")
    private BigDecimal cargosBase;
    @JsonProperty("ABONOS_BASE")
    private BigDecimal abonosBase;
    @JsonProperty("CARGOS")
    private BigDecimal cargos;
    @JsonProperty("ABONOS")
    private BigDecimal abonos;
    @JsonProperty("ESTATUS")
    private BigDecimal estatus;
    @JsonProperty("USUARIO")
    private String usuario; 
    
    @JsonProperty("FECHA_CAP")
    private String fechaCap;
    
    @JsonProperty("HORA")
    private String hora;
    @JsonProperty("MODULO_ORIG")
    private String moduloOrig;
    @JsonProperty("REVISO")
    private String reviso;
    @JsonProperty("AUTORIZO")
    private String autorizo;
    @JsonProperty("ID")
    private String id;
    
    
    
    @JsonProperty("NUM_CLC")
    private String numClc;
    
     @JsonProperty("PDF")
    private String pdf;
          @JsonProperty("XML")
    private String xml;
          
       @JsonProperty("UUID_RELACION")
    private String uuidRelacion;
    
   
    @JsonProperty("TIPO_SOLICITUD")
    private String tipoSolicitud;
    
  
    @JsonProperty("NUM_ORDEN")
    private String numOrden;
   
    @JsonProperty("NUM_TRAMITE")
    private String numTramite;   
    
    @JsonProperty("FECHA_FACT")
    private String fechaFact;
    
    @JsonProperty("NUMERO_FACT")
    private String numeroFact;
          
          
    @JsonProperty("id")
    private String id2;

//    public PolizasDTO(String compania, String tipoPoliza, Date fecha, String numero) {
//        this.compania = compania;
//        this.tipoPoliza = tipoPoliza;
//        this.fecha = fecha;
//        this.numero = numero;
//    }

    public PolizasDTO() {
    }
    
    

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

  

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public BigDecimal getParidad() {
        return paridad;
    }

    public void setParidad(BigDecimal paridad) {
        this.paridad = paridad;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFechaCap() {
        return fechaCap;
    }

    public void setFechaCap(String fechaCap) {
        this.fechaCap = fechaCap;
    }

   

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getCargosBase() {
        return cargosBase;
    }

    public void setCargosBase(BigDecimal cargosBase) {
        this.cargosBase = cargosBase;
    }

    public BigDecimal getAbonosBase() {
        return abonosBase;
    }

    public void setAbonosBase(BigDecimal abonosBase) {
        this.abonosBase = abonosBase;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }

    public BigDecimal getEstatus() {
        return estatus;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

 

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getModuloOrig() {
        return moduloOrig;
    }

    public void setModuloOrig(String moduloOrig) {
        this.moduloOrig = moduloOrig;
    }

    public String getReviso() {
        return reviso;
    }

    public void setReviso(String reviso) {
        this.reviso = reviso;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public String getId() {
        return id;
    }

    public String getNumClc() {
        return numClc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumClc(String numClc) {
        this.numClc = numClc;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getUuidRelacion() {
        return uuidRelacion;
    }

    public void setUuidRelacion(String uuidRelacion) {
        this.uuidRelacion = uuidRelacion;
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

    public String getFechaFact() {
        return fechaFact;
    }

    public void setFechaFact(String fechaFact) {
        this.fechaFact = fechaFact;
    }

    public String getNumeroFact() {
        return numeroFact;
    }

    public void setNumeroFact(String numeroFact) {
        this.numeroFact = numeroFact;
    }
    
    
    
    

       
}
