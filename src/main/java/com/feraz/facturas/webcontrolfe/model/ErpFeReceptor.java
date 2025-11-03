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
@Table(name = "ERP_FE_RECEPTOR")
public class ErpFeReceptor implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeReceptorId id;
    
     @Column (name = "RFC")
    @JsonProperty("RFC")
    private String rfc;
     
    @Column (name = "NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;
    
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
    
    @Column (name = "USO_CFDI")
    @JsonProperty("USO_CFDI")
    private String usoCfdi;
    
        @Column (name = "REGIMEN_FISCAL_RECEPTOR")
    @JsonProperty("REGIMEN_FISCAL_RECEPTOR")
    private String regimenFiscalReceptor;
        
            @Column (name = "DOMICILIO_FISCAL_RECEPTOR")
    @JsonProperty("DOMICILIO_FISCAL_RECEPTOR")
    private String domicilioFiscalReceptor;
    
    
    
    
    public ErpFeReceptor(){
       }
    
        public ErpFeReceptor(ErpFeReceptorId id){

            this.id = id;

        }

    public ErpFeReceptorId getId() {
        return id;
    }

    public String getRfc() {
        return rfc;
    }

    public String getNombre() {
        return nombre;
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

    public void setId(ErpFeReceptorId id) {
        this.id = id;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getUsoCfdi() {
        return usoCfdi;
    }

    public void setUsoCfdi(String usoCfdi) {
        this.usoCfdi = usoCfdi;
    }

    public String getRegimenFiscalReceptor() {
        return regimenFiscalReceptor;
    }

    public void setRegimenFiscalReceptor(String regimenFiscalReceptor) {
        this.regimenFiscalReceptor = regimenFiscalReceptor;
    }

    public String getDomicilioFiscalReceptor() {
        return domicilioFiscalReceptor;
    }

    public void setDomicilioFiscalReceptor(String domicilioFiscalReceptor) {
        this.domicilioFiscalReceptor = domicilioFiscalReceptor;
    }
        
    
    
    
}
