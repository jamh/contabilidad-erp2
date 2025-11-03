/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.model;

/**
 *
 * @author LENOVO
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpFoliosSolFondosId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
     
     @Column(name = "FOLIO")
    private Integer folio;
    
    @Column(name = "ID_SOLICITUD")
    private Integer idSolicitud;
    
    public ErpFoliosSolFondosId(){
    
    }
    
    public ErpFoliosSolFondosId(String compania, Integer folio,Integer idSolicitud){
        
        this.compania = compania;
        this.folio = folio;
        this.idSolicitud = idSolicitud;
    
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

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
    
}
