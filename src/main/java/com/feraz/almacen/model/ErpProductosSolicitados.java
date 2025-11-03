/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.almacen.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author FERAZ-14
 */
@Entity
@Table (name="ERP_PRODUCTOS_SOLICITADOS" )
public class ErpProductosSolicitados implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "idSolicitud", column = @Column(name = "ID_SOLICITUD",nullable = false))
    
    })
    
    private ErpProductosSolicitadosId id;
      
      @Column (name = "ID_PRODUCTO")
    private Integer idProducto;
      
      @Column (name = "USUARIO")
    private String usuario;
      
      @Column (name = "CANTIDAD")
    private Integer cantidad;
    
      
      public ErpProductosSolicitados(){
      
      }
      
      public ErpProductosSolicitados(ErpProductosSolicitadosId id){
      
            this.id = id;
          
      }

    public ErpProductosSolicitadosId getId() {
        return id;
    }

    public void setId(ErpProductosSolicitadosId id) {
        this.id = id;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
      
      
     
    
}
