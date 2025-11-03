/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFePagos;
import com.feraz.facturas.webcontrolfe.model.ErpFePagosId;

/**
 *
 * @author vavi
 */
public interface ErpFePagosDao {
    
    
    public ErpFePagosId save(ErpFePagos erpFePagos);
       public ErpFePagos findErpFePercepciones(String compania, int numero);
       public boolean delete(ErpFePagos erpFePagos);
       public boolean update(ErpFePagos erpFePagos);
       public int getMaxIdErpFePagos(ErpFePagosId id);
    
    
}
