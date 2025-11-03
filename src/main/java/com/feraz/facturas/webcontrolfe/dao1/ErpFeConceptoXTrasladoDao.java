/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTraslado;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTrasladoId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeConceptoXTrasladoDao {
    
    public ErpFeConceptoXTrasladoId save(ErpFeConceptoXTraslado erpFeConceptoXTraslado);
       public List<ErpFeConceptoXTraslado> FindErpFeConceptoXTraslado();
       public boolean delete(ErpFeConceptoXTraslado deleteErpFeConceptoXTraslado);
       public boolean update(ErpFeConceptoXTraslado updateErpFeConceptoXTraslado);
       public boolean deletePorFactura(String compania, int numero);
        public int getMaxIdConceptoXTraslado(ErpFeConceptoXTrasladoId id);
    
}
