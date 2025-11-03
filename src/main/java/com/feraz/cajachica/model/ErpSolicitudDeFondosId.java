/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

/**
 *
 * @author vavi
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpSolicitudDeFondosId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_SOLICITUD")
    private Integer idSolicitud;
    
    public ErpSolicitudDeFondosId(){
    
    }
    
    public ErpSolicitudDeFondosId(String compania, Integer idSolicitud){
    
        this.compania=compania;
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
