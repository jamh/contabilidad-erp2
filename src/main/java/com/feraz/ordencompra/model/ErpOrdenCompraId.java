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
public class ErpOrdenCompraId implements java.io.Serializable{
    
      @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID")
    private Integer id;
    
    
    public ErpOrdenCompraId(){
    
    }
    
    public ErpOrdenCompraId(String compania, Integer id){
        
        this.compania = compania;
        this.id = id;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
}
