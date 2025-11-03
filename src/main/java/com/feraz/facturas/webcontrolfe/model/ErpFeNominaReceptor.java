/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_NOMINA_RECEPTOR")
public class ErpFeNominaReceptor implements java.io.Serializable{
    
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeNominaReceptorId id;
    
     @Column (name = "CLAVE_ENT_FED")
     private String claveEntFed;
     
     @Column (name = "SALARIO_DIARIO_INT")
     private BigDecimal salarioDiarioInt;
     
     @Column (name = "CUENTA_BANCARIA")
     private String cuentaBancario;
     
     @Column (name = "PERIODICIDAD_PAGO")
     private String periocidadPago;
     
     @Column (name = "RIESGO_PUESTO")
     private String riesgoPuesto;
     
     @Column (name = "PUESTO")
     private String puesto;
     
     @Column (name = "DEPARTAMENTO")
     private String departamento;
     
     @Column (name = "NUM_EMPLEADO")
     private String numEmpleado;
     
     @Column (name = "TIPO_REGIMEN")
     private String tipoRegimen;
     
     @Column (name = "TIPOJORNADA")
     private String tipoJornada;
     
     @Column (name = "SINDICALIZADO")
     private String sindicalizado;
     
     @Column (name = "TIPO_CONTRATO")
     private String tipoContrato;
     
     @Column (name = "ANTIGUEDAD")
     private String antiguedad;
     
     @Column (name = "FECHA_INI_REL_LABORAL")
     private String fechaIniRelLaboral;
     
     @Column (name = "NUM_SEG_SOCIAL")
     private String numSegSocial;
     
     @Column (name = "CURP")
     private String curp;
     
    
     public ErpFeNominaReceptor (){
     
     
     }
     
     public ErpFeNominaReceptor(ErpFeNominaReceptorId id){
         
         
         this.id = id;
     
     }

    public ErpFeNominaReceptorId getId() {
        return id;
    }

    public void setId(ErpFeNominaReceptorId id) {
        this.id = id;
    }

    public String getClaveEntFed() {
        return claveEntFed;
    }

    public void setClaveEntFed(String claveEntFed) {
        this.claveEntFed = claveEntFed;
    }

    public BigDecimal getSalarioDiarioInt() {
        return salarioDiarioInt;
    }

    public void setSalarioDiarioInt(BigDecimal salarioDiarioInt) {
        this.salarioDiarioInt = salarioDiarioInt;
    }

    public String getCuentaBancario() {
        return cuentaBancario;
    }

    public void setCuentaBancario(String cuentaBancario) {
        this.cuentaBancario = cuentaBancario;
    }

    public String getPeriocidadPago() {
        return periocidadPago;
    }

    public void setPeriocidadPago(String periocidadPago) {
        this.periocidadPago = periocidadPago;
    }

    public String getRiesgoPuesto() {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(String riesgoPuesto) {
        this.riesgoPuesto = riesgoPuesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(String tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public String getSindicalizado() {
        return sindicalizado;
    }

    public void setSindicalizado(String sindicalizado) {
        this.sindicalizado = sindicalizado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getFechaIniRelLaboral() {
        return fechaIniRelLaboral;
    }

    public void setFechaIniRelLaboral(String fechaIniRelLaboral) {
        this.fechaIniRelLaboral = fechaIniRelLaboral;
    }

    public String getNumSegSocial() {
        return numSegSocial;
    }

    public void setNumSegSocial(String numSegSocial) {
        this.numSegSocial = numSegSocial;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
     
     
     
}
