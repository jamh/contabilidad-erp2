/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.model;

/**
 *
 * @author vavi
 */

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_CAT_BANCO" )
public class ErpCatBanco implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "banco", column = @Column(name = "BANCO",nullable = false))
    
    })
     
      private ErpCatBancoId id;
     
     @Column (name = "NOMBRE")
     private String nombre;
     
     @Column (name = "ESTATUS")
     private String estatus;
     
     @Column (name = "AUXILIAR")
     private String auxiliar;
     
     @Column (name = "MONEDA")
     private String moneda;
     
     @Column (name = "CUENTA")
     private String cuenta;
     
     @Column (name = "BANCO_SAT")
     private String bancoSat;
     
     @Column (name = "CUENTA_CLABE")
     private String cuentaClabe;
     
     @Column (name = "CUENTA_BANCO")
     private String cuentaBanco;
     
     @Column (name = "ABA")
     private String aba;
     
     @Column (name = "SWIFT")
     private String swift;
     
     @Column (name = "TIPO_CUENTA")
     private String tipoCuenta;
     
     @Column (name = "SUCURSAL")
     private String sucursal;
     @Column (name = "BANCO_PAGO")
     private String bancoPago;
     
     @Column (name = "CUENTA_COMPLEMENTARIA")
     private String cuentaComplementaria;
     
    public ErpCatBanco(){
    
    }
    
    public ErpCatBanco(ErpCatBancoId id){

        this.id = id;
    }

    public ErpCatBancoId getId() {
        return id;
    }

    public void setId(ErpCatBancoId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getBancoSat() {
        return bancoSat;
    }

    public void setBancoSat(String bancoSat) {
        this.bancoSat = bancoSat;
    }

    public String getCuentaClabe() {
        return cuentaClabe;
    }

    public void setCuentaClabe(String cuentaClabe) {
        this.cuentaClabe = cuentaClabe;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getAba() {
        return aba;
    }

    public void setAba(String aba) {
        this.aba = aba;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getBancoPago() {
        return bancoPago;
    }

    public void setBancoPago(String bancoPago) {
        this.bancoPago = bancoPago;
    }

    public String getCuentaComplementaria() {
        return cuentaComplementaria;
    }

    public void setCuentaComplementaria(String cuentaComplementaria) {
        this.cuentaComplementaria = cuentaComplementaria;
    }
    
    
}
