/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.dao;

import com.feraz.intercos.model.ErpWsIntercos;
import com.feraz.intercos.model.ErpWsIntercosId;

/**
 *
 * @author vavi
 */
public interface ErpWsIntercosDao {

    
       public ErpWsIntercosId save(ErpWsIntercos erpWsIntercos);
       public ErpWsIntercos findErpIntercos(String compania, String uuid);
       public boolean delete(ErpWsIntercos deleteErpEmisor);
       public boolean update(ErpWsIntercos updateErpEmisor);
    

    
}
