/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCpConcPago;
import com.feraz.cxp.model.ErpCpConcPagoId;

/**
 *
 * @author 55555
 */
public interface ErpCpConcPagoDao {
    
    public ErpCpConcPagoId save(ErpCpConcPago erpCpConcPago);
    public boolean delete(ErpCpConcPago erpCpConcPago);
    
}
