
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptorId;
import java.util.List;


/**
 *
 * @author Feraz3
 */
public interface ErpFeReceptorDao {
    
     public ErpFeReceptorId save(ErpFeReceptor erpFeReceptor);
       public ErpFeReceptor findErpFeReceptor( String compania, int numero);
       public boolean delete(ErpFeReceptor deleteErpFeReceptor);
       public boolean update(ErpFeReceptor updateErpFeReceptor);
    
}
