/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.model;

/**
 *
 * @author vavi
 */
    import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpPedidoMaestroId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "FOLIO")
    private Integer folio;
    
    public ErpPedidoMaestroId(){
    
    }
    
    public ErpPedidoMaestroId(String compania,Integer folio){
        
        this.compania = compania;
        this.folio = folio;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }
    
    
    
    
}
