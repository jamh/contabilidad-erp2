/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.model;

/**
 *
 * @author Ing. JAMH
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Ing. JAMH
 */
@Entity
@Table(name = "ERP_NOM_POLIZA")

public class ErpNomPoliza implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))

    })

    private ErpNomPolizaId id;

    @Column(name = "PID")
    private String pid;

    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "TIPO_OPERACION")
    private String tipoOperacion;

    @Column(name = "NUMERO_MOVIMIENTOS")
    private String numeroMovimientos;

    @Column(name = "CALENDARIO")
    private int calendario;

    @Column(name = "PERIODO")
    private int periodo;

    @Column(name = "FECHA_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "GRUPO_PAGO")
    private String grupoPago;

    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Column(name = "TIPO_NOMINA")
    private String tipoNomina;

    @Column(name = "PROCESO_ESPECIAL")
    private String procesoEspecial;

    @Column(name = "CENTRO_COSTOS")
    private String centroCostos;

    @Column(name = "CUENTA")
    private String cuenta;

    @Column(name = "CARGOS")
    private BigDecimal cargos;

    @Column(name = "ABONOS")
    private BigDecimal abonos;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "DESCRIPCION_NOM")
    private String descripcionNom;
    @Column(name = "DESCRIPCION2")
    private String descripcion2;

    @Column(name = "DIC_TIMBRADO")
    private String dicTimbrado;
    
    @Column(name = "EMPLEADO")
    private String empleado;

    
    
    
    public ErpNomPoliza() {

    }

    public ErpNomPoliza(ErpNomPolizaId id) {

        this.id = id;

    }

    public ErpNomPolizaId getId() {
        return id;
    }

    public String getCompania() {
        return compania;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public String getNumeroMovimientos() {
        return numeroMovimientos;
    }

    public int getCalendario() {
        return calendario;
    }

    public int getPeriodo() {
        return periodo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public String getGrupoPago() {
        return grupoPago;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public String getProcesoEspecial() {
        return procesoEspecial;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getCuenta() {
        return cuenta;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setId(ErpNomPolizaId id) {
        this.id = id;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public void setNumeroMovimientos(String numeroMovimientos) {
        this.numeroMovimientos = numeroMovimientos;
    }

    public void setCalendario(int calendario) {
        this.calendario = calendario;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setGrupoPago(String grupoPago) {
        this.grupoPago = grupoPago;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public void setProcesoEspecial(String procesoEspecial) {
        this.procesoEspecial = procesoEspecial;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionNom() {
        return descripcionNom;
    }

    public void setDescripcionNom(String descripcionNom) {
        this.descripcionNom = descripcionNom;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDicTimbrado() {
        return dicTimbrado;
    }

    public void setDicTimbrado(String dicTimbrado) {
        this.dicTimbrado = dicTimbrado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
 
    
    

}
