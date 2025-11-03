/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.model;

/**
 *
 * @author 55555
 */

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OTRAS_ARCHIVOS")

public class OtrasArchivos implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
    
    private OtrasArchivosId id;
    
    @Column(name = "PATH")
    private String path;
    
    @Column(name = "URL")
    private String url;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "TIPO")
    private String tipo;
   
     
    public OtrasArchivos(){
    
    }
    
    public OtrasArchivos(OtrasArchivosId id){
        
        this.id = id;
        
    }

    public OtrasArchivosId getId() {
        return id;
    }

    public void setId(OtrasArchivosId id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
