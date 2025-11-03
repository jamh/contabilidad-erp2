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
public class ErpProvDireccionId implements java.io.Serializable{
    
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_PROVEEDOR")
    private String idProveedor;
    
    
    public ErpProvDireccionId(){}
    
    public ErpProvDireccionId(String compania, String idProveedor){
        
        this.compania = compania;
        this.idProveedor = idProveedor;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    
}
