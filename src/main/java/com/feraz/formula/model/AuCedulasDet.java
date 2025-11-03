/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.model;

/**
 *
 * @author 55555
 */

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "AU_CEDULAS_DET")
public class AuCedulasDet implements java.io.Serializable{
    
     
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "cedula", column = @Column(name = "CEDULA", nullable = false, length = 10)),
        @AttributeOverride(name = "renglon", column = @Column(name = "RENGLON", nullable = false)),
        @AttributeOverride(name = "columna", column = @Column(name = "COLUMNA", nullable = false))

            

    })


    private AuCedulasDetId id;
    
    
    @Column(name = "CAMPO")
    private String campo;
    
    
    public AuCedulasDet (){
    }
    
    public AuCedulasDet (AuCedulasDetId id){
        
        this.id = id;
    
    }

    public AuCedulasDetId getId() {
        return id;
    }

    public void setId(AuCedulasDetId id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
    
    
}
