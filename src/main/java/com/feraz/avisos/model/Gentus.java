/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.avisos.model;

/**
 *
 * @author 55555
 */

import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "GENTUS")
public class Gentus implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "usGrupo", column = @Column(name = "US_GRUPO", nullable = false, length = 40)),
        @AttributeOverride(name = "usCvesis", column = @Column(name = "US_CVESIS", nullable = false, length = 6)),
        @AttributeOverride(name = "usUsuario", column = @Column(name = "US_USUARIO", nullable = false,  length = 40))
    })
    

    private GentusId id;
    
   
    @Column(name = "US_NOMBRE")
    private String usNombre;
    
    @Column (name = "US_PASSWD")
    private String usPasswd;
    
    @Column (name = "US_FECHA_BAJA")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date usFechaBaja;
    
    @Column (name = "US_FECHA_ALTA")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date usFechaAlta;
    
    @Column (name = "CORREO")
    private String correo;
    
    public Gentus (){
    
    }
    
    public Gentus(GentusId id){
        
        this.id = id;
    
    }

    public GentusId getId() {
        return id;
    }

    public void setId(GentusId id) {
        this.id = id;
    }

    public String getUsNombre() {
        return usNombre;
    }

    public void setUsNombre(String usNombre) {
        this.usNombre = usNombre;
    }

    public String getUsPasswd() {
        return usPasswd;
    }

    public void setUsPasswd(String usPasswd) {
        this.usPasswd = usPasswd;
    }

    public Date getUsFechaBaja() {
        return usFechaBaja;
    }

    public void setUsFechaBaja(Date usFechaBaja) {
        this.usFechaBaja = usFechaBaja;
    }

    public Date getUsFechaAlta() {
        return usFechaAlta;
    }

    public void setUsFechaAlta(Date usFechaAlta) {
        this.usFechaAlta = usFechaAlta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
