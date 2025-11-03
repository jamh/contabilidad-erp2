/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpProvProductos;
import com.feraz.cxp.model.ErpProvProductosId;

/**
 *
 * @author 55555
 */
public interface ErpProvProductosDao {
    
    public ErpProvProductosId save(ErpProvProductos erpProvProductos);

    public boolean delete(ErpProvProductos deleteErpProvProductos);

    public boolean update(ErpProvProductos updateErpProvProductos);
    public int getMaxErpProductos(ErpProvProductosId id);
    
}
