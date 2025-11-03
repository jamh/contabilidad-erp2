/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.portal.dto;

/**
 *
 * @author 55555
 */
public class ReadXMLDTO {
    private String nameXML;
    private String dirXML;
    private String dirPDF;
    private String namePDF;
    private String orden;
    private String rfc;
    private String email;
    private String idProveedor;

    public ReadXMLDTO(String nameXML, String dirXML, String dirPDF, String orden) {
        this.nameXML = nameXML;
        this.dirXML = dirXML;
        this.dirPDF = dirPDF;
        this.orden = orden;
    }

    public ReadXMLDTO(String nameXML, String dirXML, String dirPDF, String namePDF, String orden, String rfc, String email, String idProveedor) {
        this.nameXML = nameXML;
        this.dirXML = dirXML;
        this.dirPDF = dirPDF;
        this.namePDF = namePDF;
        this.orden = orden;
        this.rfc = rfc;
        this.email = email;
        this.idProveedor = idProveedor;
    }

   
    
    

    public ReadXMLDTO() {
    }

    public String getNameXML() {
        return nameXML;
    }

    public void setNameXML(String nameXML) {
        this.nameXML = nameXML;
    }

    public String getDirXML() {
        return dirXML;
    }

    public void setDirXML(String dirXML) {
        this.dirXML = dirXML;
    }

    public String getDirPDF() {
        return dirPDF;
    }

    public void setDirPDF(String dirPDF) {
        this.dirPDF = dirPDF;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamePDF() {
        return namePDF;
    }

    public void setNamePDF(String namePDF) {
        this.namePDF = namePDF;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    

    @Override
    public String toString() {
        return "ReadXMLDTO{" + "nameXML=" + nameXML + ", dirXML=" + dirXML + ", dirPDF=" + dirPDF + ", namePDF=" + namePDF + ", orden=" + orden + ", rfc=" + rfc + ", email=" + email + '}';
    }
    
    
    
}
