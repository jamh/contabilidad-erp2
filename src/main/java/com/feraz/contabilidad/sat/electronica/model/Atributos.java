/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "Atributos")
public class Atributos implements java.io.Serializable{
    
    //@EmbeddedId
    //@AttributeOverrides({
        
    
    //})
    
    @JsonProperty("VERSION")
//    @Column(name = "VERSION")
     @XmlElement
    public String version;
//    
    @JsonProperty("RFC")
//    @Column(name = "RFC")
    @XmlElement
    public String rfc;
    
   @JsonProperty("TOTALCTAS")
//    @Column(name = "TOTALCTAS")
    @XmlElement
    public int totalCtas;
    
   @JsonProperty("MES")
//    @Column(name = "MES")
    @XmlElement
    public String mes;
    
//    @JsonProperty("ANO")
//    @Column(name = "ANO")
    @XmlElement
    public int ano;
    
    
    public Atributos(){
    
    }
    
    public Atributos(String version, String rfc, int totalCtas, String mes, int ano){
        
        this.version = version;
        this.rfc = rfc;
        this.totalCtas = totalCtas;
        this.mes = mes;
        this.ano = ano ;
    
    }

//    public String getVersion() {
//        return version;
//    }
//
//    public String getRfc() {
//        return rfc;
//    }
//
//    public int getTotalCtas() {
//        return totalCtas;
//    }
//
//    public String getMes() {
//        return mes;
//    }
//
//    public int getAno() {
//        return ano;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    public void setRfc(String rfc) {
//        this.rfc = rfc;
//    }
//
//    public void setTotalCtas(int totalCtas) {
//        this.totalCtas = totalCtas;
//    }
//
//    public void setMes(String mes) {
//        this.mes = mes;
//    }
//
//    public void setAno(int ano) {
//        this.ano = ano;
//    }
//    
    
    
}
