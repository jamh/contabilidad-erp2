/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.model;

/**
 *
 * @author vavi
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
@Table (name="ERP_FOLIOS_PAGO_VIATICOS" )
public class ErpFoliosPagoViaticos implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "folio", column = @Column(name = "FOLIO",nullable = false)),
        @AttributeOverride(name = "comision", column = @Column(name = "COMISION",nullable = false))
    
    })
    
    private ErpFoliosPagoViaticosId id;
    
     
      
    @Column (name = "TIPO_PAGO")
    private String tipoPago;
    
    
    @Column (name = "USUARIO")
    private String usuario;
    
    
    @Column (name = "USUARIO_AUTORIZO")
    private String usuarioAutorizo;
    
    @Column (name = "FECHA_AUTORIZACION")
    private Date fechaAutorizacion;
    
    @Column (name = "ESTATUS")
    private String estatus;
    
    public ErpFoliosPagoViaticos(){
    
    }
    
    public ErpFoliosPagoViaticos(ErpFoliosPagoViaticosId id){
        
        this.id  = id;
            
    }

    public ErpFoliosPagoViaticosId getId() {
        return id;
    }

    public void setId(ErpFoliosPagoViaticosId id) {
        this.id = id;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioAutorizo() {
        return usuarioAutorizo;
    }

    public void setUsuarioAutorizo(String usuarioAutorizo) {
        this.usuarioAutorizo = usuarioAutorizo;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
    
}
