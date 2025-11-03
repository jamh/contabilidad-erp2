/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
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
import javax.persistence.TemporalType;

@Entity
@Table(name = "LOG_POLIZAS")
public class LogPolizas implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "usuario", column = @Column(name = "USUARIO", nullable = false, length = 40 )),
        @AttributeOverride(name = "numPoliza", column = @Column(name = "NUM_POLIZA", nullable = false, length = 20)),
        @AttributeOverride(name = "fechaPoliza", column = @Column(name = "FECHA_POLIZA", nullable = false)),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5))
        
    })
    
    private LogPolizasId id;
    
    @Column(name = "FECHA")
      @Temporal(TemporalType.TIMESTAMP) 
    private Date fecha;
    
    @Column(name = "ACCION")
    private String accion;
    
    @Column(name = "COMPANIA")
    private String compania;
    
    
     public LogPolizas(){
    
    }
    
    public LogPolizas(LogPolizasId id){
        
        this.id = id;
        
    }

    public LogPolizasId getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setId(LogPolizasId id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }
    
    
    
}
