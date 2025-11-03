/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpProvDireccion;
import com.feraz.cxp.model.ErpProvDireccionId;

/**
 *
 * @author 55555
 */
public interface ErpProvDireccionDao {
    
    public ErpProvDireccionId save(ErpProvDireccion erpProvDireccion);

    public boolean delete(ErpProvDireccion deleteErpProvDireccion);

    public boolean update(ErpProvDireccion updateErpProvDireccion);

}
