/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoComplId;

/**
 * @author Armando
 */
public interface ErpFeImpuestoComplDao {
    public ErpFeImpuestoComplId save(ErpFeImpuestoCompl erpFeImpuestoCompl);
       public ErpFeImpuestoCompl findErpFePercepciones(String compania, int numero);
       public boolean delete(ErpFeImpuestoCompl erpFeImpuestoCompl);
       public boolean update(ErpFeImpuestoCompl erpFeImpuestoCompl);
    
}
