/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author JAMH
 */

@Entity
@Table(name = "ERP_DET_CONVERTIDOR")
public class ErpDetConvertidor implements Serializable {

    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 25)),
        @AttributeOverride(name = "idconcgasto", column = @Column(name = "IDCONCGASTO", nullable = false, length = 25)),
        @AttributeOverride(name = "noCaso", column = @Column(name = "NO_CASO", nullable = false, length = 25)),
         @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false, length = 25))

    })

    //@JsonProperty("ID")
    private ErpDetConvertidorId id;
      
    //JsonProperty("IDCAMPO")
    @Column(name = "IDCAMPO")
    private String idcampo;


    //JsonProperty("CUENTA")
    @Column(name = "CUENTA")
    private String cuenta;
    //JsonProperty("T_APLICACION")
    @Column(name = "T_APLICACION")
    private String tAplicacion;
    //JsonProperty("IDOPERACION")
    @Column(name = "IDOPERACION")
    private String idoperacion;
    //@JsonProperty("PROYECTO")
    @Column(name = "PROYECTO")
    private String proyecto;
    //@JsonProperty("PARTIDA")
    @Column(name = "PARTIDA")
    private String partida;
    //@JsonProperty("IDTPAGO")
    @Column(name = "IDTPAGO")
    private String idtpago;
    //@JsonProperty("T_PERSONA")
    @Column(name = "T_PERSONA")
    private String tPersona;
    
    //@JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //@JsonProperty("REFERENCIA")
    @Column(name = "REFERENCIA")
    private String referencia;
    
    @Column(name = "REFERENCIA2")
    private String referencia2;
    
    @Column(name = "OPERACIONES")
    private String operaciones;
    
     @Column(name = "RFC")
    private String rfc;
     
     @Column(name = "CONCEPTO_DIOT")
    private String conceptoDiot;
     
     @Column (name = "C_COSTOS")
     private String c_costos;
     
     @Column (name = "ORDEN")
     private Integer orden;
     
     
    
    

    public ErpDetConvertidor() {
    }
    
    public ErpDetConvertidor(ErpDetConvertidorId id){
        
        this.id = id;
    
    }

    public String getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(String idcampo) {
        this.idcampo = idcampo;
    }

  

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String gettAplicacion() {
        return tAplicacion;
    }

    public void settAplicacion(String tAplicacion) {
        this.tAplicacion = tAplicacion;
    }

    public String getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(String idoperacion) {
        this.idoperacion = idoperacion;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getIdtpago() {
        return idtpago;
    }

    public void setIdtpago(String idtpago) {
        this.idtpago = idtpago;
    }

    public String gettPersona() {
        return tPersona;
    }

    public void settPersona(String tPersona) {
        this.tPersona = tPersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public ErpDetConvertidorId getId() {
        return id;
    }

    public void setId(ErpDetConvertidorId id) {
        this.id = id;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getConceptoDiot() {
        return conceptoDiot;
    }

    public void setConceptoDiot(String conceptoDiot) {
        this.conceptoDiot = conceptoDiot;
    }

    public String getC_costos() {
        return c_costos;
    }

    public void setC_costos(String c_costos) {
        this.c_costos = c_costos;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getReferencia2() {
        return referencia2;
    }

    public void setReferencia2(String referencia2) {
        this.referencia2 = referencia2;
    }

  
    
    
    

}
