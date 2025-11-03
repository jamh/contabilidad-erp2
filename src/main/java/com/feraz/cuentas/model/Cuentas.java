/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.model;

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
@Table(name = "CUENTAS")
public class Cuentas implements java.io.Serializable{
    @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "estructura", column = @Column(name = "ESTRUCTURA", nullable = false, length = 10)),
        @AttributeOverride(name = "cuenta", column = @Column(name = "CUENTA", nullable = false, length = 50))
    })
    
     @JsonProperty("ID2")
    private CuentasId id;
    
    @JsonProperty("NOMBRE")
    @Column(name = "NOMBRE")
    private String nombre;
    
    @JsonProperty("AFECTABLE")
    @Column(name = "AFECTABLE")
    private String afectable;
    
    @JsonProperty("CIERRE")
    @Column(name = "CIERRE")
    private String cierre;
    
    @JsonProperty("ESTATUS")
    @Column(name = "ESTATUS")
    private String estatus;
    
    @JsonProperty("NATURALEZA")
    @Column(name = "NATURALEZA")
    private String naturaleza;
    
    @JsonProperty("CLAVE_PRESUP")
    @Column(name = "CLAVE_PRESUP")
    private String clavePresp;
    
    @JsonProperty("TIPO")
    @Column(name = "TIPO")
    private String tipo;
    
    @JsonProperty("MAYOR")
    @Column(name = "MAYOR")
    private String mayor;
    
    @JsonProperty("CTA_COMPLEMENTARIA")
    @Column(name = "CTA_COMPLEMENTARIA")
    private String ctaComplementaria;
    
    @JsonProperty("DIVISA")
    @Column(name = "DIVISA")
    private String divisa;
    
    @JsonProperty("CUENTA_SAT")
    @Column(name = "CUENTA_SAT")
    private String cuentaSat;
    
    @JsonProperty("CUENTA_ALIAS")
    @Column(name = "CUENTA_ALIAS")
    private String cuentaAlias;
    
     @JsonProperty("ID")
    @Column(name = "ID")
    private int idCuentas;
     
      @JsonProperty("BANCO")
    @Column(name = "BANCO")
    private String banco;
      
     @JsonProperty("CUENTA_PADRE")
    @Column(name = "CUENTA_PADRE")
    private String cuentaPadre;
     
      @JsonProperty("ID_ORD_BALANZA")
    @Column(name = "ID_ORD_BALANZA")
    private String idOrdenBalanza;
      
      @JsonProperty("USUARIO")
    @Column(name = "USUARIO")
    private String usuario;
      
      @JsonProperty("CLASIFICACION1")
    @Column(name = "CLASIFICACION1")
    private String clasificacion1;
      
      @JsonProperty("CLASIFICACION2")
    @Column(name = "CLASIFICACION2")
    private String clasificacion2;
      
            @JsonProperty("NOMBRE2")
    @Column(name = "NOMBRE2")
      private String nombre2;
    
     public Cuentas(){
    
    }
    
    public Cuentas(CuentasId id){
        
        this.id = id;
        
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    
    
    
    public CuentasId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAfectable() {
        return afectable;
    }

    public String getCierre() {
        return cierre;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public String getClavePresp() {
        return clavePresp;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMayor() {
        return mayor;
    }

    public String getCtaComplementaria() {
        return ctaComplementaria;
    }

    public String getDivisa() {
        return divisa;
    }

    public String getCuentaSat() {
        return cuentaSat;
    }

    public String getCuentaAlias() {
        return cuentaAlias;
    }

    public void setId(CuentasId id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAfectable(String afectable) {
        this.afectable = afectable;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public void setClavePresp(String clavePresp) {
        this.clavePresp = clavePresp;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public void setCtaComplementaria(String ctaComplementaria) {
        this.ctaComplementaria = ctaComplementaria;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public void setCuentaSat(String cuentaSat) {
        this.cuentaSat = cuentaSat;
    }

    public void setCuentaAlias(String cuentaAlias) {
        this.cuentaAlias = cuentaAlias;
    }

    public int getIdCuentas() {
        return idCuentas;
    }

    public void setIdCuentas(int idCuentas) {
        this.idCuentas = idCuentas;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuentaPadre() {
        return cuentaPadre;
    }

    public void setCuentaPadre(String cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }

    public String getIdOrdenBalanza() {
        return idOrdenBalanza;
    }

    public void setIdOrdenBalanza(String idOrdenBalanza) {
        this.idOrdenBalanza = idOrdenBalanza;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClasificacion1() {
        return clasificacion1;
    }

    public void setClasificacion1(String clasificacion1) {
        this.clasificacion1 = clasificacion1;
    }

    public String getClasificacion2() {
        return clasificacion2;
    }

    public void setClasificacion2(String clasificacion2) {
        this.clasificacion2 = clasificacion2;
    }
    
    
    
    
    
}
