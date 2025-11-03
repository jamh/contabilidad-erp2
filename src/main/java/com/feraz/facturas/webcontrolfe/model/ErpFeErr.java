/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_ERR")
public class ErpFeErr implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false, length = 15)),
        @AttributeOverride(name = "xml", column = @Column(name = "XML", nullable = false, length = 300))
    })    
    private ErpFeErrId id;
    
     @Column (name = "MSG")
    private String msg;
    
//    @Column (name = "XML")
//    private String xml;
//    
       public ErpFeErr(){
       }
    
        public ErpFeErr(ErpFeErrId id){

            this.id = id;

        }

    public ErpFeErrId getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

//    public String getXml() {
//        return xml;
//    }

    public void setId(ErpFeErrId id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public void setXml(String xml) {
//        this.xml = xml;
//    }
    
   
    
}
