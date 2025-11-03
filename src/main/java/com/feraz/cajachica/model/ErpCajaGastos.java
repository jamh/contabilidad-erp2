/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

/**
 *
 * @author LENOVO
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
@Table(name = "ERP_CAJA_GASTOS")
public class ErpCajaGastos implements java.io.Serializable{
    
       
       @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "idCaja", column = @Column(name = "ID_CAJA", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
        @AttributeOverride(name = "idGasto", column = @Column(name = "ID_GASTO", nullable = false))
            
    })

    private ErpCajaGastosId id;

    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "MONEDA")
    private String moneda;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    @Column(name = "FECHA")
       @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "EMPLEADO")
    private String empleado;
    
    public ErpCajaGastos(){
    
    }
    
    
    public ErpCajaGastos(ErpCajaGastosId id){
    
            this.id = id;
    }

    public ErpCajaGastosId getId() {
        return id;
    }

    public void setId(ErpCajaGastosId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    
}
