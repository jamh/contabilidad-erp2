/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao;

import com.feraz.cajachica.model.ErpCajaGastos;
import com.feraz.cajachica.model.ErpCajaGastosId;

/**
 *
 * @author LENOVO
 */
public interface ErpCajaGastosDao {
    
    public ErpCajaGastosId save(ErpCajaGastos erpCajaGastos);


    public boolean delete(ErpCajaGastos erpCajaGastos);

    public boolean update(ErpCajaGastos erpCajaGastos);
    
    public int getMaxErpCajaGastosId(ErpCajaGastosId id);
    
}
