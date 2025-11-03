/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptosId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpFeConceptosDao {
    
     public ErpFeConceptosId save(ErpFeConceptos erpFeConceptos);
       public List<ErpFeConceptos> FindErpFeConceptos();
       public boolean delete(ErpFeConceptos deleteErpFeConceptos);
       public boolean update(ErpFeConceptos updateErpFeConceptos);
       public boolean deletePorFactura(String compania, int numero);
        public int getMaxIdConceptos(ErpFeConceptosId id);
        public boolean updateConceptos(ErpFeConceptosId id,String idS);
        public List<ErpFeConceptos> findErpFeConceptos2(String compania,String numero);
}





