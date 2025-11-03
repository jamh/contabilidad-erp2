/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeErr;
import com.feraz.facturas.webcontrolfe.model.ErpFeErrId;

/**
 *
 * @author Feraz3
 */
public interface ErpFeErrDao {
    
    public ErpFeErrId save(ErpFeErr erpFeErr);
    public boolean update(ErpFeErr updateErpFeErr);
    
}
