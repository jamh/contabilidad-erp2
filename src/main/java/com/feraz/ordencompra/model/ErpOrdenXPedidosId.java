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
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpOrdenXPedidosId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ORDEN_COMPRA")
    private Integer ordenCompra;
    
    @Column (name = "LINEA")
    private Integer linea;
    
    @Column (name = "ID_PEDIDO")
    private Integer idPedido;
    
    public ErpOrdenXPedidosId(){
    
    
    }
    
    public ErpOrdenXPedidosId(String compania, Integer ordenCompra, Integer linea,Integer idPedido){
        
        this.compania = compania;
        this.ordenCompra=ordenCompra;
        this.linea = linea;
        this.idPedido = idPedido;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(Integer ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    
    
    
}
