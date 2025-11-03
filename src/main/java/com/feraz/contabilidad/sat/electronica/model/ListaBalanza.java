/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.electronica.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrador
 */
import javax.xml.bind.annotation.XmlRootElement;  
  
@XmlRootElement(name = "XML_BALANZA")  
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaBalanza {
      @XmlElement(name = "Balanza")  
    private List<ErpSatBalanza> lista;

    public ListaBalanza() {
    }


    public ListaBalanza(List<ErpSatBalanza> lista) {
        this.lista = lista;
    }

  
    
    
//    @javax.xml.bind.annotation.XmlElement(name = "BALANZAS")  
    public List<ErpSatBalanza> getLista() {
        return lista;
    }

   
    
    
}
