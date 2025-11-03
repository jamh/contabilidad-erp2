/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptorId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeNominaReceptorDao {
    
    public ErpFeNominaReceptorId save(ErpFeNominaReceptor erpFeNominaReceptor);
       public ErpFeNominaReceptor findErpFeNominaReceptor(String compania, int numero);
       public boolean delete(ErpFeNominaReceptor erpFeNominaReceptor);
       public boolean update(ErpFeNominaReceptor erpFeNominaReceptor);
    
}
