/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeXML;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public interface ErpFeXMLDao {
    
       public boolean saveLista(List<ErpFeXML> listaXml,String compania);       
       public boolean deleteAll(String compania);
       
}
