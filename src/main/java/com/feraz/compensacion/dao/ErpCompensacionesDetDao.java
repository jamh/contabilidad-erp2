/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.dao;

import com.feraz.compensacion.model.ErpCompensacionesDet;
import com.feraz.compensacion.model.ErpCompensacionesDetId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpCompensacionesDetDao {
    
     public ErpCompensacionesDetId save(ErpCompensacionesDet erpCompensacionesDet);

    public List<ErpCompensacionesDet> findErpCompensacionesDet();

    public boolean delete(ErpCompensacionesDet deleteErpCompensacionesDet);

    public boolean update(ErpCompensacionesDet updateErpCompensacionesDet);
    
}
