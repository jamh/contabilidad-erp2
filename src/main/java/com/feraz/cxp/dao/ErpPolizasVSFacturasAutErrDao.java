/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpPolizasVSFacturasAutErr;
import com.feraz.cxp.model.ErpPolizasVSFacturasAutErrId;

/**
 *
 * @author 55555
 */
public interface ErpPolizasVSFacturasAutErrDao {
    
     public ErpPolizasVSFacturasAutErrId save(ErpPolizasVSFacturasAutErr erpPolizasVSFacturasAutErr);
     public boolean update (ErpPolizasVSFacturasAutErr erpPolizasVSFacturasAutErr);

    
}
