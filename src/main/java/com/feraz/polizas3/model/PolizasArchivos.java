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
@Table(name = "POLIZAS_ARCHIVOS")
public class PolizasArchivos implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "tipoPoliza", column = @Column(name = "TIPO_POLIZA", nullable = false, length = 5)),
        @AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false, length = 10)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
    })
    
    private PolizasArchivosId id;
    
    @Column(name = "PATH")
    private String path;
    
    @Column(name = "URL")
    private String url;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
   
     
    public PolizasArchivos(){
    
    }
    
    public PolizasArchivos(PolizasArchivosId id){
        
        this.id = id;
        
    }

    public PolizasArchivosId getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(PolizasArchivosId id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    
}
