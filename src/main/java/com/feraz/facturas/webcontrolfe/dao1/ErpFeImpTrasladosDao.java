/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeImpTraslados;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTrasladosId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpFeImpTrasladosDao {
    
     public ErpFeImpTrasladosId save(ErpFeImpTraslados erpImpTraslados);
       public List<ErpFeImpTraslados> findErpImpTraslados(String compania, int numero);
       public boolean delete(ErpFeImpTraslados deleteErpImpTraslados);
       public boolean deletePorFactura(String compania, int numero);
       public boolean update(ErpFeImpTraslados updateErpImpTraslados);
    
}
