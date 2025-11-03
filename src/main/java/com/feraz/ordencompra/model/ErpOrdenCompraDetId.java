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
public class ErpOrdenCompraDetId implements java.io.Serializable{
    
     @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID_ORDEN_COMPRA")
    private Integer idOrdenCompra;
    
    @Column (name = "LINEA")
    private Integer linea;
    
    
    public ErpOrdenCompraDetId(){
    
    }
    
    public ErpOrdenCompraDetId(String compania, Integer idOrdenCompra, Integer linea){
        
        this.compania = compania;
        this.idOrdenCompra = idOrdenCompra;
        this.linea = linea;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }
    
    
    
    
}
