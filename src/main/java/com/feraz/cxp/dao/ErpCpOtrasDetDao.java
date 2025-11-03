/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCpOtrasDet;
import com.feraz.cxp.model.ErpCpOtrasDetId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpCpOtrasDetDao {
    
    public ErpCpOtrasDetId save(ErpCpOtrasDet erpCpOtrasDet);

    public List<ErpCpOtrasDet> findErpCpOtrasDet(ErpCpOtrasDetId erpCpOtrasDet);

    public boolean delete(ErpCpOtrasDet erpCpOtrasDet);

    public boolean update(ErpCpOtrasDet erpCpOtrasDet);
    
    public int getMaxErpCpOtrasDetId(ErpCpOtrasDetId id);
    
}
