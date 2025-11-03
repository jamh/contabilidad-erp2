/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.almacen.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author FERAZ-14
 */

@Embeddable
public class ErpProductosSolicitadosId implements java.io.Serializable{
    
     @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID_SOLICITUD")
    private Integer idSolicitud;
    
    
    public ErpProductosSolicitadosId(){
    
    }
    
    public ErpProductosSolicitadosId(String compania, Integer idSolicitud){
        
        this.compania = compania;
        this.idSolicitud = idSolicitud;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
    
}
