/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocalesId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeImpLocalesDao {
    
     public ErpFeImpLocalesId save(ErpFeImpLocales erpFeImpLocales);
       public List<ErpFeImpLocales> findErpFeImpLocales(String compania, int numero);
       public boolean delete(ErpFeImpLocales deleteErpFeImpLocales);
       public boolean update(ErpFeImpLocales updateErpFeImpLocales);
    
}
