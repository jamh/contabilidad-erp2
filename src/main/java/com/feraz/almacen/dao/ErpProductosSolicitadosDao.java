/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.almacen.dao;

import com.feraz.almacen.model.ErpProductosSolicitados;
import com.feraz.almacen.model.ErpProductosSolicitadosId;

/**
 *
 * @author FERAZ-14
 */
public interface ErpProductosSolicitadosDao {
    
      public ErpProductosSolicitadosId save(ErpProductosSolicitados erpProductosSolicitados);
       public ErpProductosSolicitados findErpProductosSolicitados(String compania, Integer idSolicitud);
       public boolean delete(ErpProductosSolicitados erpProductosSolicitados);
       public boolean update(ErpProductosSolicitados erpProductosSolicitados);
        public int getMaxIdErpProductosSolicitados(ErpProductosSolicitadosId id);
    
}
