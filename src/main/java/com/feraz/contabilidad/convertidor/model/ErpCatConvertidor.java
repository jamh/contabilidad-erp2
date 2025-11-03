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
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_CAT_CONVERTIDOR")
public class ErpCatConvertidor implements Serializable {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 25)),
        @AttributeOverride(name = "idconcgasto", column = @Column(name = "IDCONCGASTO", nullable = false, length = 25)),
        @AttributeOverride(name = "noCaso", column = @Column(name = "NO_CASO", nullable = false))

    })

    //@JsonProperty("ID")
    private ErpCatConvertidorId id;

//    @JsonProperty("NO_CASO")
//    @Column(name = "NO_CASO")
//    private String noCaso;
    //@JsonProperty("T_POLIZA")
    @Column(name = "T_POLIZA")
    private String tPoliza;
    //@JsonProperty("ACTIVO")
    @Column(name = "ACTIVO")
    private String activo;

    //@JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    //@JsonProperty("NOMBRE")
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "RFC_EMISOR")
    private String rfcEmisor;
    
    @Column(name = "RFC_RECEPTOR")
    private String rfcReceptor;

    public ErpCatConvertidor() {
    }
    
    public ErpCatConvertidor(ErpCatConvertidorId id){
        
        this.id = id;
    
    }

//    public String getNoCaso() {
//        return noCaso;
//    }
//
//    public void setNoCaso(String noCaso) {
//        this.noCaso = noCaso;
//    }

    public String gettPoliza() {
        return tPoliza;
    }

    public void settPoliza(String tPoliza) {
        this.tPoliza = tPoliza;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ErpCatConvertidorId getId() {
        return id;
    }

    public void setId(ErpCatConvertidorId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }
    
    
    

    
    
}
