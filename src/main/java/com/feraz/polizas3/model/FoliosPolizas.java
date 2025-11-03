/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */

import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FOLIOS_POLIZAS")


public class FoliosPolizas implements java.io.Serializable {
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "ano", column = @Column(name = "ANO", nullable = false)),
        @AttributeOverride(name = "mes", column = @Column(name = "MES", nullable = false)),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5))
    })
    
    private FoliosPolizasId id;
     
    @Column (name = "FOLIO")
    private int folio;
    
    public FoliosPolizas(){
    }
    
    public FoliosPolizas(FoliosPolizasId id){
    }

    public FoliosPolizasId getId() {
        return id;
    }

    public int getFolio() {
        return folio;
    }

    public void setId(FoliosPolizasId id) {
        this.id = id;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }
   
    
    
}
