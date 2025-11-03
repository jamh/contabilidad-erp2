/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao;

import com.feraz.facturasext.model.ErpExtFacturasMaster;
import com.feraz.facturasext.model.ErpExtFacturasMasterId;

/**
 *
 * @author FERAZ-14
 */
public interface ErpExtFacturasMasterDao {
    
       public ErpExtFacturasMasterId save(ErpExtFacturasMaster erpExtFacturasMaster);
       public boolean delete(ErpExtFacturasMaster erpExtFacturasMaster);
       public boolean update(ErpExtFacturasMaster erpExtFacturasMaster);
       public boolean actualizaEstatus(ErpExtFacturasMaster comp);
    
}
