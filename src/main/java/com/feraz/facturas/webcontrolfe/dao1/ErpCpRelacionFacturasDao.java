/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturas;
import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturasId;

/**
 *
 * @author Feraz3
 */
public interface ErpCpRelacionFacturasDao {
    
       public ErpCpRelacionFacturasId save(ErpCpRelacionFacturas erpCpRelacionFacturas);
       public boolean delete(ErpCpRelacionFacturas deleteErpCpRelacionFacturas);
       public int getMaxIdFacturas(ErpCpRelacionFacturasId id);
    
}
