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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_CONCEPTOS")
public class ErpFeConceptos implements java.io.Serializable {
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeConceptosId id;
    
     @Column (name = "FOLIO")
    @JsonProperty("FOLIO")
    private String folio;
    
//    @Column (name = "SEC")
//    @JsonProperty("SEC")
//    private int sec;
    
    @Column (name = "CANTIDAD")
    @JsonProperty("CANTIDAD")
    private BigDecimal cantidad;
    
    @Column (name = "DESCRIPCION")
    @JsonProperty("DESCRIPCION")
    private String descripcion;
    
    @Column (name = "IMPORTE")
    @JsonProperty("IMPORTE")
    private BigDecimal importe;
    
    @Column (name = "NO_IDENTIFICACION")
    @JsonProperty("NO_IDENTIFICACION")
    private String noIdentificador;
    
    @Column (name = "UNIDAD")
    @JsonProperty("UNIDAD")
    private String unidad;
     
    @Column (name = "VALOR_UNITARIO")
    @JsonProperty("VALOR_UNITARIO")
    private BigDecimal valorUnitario;
    
    @Column (name = "CLAVE_UNIDAD")
    @JsonProperty("CLAVE_UNIDAD")
    private String claveUnidad;
    
    @Column (name = "CLAVE_PROD_SERV")
    @JsonProperty("CLAVE_PROD_SERV")
    private String claveProdServ;
    
    @Column (name = "DESCUENTO")
    @JsonProperty("DESCUENTO")
    private BigDecimal descuento;
    
      public ErpFeConceptos(){
       }
    
        public ErpFeConceptos(ErpFeConceptosId id){

            this.id = id;

        }

    public ErpFeConceptosId getId() {
        return id;
    }

    public String getFolio() {
        return folio;
    }

  

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public String getNoIdentificador() {
        return noIdentificador;
    }

    public String getUnidad() {
        return unidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setId(ErpFeConceptosId id) {
        this.id = id;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public void setNoIdentificador(String noIdentificador) {
        this.noIdentificador = noIdentificador;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getClaveProdServ() {
        return claveProdServ;
    }

    public void setClaveProdServ(String claveProdServ) {
        this.claveProdServ = claveProdServ;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
        
     
    
}
