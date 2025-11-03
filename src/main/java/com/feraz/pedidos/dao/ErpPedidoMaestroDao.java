/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.dao;

import com.feraz.pedidos.model.ErpPedidoMaestro;
import com.feraz.pedidos.model.ErpPedidoMaestroId;

/**
 *
 * @author vavi
 */
public interface ErpPedidoMaestroDao {
    
      public ErpPedidoMaestroId save(ErpPedidoMaestro ErpPedidoMaestro);
       public ErpPedidoMaestro findErpPedidoMaestro(String compania, Integer folio);
       public boolean delete(ErpPedidoMaestro deleteErpPedidoMaestro);
       public boolean update(ErpPedidoMaestro updateErpPedidoMaestro);
        public int getMaxIdPedidos(ErpPedidoMaestroId id);
        
        public boolean actualizaEstatus(ErpPedidoMaestro comp);
        public boolean actualizaEstatusUrgente(ErpPedidoMaestro comp) ;
        public boolean actualizaEstatusUrgenteSinArea(ErpPedidoMaestro comp);
        
    
}
