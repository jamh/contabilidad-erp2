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
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpProvProductosId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "PROVEEDOR")
    private String proveedor;
    
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    
    
    public ErpProvProductosId(){}
    
    public ErpProvProductosId(String compania, String proveedor, Integer idProducto){
    
        this.compania = compania;
        this.proveedor = proveedor;
        this.idProducto = idProducto;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    
    
}
