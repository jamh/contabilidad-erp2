/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencion;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencionId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeConceptoXRetencionDao {
    
    
     public ErpFeConceptoXRetencionId save(ErpFeConceptoXRetencion erpFeConceptoXRetencion);
       public List<ErpFeConceptoXRetencion> FindErpFeConceptoXRetencion();
       public boolean delete(ErpFeConceptoXRetencion deleteErpFeConceptoXRetencion);
       public boolean update(ErpFeConceptoXRetencion updateErpFeConceptoXRetencion);
       public boolean deletePorFactura(String compania, int numero);
        public int getMaxIdConceptoXTraslado(ErpFeConceptoXRetencionId id);
    
}
