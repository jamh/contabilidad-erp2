/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.avisos.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 55555
 */
@Embeddable
public class GentusId implements java.io.Serializable{
    
    @Column (name = "US_GRUPO")
    private String usGrupo;
    
    @Column (name = "US_CVESIS")
    private String usCvesis;
    
    @Column (name = "US_USUARIO")
    private String usUsuario;
    

    private GentusId (String usGrupo, String usCvesis, String usUsuario){
        
        this.usCvesis = usCvesis;
        this.usGrupo = usGrupo;
        this.usUsuario = usGrupo;
    
    }

    public GentusId() {
    }

    public String getUsGrupo() {
        return usGrupo;
    }

    public void setUsGrupo(String usGrupo) {
        this.usGrupo = usGrupo;
    }

    public String getUsCvesis() {
        return usCvesis;
    }

    public void setUsCvesis(String usCvesis) {
        this.usCvesis = usCvesis;
    }

    public String getUsUsuario() {
        return usUsuario;
    }

    public void setUsUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }
    
    
    
}
