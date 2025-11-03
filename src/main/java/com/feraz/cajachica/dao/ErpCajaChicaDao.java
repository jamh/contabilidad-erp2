/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao;

import com.feraz.cajachica.model.ErpCajaChica;
import com.feraz.cajachica.model.ErpCajaChicaId;

/**
 *
 * @author vavi
 */
public interface ErpCajaChicaDao {
    
     public ErpCajaChicaId save(ErpCajaChica erpCajaChica);


    public boolean delete(ErpCajaChica erpCajaChica);

    public boolean update(ErpCajaChica erpCajaChica);
    
    public int getMaxErpCajaChicaId(ErpCajaChicaId id);
    
}
