/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_EMISOR")
public class ErpFeEmisor implements java.io.Serializable {
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeEmisorId id;
    
     @Column (name = "NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;
    
    @Column (name = "RFC")
    @JsonProperty("RFC")
    private String rfc;
    
    @Column (name = "CALLE")
    @JsonProperty("CALLE")
    private String calle;
    
    @Column (name = "CODIGO_POSTAL")
    @JsonProperty("CODIGO_POSTAL")
    private String codigoPostal;
    
    @Column (name = "COLONIA")
    @JsonProperty("COLONIA")
    private String colonia;
    
    @Column (name = "ESTADO")
    @JsonProperty("ESTADO")
    private String estado;
    
    @Column (name = "LOCALIDAD")
    @JsonProperty("LOCALIDAD")
    private String localidad;
    
    @Column (name = "MUNICIPIO")
    @JsonProperty("MUNICIPIO")
    private String municipio;
    
    @Column (name = "NO_INTERIOR")
    @JsonProperty("NO_INTERIOR")
    private String noInterior;
    
    @Column (name = "NO_EXTERIOR")
    @JsonProperty("NO_EXTERIOR")
    private String noExterior;
    
    @Column (name = "PAIS")
    @JsonProperty("PAIS")
    private String pais;
    
    @Column (name = "REFERENCIA")
    @JsonProperty("REFERENCIA")
    private String referencia;
    
    @Column (name = "EXP_COLONIA")
    @JsonProperty("EXP_COLONIA")
    private String expColonia;
    
    @Column (name = "EXP_ESTADO")
    @JsonProperty("EXP_ESTADO")
    private String expEstado;
    
    @Column (name = "EXP_LOCALIDAD")
    @JsonProperty("EXP_LOCALIDAD")
    private String expLocalidad;
    
    @Column (name = "EXP_MUNICIPIO")
    @JsonProperty("EXP_MUNICIPIO")
    private String expMunicipio;
    
    @Column (name = "EXP_NO_INTERIOR")
    @JsonProperty("EXP_NO_INTERIOR")
    private String expNoInterior;
    
    @Column (name = "EXP_NO_EXTERIOR")
    @JsonProperty("EXP_NO_EXTERIOR")
    private String expNoExterior;
    
    @Column (name = "EXP_PAIS")
    @JsonProperty("EXP_PAIS")
    private String expPais;
    
    @Column (name = "EXP_REFERENCIA")
    @JsonProperty("EXP_REFERENCIA")
    private String expReferencia;
    
    @Column (name = "REGIMEN_FISCAL")
    @JsonProperty("REGIMEN_FISCAL")
    private String regimenFiscal;
    
      public ErpFeEmisor(){
       }
    
        public ErpFeEmisor(ErpFeEmisorId id){

            this.id = id;

        }

    public ErpFeEmisorId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public String getCalle() {
        return calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public String getEstado() {
        return estado;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public String getPais() {
        return pais;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getExpColonia() {
        return expColonia;
    }

    public String getExpEstado() {
        return expEstado;
    }

    public String getExpLocalidad() {
        return expLocalidad;
    }

    public String getExpMunicipio() {
        return expMunicipio;
    }

    public String getExpNoInterior() {
        return expNoInterior;
    }

    public String getExpNoExterior() {
        return expNoExterior;
    }

    public String getExpPais() {
        return expPais;
    }

    public String getExpReferencia() {
        return expReferencia;
    }

    public void setId(ErpFeEmisorId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setExpColonia(String expColonia) {
        this.expColonia = expColonia;
    }

    public void setExpEstado(String expEstado) {
        this.expEstado = expEstado;
    }

    public void setExpLocalidad(String expLocalidad) {
        this.expLocalidad = expLocalidad;
    }

    public void setExpMunicipio(String expMunicipio) {
        this.expMunicipio = expMunicipio;
    }

    public void setExpNoInterior(String expNoInterior) {
        this.expNoInterior = expNoInterior;
    }

    public void setExpNoExterior(String expNoExterior) {
        this.expNoExterior = expNoExterior;
    }

    public void setExpPais(String expPais) {
        this.expPais = expPais;
    }

    public void setExpReferencia(String expReferencia) {
        this.expReferencia = expReferencia;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }
        
     
    
}
