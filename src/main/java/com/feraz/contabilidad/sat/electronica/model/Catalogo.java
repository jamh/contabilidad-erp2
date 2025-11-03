/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "Catalogo")
public class Catalogo implements java.io.Serializable {
    
//    @EmbeddedId
//    @AttributeOverrides({
//        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
//        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false, length = 25))
//    })
//    
//    @JsonProperty("ID")
    @XmlElement
   public  CatalogoId id;
    
//    @JsonProperty("DESCRIPCION")
    @XmlElement
  public   String descripcion;
    
    @XmlElement
  public   Atributos atributos;
    
    public Catalogo(){
    
    }
    
    public Catalogo(CatalogoId id){
        
        this.id =  id;
        
    }
    
    

//    public CatalogoId getId() {
//        return id;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public Atributos getAtributos() {
//        return atributos;
//    }
//
//    public void setId(CatalogoId id) {
//        this.id = id;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public void setAtributos(Atributos atributos) {
//        this.atributos = atributos;
//    }

//    public CatalogoId getId() {
//        return id;
//    }
//
//    public void setId(CatalogoId id) {
//        this.id = id;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public Atributos getAtributos() {
//        return atributos;
//    }
//
//    public void setAtributos(Atributos atributos) {
//        this.atributos = atributos;
//    }
//    
    
    
    
    
}
