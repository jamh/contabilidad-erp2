/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpMsgAvisosNumId implements java.io.Serializable{
    
    @JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;
    
    @JsonProperty ("USUARIO")
    @Column (name = "USUARIO")
    private String usuario;
    
    @JsonProperty("SEC")
    @Column (name = "SEC")
    private int sec;
    
    public ErpMsgAvisosNumId(){
    }
    
    public ErpMsgAvisosNumId(String compania, String usuario, int sec){
        
        this.compania = compania;
        this.usuario = usuario;
        this.sec = sec;
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
    
    
    
    
}

