/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeEmisorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisor;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpFeEmisorDao {
    
       public ErpFeEmisorId save(ErpFeEmisor erpEmisor);
       public ErpFeEmisor findErpEmisor(String compania, String numero);
       public boolean delete(ErpFeEmisor deleteErpEmisor);
       public boolean update(ErpFeEmisor updateErpEmisor);
    
}
