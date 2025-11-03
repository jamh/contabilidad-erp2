/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpDetImpuestosBr;
import com.feraz.cxp.model.ErpDetImpuestosBrId;

/**
 *
 * @author FERAZ-14
 */
public interface ErpDetImpuestosBrDao {
    
    public ErpDetImpuestosBrId save(ErpDetImpuestosBr erpDetImpuestosBr);
    public boolean update(ErpDetImpuestosBr erpDetImpuestosBr);
    public boolean delete(ErpDetImpuestosBr erpDetImpuestosBr);
    
}
