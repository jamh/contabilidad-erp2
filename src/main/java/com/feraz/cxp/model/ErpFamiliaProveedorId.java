/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

import javax.persistence.Column;

/**
 *
 * @author vavi
 */

import javax.persistence.Embeddable;


@Embeddable
public class ErpFamiliaProveedorId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_PROVEEDOR")
    private String idProveedor;
    
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    
    public ErpFamiliaProveedorId(){
    
    }
    
    
    public ErpFamiliaProveedorId(String compania,String idProveedor,Integer idFamilia){
        
        this.compania = compania;
        this.idProveedor = idProveedor;
        this.idFamilia = idFamilia;
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

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }
    
    
    
}
