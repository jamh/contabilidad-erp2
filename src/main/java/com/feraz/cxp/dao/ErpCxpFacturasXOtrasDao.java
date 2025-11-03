/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCxpFacturasXOtras;
import com.feraz.cxp.model.ErpCxpFacturasXOtrasId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface ErpCxpFacturasXOtrasDao {
    
        public ErpCxpFacturasXOtrasId save(ErpCxpFacturasXOtras erpCxpFacturasXOtras);

    public List<ErpCxpFacturasXOtras> findErpCxpFacturasXOtras(ErpCxpFacturasXOtrasId erpCpOtras);

    public boolean delete(ErpCxpFacturasXOtras erpCxpFacturasXOtras);

    public boolean update(ErpCxpFacturasXOtras erpCxpFacturasXOtras);
    
    public int getMaxErpCpOtrasId(ErpCxpFacturasXOtrasId id);
    
    public boolean eliminaFactOtras(ErpCxpFacturasXOtras comp);
    
}
