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
@Table(name = "ERP_FE_NOMINA")
public class ErpFeNomina implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeNominaId id;
    
     @Column (name = "TOTAL_DEDUCCIONES")
     private BigDecimal totalDeducciones;
     
     @Column (name = "TOTAL_PERCEPCIONES")
     private BigDecimal totalPercepciones;
     
     @Column (name = "NUM_DIAS_PAGADOS")
     private String numDiasPagados;
     
     @Column (name = "FECHA_FINAL_PAGO")
     private String fechaFinalPago;
     
     @Column (name = "FECHA_INICIAL_PAGO")
     private String fechaInicialPago;
     
     @Column (name = "FECHA_PAGO")
     private String fechaPago;
     
     @Column (name = "TIPO_NOMINA")
     private String tipoNomina;
     
     @Column (name = "VERSION")
     private String version;
     
     
     public ErpFeNomina(){
     
     }
     
     public ErpFeNomina(ErpFeNominaId id){
     
         this.id = id;
     }

    public ErpFeNominaId getId() {
        return id;
    }

    public void setId(ErpFeNominaId id) {
        this.id = id;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(BigDecimal totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(BigDecimal totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public String getNumDiasPagados() {
        return numDiasPagados;
    }

    public void setNumDiasPagados(String numDiasPagados) {
        this.numDiasPagados = numDiasPagados;
    }

    public String getFechaFinalPago() {
        return fechaFinalPago;
    }

    public void setFechaFinalPago(String fechaFinalPago) {
        this.fechaFinalPago = fechaFinalPago;
    }

    public String getFechaInicialPago() {
        return fechaInicialPago;
    }

    public void setFechaInicialPago(String fechaInicialPago) {
        this.fechaInicialPago = fechaInicialPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
     
     
    
}
