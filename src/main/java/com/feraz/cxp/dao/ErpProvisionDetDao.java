/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpProvisionDet;
import com.feraz.cxp.model.ErpProvisionDetId;

/**
 *
 * @author vavi
 */
public interface ErpProvisionDetDao {
    
    
        public ErpProvisionDetId save(ErpProvisionDet erpProvisionDet);

    

    public boolean delete(ErpProvisionDet erpProvisionDet);

    public boolean eliminaProvisionDoc(ErpProvisionDet comp);
    
    public int getMaxerpProvisionDetId(ErpProvisionDetId id);
    
    
    
}
