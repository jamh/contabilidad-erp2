/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao;

import com.feraz.facturasext.model.ErpExtFacturasId;
import com.feraz.facturasext.model.ErpExtFacturas;

/**
 *
 * @author Ing. David Ortiz García
 */
public interface ErpExtFacturasDao {
    
       public ErpExtFacturasId save(ErpExtFacturas erpExtFacturas);
       public boolean delete(ErpExtFacturas erpExtFacturas);
       public boolean update(ErpExtFacturas erpExtFacturas);
       public int getMaxErpExtFacturas(ErpExtFacturasId id);
    
}
