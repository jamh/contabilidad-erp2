/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpNotasExt;
import com.feraz.cxp.model.ErpNotasExtId;

/**
 *
 * @author vavi
 */
public interface ErpNotasExtDao {
    
    public ErpNotasExtId save(ErpNotasExt erpNotasExt);
      
      public boolean update (ErpNotasExt erpNotasExt);
      public boolean eliminaNotas(ErpNotasExt erpNotasExt);
    
}
