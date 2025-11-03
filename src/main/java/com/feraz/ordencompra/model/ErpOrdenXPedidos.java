/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.model;

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
@Table (name="ERP_ORDENXPEDIDOS" )
public class ErpOrdenXPedidos implements java.io.Serializable{
    
    
     @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "ordenCompra", column = @Column(name = "ORDEN_COMPRA",nullable = false)),
        @AttributeOverride(name = "linea", column = @Column(name = "LINEA",nullable = false)),
        @AttributeOverride(name = "idPedido", column = @Column(name = "ID_PEDIDO",nullable = false))
    
    })
     
      private ErpOrdenXPedidosId id;
     
     @Column (name = "USUARIO")
     private String usuario;
     
     public ErpOrdenXPedidos(){
     
     }
     
     public ErpOrdenXPedidos(ErpOrdenXPedidosId id){
         
         this.id=id;
     
     }

    public ErpOrdenXPedidosId getId() {
        return id;
    }

    public void setId(ErpOrdenXPedidosId id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     
     
}
