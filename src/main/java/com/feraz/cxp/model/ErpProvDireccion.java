/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

/**
 *
 * @author 55555
 */

import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_PROV_DIRECCION")
public class ErpProvDireccion implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "idProveedor", column = @Column(name = "ID_PROVEEDOR", nullable = false, length = 12))

    })
     
     private ErpProvDireccionId id;
    
     
      @Column(name = "SEC")
      private Integer sec;
      
      @Column(name = "CALLE")
      private String calle;
      
      @Column(name = "NO_EXTERIOR")
      private String noExterior;
      
      @Column(name = "NO_INTERIOR")
      private String noInterior;
      
      @Column(name = "COLONIA")
      private String colonia;
      
      @Column(name = "LOCALIDAD")
      private String localidad;
      
      @Column(name = "DELEGACION")
      private String delegacion;
      
      @Column(name = "ESTADO")
      private String estado;
      
      @Column(name = "PAIS")
      private String pais;
      
      @Column(name = "CP")
      private String cp;
      
      @Column(name = "DIRECCION")
      private String direccion;
      
      
    
      
      public ErpProvDireccion(){
      
      }
      
      public ErpProvDireccion(ErpProvDireccionId id){
          
          this.id = id;
      
      }

    public ErpProvDireccionId getId() {
        return id;
    }

    public void setId(ErpProvDireccionId id) {
        this.id = id;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
      
      
      
}
