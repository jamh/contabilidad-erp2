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
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_CP_CONC_PAGO")
public class ErpCpConcPago implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "concepto", column = @Column(name = "CONCEPTO", nullable = false, length = 10))
    })
      private  ErpCpConcPagoId id;
     



    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "T_DISTRIBUCION")
    private String tDistribucion;
    
    @Column(name = "COLUMNA_PRORRATEO")
    private BigDecimal columnaProrrateo;
    
    @Column(name = "PROG_EJECUTAR")
    private String progEjecutar;
    
    @Column(name = "CUENTA")
    private String cuenta;
    
    public ErpCpConcPago(){

    
    }
    
    public ErpCpConcPago( ErpCpConcPagoId id){
    
       this.id = id;
    
    }

    public ErpCpConcPagoId getId() {
        return id;
    }

    public void setId(ErpCpConcPagoId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String gettDistribucion() {
        return tDistribucion;
    }

    public void settDistribucion(String tDistribucion) {
        this.tDistribucion = tDistribucion;
    }

    public BigDecimal getColumnaProrrateo() {
        return columnaProrrateo;
    }

    public void setColumnaProrrateo(BigDecimal columnaProrrateo) {
        this.columnaProrrateo = columnaProrrateo;
    }

    public String getProgEjecutar() {
        return progEjecutar;
    }

    public void setProgEjecutar(String progEjecutar) {
        this.progEjecutar = progEjecutar;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
}
