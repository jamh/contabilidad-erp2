/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao;

import com.feraz.cajachica.model.ErpCajaChicaDet;
import com.feraz.cajachica.model.ErpCajaChicaDetId;

/**
 *
 * @author vavi
 */
public interface ErpCajaChicaDetDao {
    
    
     public ErpCajaChicaDetId save(ErpCajaChicaDet erpCajaChicaDet);


    public boolean delete(ErpCajaChicaDet erpCajaChicaDet);

    public boolean update(ErpCajaChicaDet erpCajaChicaDet);
    
    public int getMaxErpCajaChicaDetId(ErpCajaChicaDetId id);
    public boolean actualizaEstatusCaja(ErpCajaChicaDet erpCajaChicaDet);
    
    
}
