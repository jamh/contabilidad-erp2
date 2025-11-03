/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisorId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeNominaEmisorDao {
    
    
     public ErpFeNominaEmisorId save(ErpFeNominaEmisor erpFeNominaEmisor);
       public ErpFeNominaEmisor findErpFeNominaEmisor(String compania, int numero);
       public boolean delete(ErpFeNominaEmisor erpFeNominaEmisor);
       public boolean update(ErpFeNominaEmisor erpFeNominaEmisor);
    
}
