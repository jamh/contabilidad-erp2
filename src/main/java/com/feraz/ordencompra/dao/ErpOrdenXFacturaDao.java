/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao;

import com.feraz.ordencompra.model.ErpOrdenXFactura;
import com.feraz.ordencompra.model.ErpOrdenXFacturaId;

/**
 *
 * @author vavi
 */
public interface ErpOrdenXFacturaDao {
    
    public ErpOrdenXFacturaId save(ErpOrdenXFactura erpOrdenXFactura);
       public boolean delete(ErpOrdenXFactura deleteErpOrdenXFactura);
       public boolean update(ErpOrdenXFactura updateErpOrdenXFactura);
       public boolean actualizaEstatus(ErpOrdenXFactura comp);
    
}
