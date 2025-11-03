/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpFamiliaProveedor;
import com.feraz.cxp.model.ErpFamiliaProveedorId;

/**
 *
 * @author vavi
 */
public interface ErpFamiliaProveedorDao {
    
    public ErpFamiliaProveedorId save(ErpFamiliaProveedor erpFamiliaProveedor);

    public boolean delete(ErpFamiliaProveedor deleteErpFamiliaProveedor);
    
}
