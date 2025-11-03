/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;


@Embeddable
public class ErpSeguiDocumentosId implements java.io.Serializable{
    
     
    @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ORIGEN")
    private String origen;
    
    @Column(name = "T_DOC")
    private String tDoc;
    
    @Column(name = "SERIE")
    private String serie;
    
     @Column(name = "NUMERO_FE")
     private int numFe;
    
    
    @Column(name = "SEC")
    private int sec;
    
    public ErpSeguiDocumentosId(){
    
    }
    
    public ErpSeguiDocumentosId(String compania, String origen, String tDoc, String serie, int numFe, int sec){
        
        this.compania = compania;
        this.origen = origen;
        this.tDoc = tDoc;
        this.serie = serie;
        this.numFe = numFe;
        this.sec = sec;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String gettDoc() {
        return tDoc;
    }

    public void settDoc(String tDoc) {
        this.tDoc = tDoc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }



    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getNumFe() {
        return numFe;
    }

    public void setNumFe(int numFe) {
        this.numFe = numFe;
    }
    
    
    
}
