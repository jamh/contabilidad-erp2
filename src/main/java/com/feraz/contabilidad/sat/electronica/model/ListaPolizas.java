/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrador
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XML_POLIZAS")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({ErpSatPlztransaccion.class})
//@XmlAccessorType(XmlAccessType.PROPERTY)
public class ListaPolizas {
    @XmlElement(name = "Polizas")
    private ErpSatPolizas erpSatPolizas;
    @XmlElement(name = "Poliza")
    private List<ErpSatPoliza> lista;

    public ListaPolizas() {
    }

    public ListaPolizas(List<ErpSatPoliza> lista) {
        this.lista = lista;
    }

    public ErpSatPolizas getErpSatPolizas() {
        return erpSatPolizas;
    }

    public void setErpSatPolizas(ErpSatPolizas erpSatPolizas) {
        this.erpSatPolizas = erpSatPolizas;
    }

//    @javax.xml.bind.annotation.XmlElement(name = "POLIZAS")  
    public List<ErpSatPoliza> getLista() {
        return lista;
    }

}
