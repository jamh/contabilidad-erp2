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
//  
//@XmlRootElement(name = "ListaCatalogos") 

@XmlRootElement(name = "XML_CATALOGOS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaCatalogos {

    @XmlElement(name = "Catalogo")
    private ErpSatCatalogo erpSatCatalogo;
    @XmlElement(name = "catalogocuentas")
    private List<ErpSatCatalogoCtas> lista;
     //  @XmlElement(name = "catalogocuentas")  

    public ListaCatalogos() {
    }

    public ListaCatalogos(List<ErpSatCatalogoCtas> lista) {
        this.lista = lista;
    }

    public ErpSatCatalogo getErpSatCatalogo() {
        return erpSatCatalogo;
    }

    public void setErpSatCatalogo(ErpSatCatalogo erpSatCatalogo) {
        this.erpSatCatalogo = erpSatCatalogo;
    }

    public List<ErpSatCatalogoCtas> getLista() {
        return lista;
    }

}
