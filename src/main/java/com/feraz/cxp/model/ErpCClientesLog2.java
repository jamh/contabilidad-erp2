/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author lbadi
 */
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity 
@Table (name = "ERP_CCLIENTES_LOG2")
public class ErpCClientesLog2  implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false ,length = 10))
        ,
        @AttributeOverride(name = "folio",    column = @Column(name = "FOLIO",    nullable = false ))   
 })

private ErpCClientesLog2Id id;
 
@Column(name = "ID_CLIENTE")
private String id_Cliente;

@Column(name = "RFC")
private String rfc;

@Column(name = "FECHA")
@Temporal(javax.persistence.TemporalType.DATE)
private Date fecha;

@Column(name = "CUENTA_CLABE")
private String cuentClabe;

@Column(name = "NUMERO_CUENTA")
private String numeroCuenta;

@Column(name = "BANCO")
private String banco;

@Column(name = "CUENTA")
private String cuenta;

@Column(name = "USUARIO")
private String usuario;

@Column(name = "ESTATUS")
private String estatus;
 
public ErpCClientesLog2 () {
    
}

public ErpCClientesLog2 (ErpCClientesLog2Id id){}

    public ErpCClientesLog2Id getId() {
        return id;
    }

    public void setId(ErpCClientesLog2Id id) {
        this.id = id;
    }

    public String getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(String id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCuentClabe() {
        return cuentClabe;
    }

    public void setCuentClabe(String cuentClabe) {
        this.cuentClabe = cuentClabe;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "ErpCClientesLog2{" + "id=" + id + ", id_Cliente=" + id_Cliente + ", rfc=" + rfc + ", fecha=" + fecha + ", cuentClabe=" + cuentClabe + ", numeroCuenta=" + numeroCuenta + ", banco=" + banco + ", cuenta=" + cuenta + ", usuario=" + usuario + ", estatus=" + estatus + '}';
    }

   
  
  


}


