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
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_CLIENTES")
public class ErpClientes implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "idCliente", column = @Column(name = "ID_CLIENTE", nullable = false, length = 12)),
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 3))

    })
     
     private ErpClientesId id;
    
     
      @Column(name = "NOMBRE")
      private String nombre;
      
      @Column(name = "RFC")
      private String rfc;
      
      @Column(name = "ACT_ECONOMICA")
      private String actEconomica;
      
      @Column(name = "F_ALTA")
     @Temporal(javax.persistence.TemporalType.DATE)
      private Date fAlta;
      
      @Column(name = "RAZON_SOCIAL")
      private String razonSocial;
      
      @Column(name = "PAPELERIA")
      private String papeleria;
      
      @Column(name = "T_PERSONA")
      private String tPersona;
      
      @Column(name = "T_CLIEPROV")
      private String tClieprov;
      
      @Column(name = "T_TERCERO")
      private String tTercero;
      
      @Column(name = "T_OPERACION")
      private String tOperacion;
      
      @Column(name = "ID_FISCAL")
      private String idFiscal;
      
      @Column(name = "NOMBRE_EXTRANJERO")
      private String nombreExtranjero;
      
      @Column(name = "PAIS_RESIDENCIA")
      private String paisResidencia;
      
      @Column(name = "NACIONALIDAD")
      private String nacionalidad;
      
      @Column(name = "AUXILIAR")
      private String auxiliar;
      
      @Column(name = "CTO_CTO")
      private String ctoCto;
      
      @Column(name = "TIPO_INGRESO")
      private String tipoIngreso;
      
      @Column(name = "CUENTA")
      private String cuentas;
      
      @Column(name = "BANCO")
      private String banco;
      
      @Column(name = "NUMERO_CUENTA")
      private String numeroCuenta;
      
      @Column(name = "CUENTA_CLABE")
      private String cuentaClabe;
      
      @Column(name = "CORREO")
      private String correo;
      
      @Column(name = "CUENTA_COMPLEMENTARIA")
      private String cuentaComplementaria;
    
      @Column(name = "CUENTA_INGRESO")
      private String cuentaIngreso;

      
      public ErpClientes(){
      
      }
      
      public ErpClientes(ErpClientesId id){
          
          this.id = id;
      }

    public ErpClientesId getId() {
        return id;
    }

    public void setId(ErpClientesId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getActEconomica() {
        return actEconomica;
    }

    public void setActEconomica(String actEconomica) {
        this.actEconomica = actEconomica;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPapeleria() {
        return papeleria;
    }

    public void setPapeleria(String papeleria) {
        this.papeleria = papeleria;
    }

    public String gettPersona() {
        return tPersona;
    }

    public void settPersona(String tPersona) {
        this.tPersona = tPersona;
    }

    public String gettClieprov() {
        return tClieprov;
    }

    public void settClieprov(String tClieprov) {
        this.tClieprov = tClieprov;
    }

    public String gettTercero() {
        return tTercero;
    }

    public void settTercero(String tTercero) {
        this.tTercero = tTercero;
    }

    public String gettOperacion() {
        return tOperacion;
    }

    public void settOperacion(String tOperacion) {
        this.tOperacion = tOperacion;
    }

    public String getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(String idFiscal) {
        this.idFiscal = idFiscal;
    }

    public String getNombreExtranjero() {
        return nombreExtranjero;
    }

    public void setNombreExtranjero(String nombreExtranjero) {
        this.nombreExtranjero = nombreExtranjero;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public String getCuentas() {
        return cuentas;
    }

    public void setCuentas(String cuentas) {
        this.cuentas = cuentas;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCuentaClabe() {
        return cuentaClabe;
    }

    public void setCuentaClabe(String cuentaClabe) {
        this.cuentaClabe = cuentaClabe;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCuentaComplementaria() {
        return cuentaComplementaria;
    }

    public void setCuentaComplementaria(String cuentaComplementaria) {
        this.cuentaComplementaria = cuentaComplementaria;
    }
    
     public String getCuentaIngreso() {
        return cuentaIngreso;
    }

    public void setCuentaIngreso(String cuentaIngreso) {
        this.cuentaIngreso = cuentaIngreso;
    }
    
      
      
}
