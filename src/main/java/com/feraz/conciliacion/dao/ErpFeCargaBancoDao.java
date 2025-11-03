/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dao;

import com.feraz.conciliacion.model.ErpFeCargaBanco;
import com.feraz.conciliacion.model.ErpFeCargaBancoId;

/**
 *
 * @author vavi
 */
public interface ErpFeCargaBancoDao {
    
    
     public ErpFeCargaBancoId save(ErpFeCargaBanco erpFeCargaBanco);
     public int getMaxIdErpFeCargaBanco(ErpFeCargaBancoId id);
    
}
