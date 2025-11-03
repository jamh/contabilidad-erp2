/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

/**
 *
 * @author Feraz3
 */

import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetenciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetencionesId;
import java.util.List;
public interface ErpFeImpRetencionesDao {
    
     public ErpFeImpRetencionesId save(ErpFeImpRetenciones erpImpRetenciones);
       public List<ErpFeImpRetenciones> findErpImpRetenciones(String compania, int numero);
       public boolean delete(ErpFeImpRetenciones deleteErpImpRetenciones);
       public boolean deletePorFactura(String compania, int numero);
       public boolean update(ErpFeImpRetenciones updateErpImpRetenciones);
    
}
