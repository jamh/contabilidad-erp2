/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeNomina;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeNominaDao {
    
    public ErpFeNominaId save(ErpFeNomina erpFeNomina);
       public ErpFeNomina findErpFeNomina(String compania, int numero);
       public boolean delete(ErpFeNomina erpFeNomina);
       public boolean update(ErpFeNomina erpFeNomina);
    
}
