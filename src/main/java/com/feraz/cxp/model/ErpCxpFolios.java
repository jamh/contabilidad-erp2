/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author Feraz3
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="ERP_CXP_FOLIOS" )
public class ErpCxpFolios implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
       
        @AttributeOverride(name = "compania", column = @Column(name="COMPANIA",nullable = false,length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID",nullable = false))
    
    })
    
    private ErpCxpFoliosId id;
    
    
    @Column (name = "FOLIO")
    private int folio;
    

    
    @Column (name = "USUARIO" )
    private String usuario;
    
    @Column (name = "OPERACION")
    private String operacion;
    
    @Column (name = "NUMERO_FE")
    private int numeroFe;
    
    @Column (name = "ORIGEN")
    private String origen;
    

    public ErpCxpFolios(){
    
    }
    
    public ErpCxpFolios(ErpCxpFoliosId id){

        this.id = id;
        
    }

    public ErpCxpFoliosId getId() {
        return id;
    }

    public void setId(ErpCxpFoliosId id) {
        this.id = id;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(int numeroFe) {
        this.numeroFe = numeroFe;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    
    
}
