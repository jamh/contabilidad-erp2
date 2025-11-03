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
import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_POLIZASVSFACTURAS_AUT_ERR" )
public class ErpPolizasVSFacturasAutErr implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "folio", column = @Column(name = "FOLIO",nullable = false,length = 20)),
        @AttributeOverride(name = "tipo", column = @Column(name = "TIPO",nullable = false,length = 2))
    
    })
    
    private ErpPolizasVSFacturasAutErrId id;
    
    
    @Column (name = "ID_FACTURA")
    private int idFactura;
    
    @Column (name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column (name = "DESCRIPCION")
    private String descripcion;
    
    @Column (name = "FECHA_CAP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCap;
    
     
     @Column (name = "ENVIO")
    private String envio;
     
     @Column (name = "NOMBRE")
    private String nombre;
     
     @Column (name = "CORREO")
    private String correo;
     
     
     public ErpPolizasVSFacturasAutErr(){
     
     }
     
     public ErpPolizasVSFacturasAutErr(ErpPolizasVSFacturasAutErrId id){
     
         this.id = id;
         
     }

    public ErpPolizasVSFacturasAutErrId getId() {
        return id;
    }

    public void setId(ErpPolizasVSFacturasAutErrId id) {
        this.id = id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCap() {
        return fechaCap;
    }

    public void setFechaCap(Date fechaCap) {
        this.fechaCap = fechaCap;
    }

 

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
     
     
     
    
}
