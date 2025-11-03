/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
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

@JsonIgnoreProperties(ignoreUnknown = true)

public class AtributosCtas implements  java.io.Serializable{
    
    @JsonProperty("CODAGRUP")
    @Column(name = "CODAGRUP")
    private String codAgrup;
    
    @JsonProperty("NUMCTA")
    @Column(name = "NUMCTA")
    private String numCta;
    
    @JsonProperty("DESC")
    @Column(name = "DESC")
    private String  desc;
    
    @JsonProperty("SUBCTADE")
    @Column(name = "SUBCTADE")
    private String subCtaDe;
    
    @JsonProperty("NIVEL")
    @Column(name = "NIVEL")
    private int nivel;
    
    @JsonProperty("NATUR")
    @Column(name = "NATUR")
    private String natur;
    
    public AtributosCtas(){
    }
    
    public AtributosCtas(String codAgrup, String numCta, String desc, String subCtaDe, int nivel, String natur){
        
        this.codAgrup = codAgrup;
        this.numCta = numCta;
        this.desc = desc;
        this.subCtaDe = subCtaDe;
        this.nivel = nivel;
        this.natur = natur;
    
    
    }

    public String getCodAgrup() {
        return codAgrup;
    }

    public String getNumCta() {
        return numCta;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubCtaDe() {
        return subCtaDe;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNatur() {
        return natur;
    }

    public void setCodAgrup(String codAgrup) {
        this.codAgrup = codAgrup;
    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSubCtaDe(String subCtaDe) {
        this.subCtaDe = subCtaDe;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNatur(String natur) {
        this.natur = natur;
    }
    
    
    
}
