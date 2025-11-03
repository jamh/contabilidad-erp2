
package com.feraz.cfdi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_SAT_LEER_CFDI")
public class ErpSatLeerCfdi implements java.io.Serializable{


    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        
    })    
    private ErpSatLeerCfdiId id;
    
    
    
    
    
    @Column(name = "RFC")
    private String rfc;
     
     
    @Column(name = "PASSWORD")
    private String password;
    
     @Column(name = "USUARIO")
    private String usuario;
      
       public ErpSatLeerCfdi(){
    
       }
    
    public ErpSatLeerCfdi(ErpSatLeerCfdiId id){
        
        this.id = id;
        
    }

    public ErpSatLeerCfdiId getId() {
        return id;
    }

    public void setId(ErpSatLeerCfdiId id) {
        this.id = id;
    }

   

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
      
     
    
}
