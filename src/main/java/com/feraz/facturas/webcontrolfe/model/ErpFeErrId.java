/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpFeErrId implements java.io.Serializable{
    
      @JsonProperty("COMPANIA")
    @Column (name = "COMPANIA")
    private String compania;
   
    @JsonProperty("ID")
    @Column (name = "ID")
    private String id;
    
     @JsonProperty("XML")
    @Column (name = "XML")
    private String xml;
    
    public ErpFeErrId(){
    
    }
    
    public ErpFeErrId(String compania, String id, String xml){
        
        this.compania = compania;
        this.id = id;
        this.xml = xml;
    
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompania() {
        return compania;
    }

    public String getId() {
        return id;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    
    
    
}
