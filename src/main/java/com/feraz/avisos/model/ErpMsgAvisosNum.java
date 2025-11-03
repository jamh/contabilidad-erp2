/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_MSG_AVISOS_NUM")
public class ErpMsgAvisosNum implements java.io.Serializable{
    
       @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "usuario", column = @Column(name = "USUARIO", nullable = false, length = 50)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
    
     @JsonProperty("ID2")
    private ErpMsgAvisosNumId id;
    
       public ErpMsgAvisosNum(){
       }
       
       public ErpMsgAvisosNum(ErpMsgAvisosNumId id){
             this.id = id;
       }

    public ErpMsgAvisosNumId getId() {
        return id;
    }

    public void setId(ErpMsgAvisosNumId id) {
        this.id = id;
    }
     
       
    
}
