/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author vavi
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

@Entity
@Table (name="ERP_PROVISION_DET" )
public class ErpProvisionDet implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID",nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC",nullable = false))
    
    })
    
    private ErpProvisionDetId id;
    
    
    @Column (name = "ID_DOCUMENTO")
    private int idDocumento;
    
    @Column (name = "IMPORTE_DOCUMENTO")
    private BigDecimal importeDocumento;
    
    @Column (name = "DESCRIPCION")
    private String descripcion;
    
    @Column (name = "ORIGEN")
    private String origen;
    
    @Column (name = "FECHA_CANCELACION")
    private Date fechaCancelacion;
    
    
    
    public ErpProvisionDet(){
    
    
    }
    
    public ErpProvisionDet(ErpProvisionDetId id){
    
        
        this.id = id;
    
    }

    public ErpProvisionDetId getId() {
        return id;
    }

    public void setId(ErpProvisionDetId id) {
        this.id = id;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public BigDecimal getImporteDocumento() {
        return importeDocumento;
    }

    public void setImporteDocumento(BigDecimal importeDocumento) {
        this.importeDocumento = importeDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }
    
    
    
}
